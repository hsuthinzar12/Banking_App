import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Options extends JFrame {
    private JPanel contentPane;
    private boolean isLoggedIn = false;
    private String loggedInPhone = "";

    public Options(String loggedInPhone, boolean isLoggedIn) {
        this.loggedInPhone = loggedInPhone;
        this.isLoggedIn = isLoggedIn;
        setTitle("CW Pay");

        setIconImage(Toolkit.getDefaultToolkit().getImage("image/Logo.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 1321, 833);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(5, 1, 75));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnCashIn = new JButton("Cash In\r\n");
        btnCashIn.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnCashIn.setBackground(new Color(98, 207, 249));
        btnCashIn.setBounds(99, 369, 278, 97);
        contentPane.add(btnCashIn);

        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnTransfer.setBackground(new Color(98, 207, 249));
        btnTransfer.setBounds(462, 369, 278, 97);
        contentPane.add(btnTransfer);

        JButton btnBalance = new JButton("Balance");
        btnBalance.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnBalance.setBackground(new Color(98, 207, 249));
        btnBalance.setBounds(824, 369, 278, 97);
        contentPane.add(btnBalance);

        JButton btnHistory = new JButton("History");
        btnHistory.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnHistory.setBackground(new Color(98, 207, 249));
        btnHistory.setBounds(1185, 369, 278, 97);
        contentPane.add(btnHistory);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("image/Logo.png"));
        lblNewLabel.setBounds(504, 10, 500, 177);
        contentPane.add(lblNewLabel);

        JLabel lblLogout = new JLabel("");
        lblLogout.setIcon(new ImageIcon("image/logout.png"));
        lblLogout.setForeground(new Color(255, 255, 255));
        lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblLogout.setBounds(1268, 45, 329, 120);
        // backbtn.setBounds(1268, 10, 329, 133);
        contentPane.add(lblLogout);

        lblLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to logout ? ", "Log Out", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Login login = new Login();
                    login.setVisible(true);
                    dispose();
                }
            }
        });
        btnCashIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCashInDialog();
            }

        });

        btnBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLoggedIn) {
                    JPasswordField pswField = new JPasswordField(10);
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Enter your password:"));
                    panel.add(pswField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "Balance",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        String password = new String(pswField.getPassword());

                        if (UtilityDb.checkUserLogIn(loggedInPhone, password)) {
                            checkBalance(loggedInPhone);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please login first.");
                }
            }
        });


        btnTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transfer();
            }
        });

        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLoggedIn) {
                    JPasswordField pswField = new JPasswordField(10);
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Enter your password:"));
                    panel.add(pswField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "History",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        String password = new String(pswField.getPassword());
                        if (UtilityDb.checkUserLogIn(loggedInPhone, password)) {
                            UtilityDb.showTransferHistories(loggedInPhone);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please login first.");
                }
            }
        });
    }

    //For Cash In
    private void showCashInDialog() {
        JTextField phoneField = new JTextField(loggedInPhone, 10);
        JTextField amountField = new JTextField(10);
        JPasswordField pswField = new JPasswordField(10);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Phone number:"));
        panel.add(phoneField);
        panel.add(new JLabel("Enter amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Enter password:"));
        panel.add(pswField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Cash In",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String phone = phoneField.getText();
            String amount = amountField.getText();
            String password = new String(pswField.getPassword());

            ResultSet res = UtilityDb.retrieveData(phone);
            try {
                if (!res.next())
                    System.out.println("Balance update failed.");
                else {
                    double balance = res.getDouble("balance");
                    if (password.equals(res.getString("password"))) {
                        double increaseAmount = Double.parseDouble(amount);
                        double newBalance = increaseAmount + balance;
                        if (UtilityDb.cashIntoAccount(phone, newBalance)) {
                            JOptionPane.showMessageDialog(null, "Balance Updated");
                            System.out.println("Balance updated.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Fail to update balance");
                            System.out.println("Balance update failed.");
                        }
                    } else {
                        System.out.println("Incorrect password.");
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // To check balance
    private void checkBalance(String phone) {
        ResultSet dataSet = UtilityDb.retrieveData(phone);
        try {
            if (!dataSet.next())
                JOptionPane.showMessageDialog(null, "credential error");
            else {
                double balance = dataSet.getDouble("balance");
                JOptionPane.showMessageDialog(null, "Your balance is " + balance + " mmk");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // To transfer
    private void transfer() {
        JTextField receiverPhoneField = new JTextField(10);
        JTextField receiverNameField = new JTextField(10);
        JTextField amountField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        JPanel panel1 = new JPanel(new GridLayout(0, 1));
        panel1.add(new JLabel("Enter receiver's phone number:"));
        panel1.add(receiverPhoneField);

        int result1 = JOptionPane.showConfirmDialog(null, panel1, "Transfer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result1 == JOptionPane.OK_OPTION) {
            String receiverPhone = receiverPhoneField.getText();

            ResultSet receiverData = UtilityDb.retrieveData(receiverPhone);
            try {
                if (receiverData.next()) {
                    String receiverName = receiverData.getString("name");
                    receiverNameField.setText(receiverName);
                    receiverNameField.setEditable(false);

                    JPanel panel2 = new JPanel(new GridLayout(0, 1));
                    panel2.add(new JLabel("Receiver's Name:"));
                    panel2.add(receiverNameField);
                    panel2.add(new JLabel("Receiver's Phone Number:"));
                    panel2.add(new JLabel(receiverPhone));
                    panel2.add(new JLabel("Enter amount to transfer:"));
                    panel2.add(amountField);

                    int result2 = JOptionPane.showConfirmDialog(null, panel2, "Transfer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result2 == JOptionPane.OK_OPTION) {
                        String amountStr = amountField.getText();
                        try {
                            double amount = Double.parseDouble(amountStr);

                            String confirmationMessage = "Are you sure to transfer " + amount + " mmk to " + receiverName + " (" + receiverPhone + ")?";
                            int confirmTransfer = JOptionPane.showConfirmDialog(null, confirmationMessage, "Check to transfer", JOptionPane.YES_NO_OPTION);

                            if (confirmTransfer == JOptionPane.YES_OPTION) {
                                ResultSet userData = UtilityDb.retrieveData(loggedInPhone);

                                if (userData.next()) {
                                    double userBalance = userData.getDouble("balance");

                                    if (userBalance >= amount) {
                                        JPanel panel3 = new JPanel(new GridLayout(0, 1));
                                        panel3.add(new JLabel("Enter your password:"));
                                        panel3.add(passwordField);

                                        int result3 = JOptionPane.showConfirmDialog(null, panel3, "Password Verification", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                        if (result3 == JOptionPane.OK_OPTION) {
                                            String password = new String(passwordField.getPassword());

                                            if (UtilityDb.checkUserLogIn(loggedInPhone, password)) {
                                                boolean success = UtilityDb.transferBalance(loggedInPhone, receiverPhone, amount);
                                                if (success) {
                                                    JOptionPane.showMessageDialog(null, "Transfer successful!");
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Transfer failed. Please try again.");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Incorrect password. Transfer aborted.");
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Insufficient amount to transfer.");
                                    }
                                }
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid amount format. Please enter a valid number.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Receiver with phone number " + receiverPhone + " does not exist.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            }
        }
    }
}

