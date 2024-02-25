import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilityDb {
    static String url = "jdbc:mysql://localhost:3306/cwbank";
    static String dbuser = "root";
    static String dbpassword = "root";

    public static void main(String[] args) {
        try {
            Class.forName("java.sql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recordHistory(String senderPhone, String senderName,
                                      String receiverPhone, String receiverName, double amount) {
        try {
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter formatStyle = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:MM:ss");
            String transferTime = formatStyle.format(timeNow);
            System.out.println(transferTime);
            Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("insert into " +
                    "histories(senderName, receiverName, senderPhone, receiverPhone,transferTime,amount) " +
                    "values ('" + senderName + "', '" + receiverName + "','" + senderPhone + "','" + receiverPhone + "','" +
                    transferTime + "'," + amount + ");");
            if (status == 1)
                System.out.println("Transfer Recorded successfully.");
            else
                System.out.println("Failed to record.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean checkDbData(String column, String inputData) {
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users;");

            String dbData;
            while (resultSet.next()) {
                if (column.equals("age") || column.equals("idnumber")) {
                    int dbDataInt = resultSet.getInt(column);
                    dbData = Integer.toString(dbDataInt);
                } else {
                    dbData = resultSet.getString(column);
                }

//                System.out.println(name);
                if (dbData.equals(inputData)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkPhNumber(String inpPhone) {
        return checkDbData("phone", inpPhone);
    }

    public static boolean checkNRC(String statenum, String city, String inpNrcType, int inpNrcNumber) {
        boolean checkedStateNum = false;
        boolean checkedCity = false;
        boolean checkedType = false;
        boolean checkedNumber = false;

        if (statenum != null && !statenum.isEmpty()) {
            checkedStateNum = checkDbData("statenumber", statenum);
        }

        if (city != null && !city.isEmpty()) {
            checkedCity = checkDbData("city", city);
        }

        if (inpNrcType != null && !inpNrcType.isEmpty()) {
            checkedType = checkDbData("idtype", inpNrcType);
        }

        if (inpNrcNumber > 0) {
            checkedNumber = checkDbData("idnumber", Integer.toString(inpNrcNumber));
        }

        return checkedStateNum && checkedCity && checkedType && checkedNumber;
    }


    public static boolean checkUserLogIn(String phone, String password) {

        if (!checkPhNumber(phone))
            return false;
        ResultSet userinfo = retrieveData(phone);
        while (true) {
            try {
                if (!userinfo.next()) break;
                String dbPassword = userinfo.getString("password");
                return password.equals(dbPassword);
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
                return false;
            }
        }
        return false;

    }

    public static boolean registerUser(String name, String password,
                                       String phone, String gender,
                                       int age, String stateNumber, String city,
                                       String idType, int idNumber) {
        String query = "insert into users (name, password, phone, gender, age, statenumber, city, idtype, idnumber) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, phone);
            statement.setString(4, gender);
            statement.setInt(5, age);
            statement.setString(6, stateNumber);
            statement.setString(7, city);
            statement.setString(8, idType);
            statement.setInt(9, idNumber);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error registering user: " + e.getMessage(), e);
        }
    }

    public static ResultSet retrieveData(String phone) {
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from users where phone = " + phone);
            return res;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean cashIntoAccount(String phone, double balance) {
        try {
            Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
            PreparedStatement pst = connection.prepareStatement("update users set balance = ? where phone = ?");
            pst.setDouble(1, balance);
            pst.setString(2, phone);
            int status = pst.executeUpdate();
            return status == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean transferBalance(String sender, String receiver, double amount) {
        ResultSet senderInfo = retrieveData(sender);
        ResultSet receiverInfo = retrieveData(receiver);

        try {
            if (!senderInfo.next() || !receiverInfo.next()) {
                System.out.println("error");
                return false;
            } else {
                double senderBalance = senderInfo.getDouble("balance");
                double receiverBalance = receiverInfo.getDouble("balance");
                String senderName = senderInfo.getString("name");
                String receiverName = receiverInfo.getString("name");

                senderBalance = senderBalance - amount;
                receiverBalance = receiverBalance + amount;

                if (cashIntoAccount(sender, senderBalance) &&
                        cashIntoAccount(receiver, receiverBalance)) {
                    recordHistory(sender, senderName, receiver, receiverName, amount);
                    return true;
                }
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showTransferHistories(String phoneNumber) {
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));

        try {
            Connection connection = DriverManager.getConnection(url, dbuser, dbpassword);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from histories where senderPhone = ?");
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "No transfer history found.");
            } else {
                JPanel headerPanel = new JPanel(new GridLayout(1, 4));
                headerPanel.add(new JLabel("Sender"));
                headerPanel.add(new JLabel("Receiver"));
                headerPanel.add(new JLabel("Amount"));
                headerPanel.add(new JLabel("Time"));
                historyPanel.add(headerPanel);

                do {
                    JPanel dataPanel = new JPanel(new GridLayout(1, 4));
                    dataPanel.add(new JLabel(resultSet.getString("senderName")));
                    dataPanel.add(new JLabel(resultSet.getString("receiverName")));
                    dataPanel.add(new JLabel(Double.toString(resultSet.getDouble("amount"))));
                    dataPanel.add(new JLabel(resultSet.getString("transferTime")));
                    historyPanel.add(dataPanel);
                } while (resultSet.next());
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, historyPanel, "Transfer Histories", JOptionPane.PLAIN_MESSAGE);
    }


}
