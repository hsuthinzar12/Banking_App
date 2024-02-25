import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignUp extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsr;
    private JTextField txtphnum;
    private JTextField txtpsw;
    private JTextField txtAge;
    private JTextField idnumTxt;
    private JTextField distinctTxt;
    private JTextField txtType;
    private JRadioButton male;
    private JRadioButton female;
    private JSpinner stateSpin;
    private static String patternPw = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
    private static String patternPhone = "09[0-9]{7,9}";

    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    public SignUp() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Hsu\\Downloads\\Play-removebg-preview (1).png"));
        setTitle("CW Pay");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 1320, 896);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(5, 1, 75));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Hsu\\Downloads\\Play-removebg-preview (1).png"));
        lblNewLabel.setBounds(544, -14, 500, 177);
        contentPane.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(5, 1, 90));
        panel.setBounds(423, 156, 763, 636);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("User Name\t\t\t : ");
        lblNewLabel_1.setBounds(27, 25, 235, 41);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Gadugi", Font.PLAIN, 30));

        JLabel lblNewLabel_1_1 = new JLabel("Phone Number : ");
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Gadugi", Font.PLAIN, 30));
        lblNewLabel_1_1.setBounds(27, 110, 292, 41);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Gender : ");
        lblNewLabel_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1.setFont(new Font("Gadugi", Font.PLAIN, 30));
        lblNewLabel_1_1_1.setBounds(27, 199, 292, 41);
        panel.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Age : ");
        lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1_1.setFont(new Font("Gadugi", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1.setBounds(27, 288, 292, 41);
        panel.add(lblNewLabel_1_1_1_1);

        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("NRC : ");
        lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1_1_1.setFont(new Font("Gadugi", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1_1.setBounds(27, 386, 292, 41);
        panel.add(lblNewLabel_1_1_1_1_1);

        JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Password : ");
        lblNewLabel_1_1_1_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1_1_1_1.setFont(new Font("Gadugi", Font.PLAIN, 30));
        lblNewLabel_1_1_1_1_1_1.setBounds(27, 470, 292, 41);
        panel.add(lblNewLabel_1_1_1_1_1_1);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Georgia", Font.PLAIN, 30));
        btnSignUp.setBounds(296, 571, 171, 55);
        panel.add(btnSignUp);

        txtUsr = new JTextField();
        txtUsr.setForeground(Color.BLACK);
        txtUsr.setFont(new Font("Tahoma", Font.PLAIN, 28));
        txtUsr.setColumns(10);
        txtUsr.setBounds(419, 25, 317, 41);
        panel.add(txtUsr);

        txtphnum = new JTextField();
        txtphnum.setForeground(Color.BLACK);
        txtphnum.setFont(new Font("Tahoma", Font.PLAIN, 28));
        txtphnum.setColumns(10);
        txtphnum.setBounds(419, 110, 317, 41);
        panel.add(txtphnum);

        txtpsw = new JTextField();
        txtpsw.setForeground(Color.BLACK);
        txtpsw.setFont(new Font("Tahoma", Font.PLAIN, 28));
        txtpsw.setColumns(10);
        txtpsw.setBounds(419, 470, 317, 41);
        panel.add(txtpsw);

        txtAge = new JTextField();
        txtAge.setForeground(Color.BLACK);
        txtAge.setFont(new Font("Tahoma", Font.PLAIN, 28));
        txtAge.setColumns(10);
        txtAge.setBounds(419, 288, 317, 41);
        panel.add(txtAge);

        idnumTxt = new JTextField();
        idnumTxt.setForeground(Color.BLACK);
        idnumTxt.setFont(new Font("Tahoma", Font.PLAIN, 28));
        idnumTxt.setColumns(10);
        idnumTxt.setBounds(641, 388, 95, 41);
        panel.add(idnumTxt);

        stateSpin = new JSpinner();
        stateSpin.setModel(new SpinnerNumberModel(1, 1, 14, 1));
        stateSpin.setFont(new Font("Tahoma", Font.PLAIN, 28));
        stateSpin.setBounds(419, 387, 53, 40);
        panel.add(stateSpin);

        distinctTxt = new JTextField();
        distinctTxt.setForeground(Color.BLACK);
        distinctTxt.setFont(new Font("Tahoma", Font.PLAIN, 28));
        distinctTxt.setColumns(10);
        distinctTxt.setBounds(482, 388, 86, 41);
        panel.add(distinctTxt);

        JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("State/Distinct/Type/Registeration Number ");
        lblNewLabel_1_1_1_1_1_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_1_1_1_1_1_2.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
        lblNewLabel_1_1_1_1_1_2.setBounds(27, 420, 352, 41);
        panel.add(lblNewLabel_1_1_1_1_1_2);

        // Gender button grop
        ButtonGroup genderGroup = new ButtonGroup();

        male = new JRadioButton("Male");
        male.setForeground(new Color(255, 255, 255));
        male.setBackground(new Color(5, 1, 75));
        male.setFont(new Font("Tahoma", Font.PLAIN, 28));
        male.setBounds(419, 201, 118, 41);
        panel.add(male);
        genderGroup.add(male);

        female = new JRadioButton("Female");
        female.setForeground(Color.WHITE);
        female.setFont(new Font("Tahoma", Font.PLAIN, 28));
        female.setBackground(new Color(5, 1, 75));
        female.setBounds(618, 199, 118, 41);
        panel.add(female);
        genderGroup.add(female);

        txtType = new JTextField();
        txtType.setForeground(Color.BLACK);
        txtType.setFont(new Font("Tahoma", Font.PLAIN, 28));
        txtType.setColumns(10);
        txtType.setBounds(578, 388, 53, 41);
        panel.add(txtType);

        JLabel backbtn = new JLabel("");
        backbtn.setIcon(new ImageIcon("C:\\Users\\Hsu\\Downloads\\icons8-back-80.png"));
        backbtn.setBounds(1268, 10, 329, 133);
        contentPane.add(backbtn);

        backbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsr.getText().isEmpty() || txtphnum.getText().isEmpty() || txtpsw.getText().isEmpty() ||
                        txtAge.getText().isEmpty() || idnumTxt.getText().isEmpty() || distinctTxt.getText().isEmpty() ||
                        txtType.getText().isEmpty() || (!male.isSelected() && !female.isSelected())) {

                    JOptionPane.showMessageDialog(null, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    String password = txtpsw.getText();
                    String phoneNumber = txtphnum.getText();
                    int age = Integer.parseInt(txtAge.getText());

                    if (age < 18){
                        JOptionPane.showMessageDialog(null,"You must be over 18 to signup");
                        return;
                    }
                    if (!password.matches(patternPw)) {
                        JOptionPane.showMessageDialog(null, "Your password must have at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!phoneNumber.matches(patternPhone)) {
                        JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid phone number starting with '09' and followed by 9 to 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }
                registerUser();
            }
        });

    }

    private void registerUser() {
        String userName = txtUsr.getText();
        String phoneNumber = txtphnum.getText();
        String password = txtpsw.getText();
        String gender = male.isSelected() ? "Male" : "Female";
        int age = Integer.parseInt(txtAge.getText());
        String stateNumber = stateSpin.getValue().toString();
        String city = distinctTxt.getText();
        String idType = txtType.getText();
        int idNumber = Integer.parseInt(idnumTxt.getText());

        boolean phoneNumberExists = UtilityDb.checkPhNumber(phoneNumber);
        if (phoneNumberExists) {
            JOptionPane.showMessageDialog(null, "Phone number already exists. Please try with another one.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean success = UtilityDb.registerUser(userName, password, phoneNumber, gender, age, stateNumber, city, idType, idNumber);

        if (success) {
            JOptionPane.showMessageDialog(null, "Account created", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to register user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtUsr.setText("");
        txtphnum.setText("");
        txtpsw.setText("");
        txtAge.setText("");
        txtType.setText("");
        idnumTxt.setText("");
        distinctTxt.setText("");
        male.setSelected(false);
        female.setSelected(false);
    }
}
