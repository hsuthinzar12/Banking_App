import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField txtphnum;
    private JTextField txtpsw;
    private boolean isLoggedIn = false;
    private String loggedInPhone = "";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setTitle("CW Pay");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Hsu\\Downloads\\Play-removebg-preview (1).png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(5, 1, 75));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Hsu\\Downloads\\Play-removebg-preview.png"));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 50));
        lblNewLabel.setBounds(565, 21, 492, 206);
        contentPane.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(5, 1, 90));
        panel.setBounds(450, 237, 788, 466);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Phone Number : ");
        lblNewLabel_1.setFont(new Font("Gadugi", Font.PLAIN, 30));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(79, 68, 292, 41);
        panel.add(lblNewLabel_1);

        txtphnum = new JTextField();
        txtphnum.setForeground(new Color(0, 0, 0));
        txtphnum.setFont(new Font("Tahoma", Font.PLAIN, 28));
        txtphnum.setBounds(442, 68, 258, 41);
        panel.add(txtphnum);
        txtphnum.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Password : ");
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Gadugi", Font.PLAIN, 30));
        lblNewLabel_1_1.setBounds(79, 193, 177, 41);
        panel.add(lblNewLabel_1_1);

        txtpsw = new JTextField();
        txtpsw.setForeground(new Color(0, 0, 0));
        txtpsw.setFont(new Font("Tahoma", Font.PLAIN, 28));
        txtpsw.setColumns(10);
        txtpsw.setBounds(442, 193, 258, 41);
        panel.add(txtpsw);

        JButton btnlogin = new JButton("Login");
        btnlogin.setFont(new Font("Georgia", Font.PLAIN, 30));
        btnlogin.setBounds(314, 305, 171, 55);
        panel.add(btnlogin);

        JLabel lblNewLabel_1_1_1 = new JLabel("Don't have CW Pay yet, Please ");
        lblNewLabel_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1.setFont(new Font("Gadugi", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(206, 415, 279, 41);
        panel.add(lblNewLabel_1_1_1);

        JLabel lblSignUp = new JLabel("Sign Up");
        lblSignUp.setForeground(new Color(255, 0, 0));
        lblSignUp.setFont(new Font("Gadugi", Font.PLAIN, 20));
        lblSignUp.setBounds(479, 415, 81, 41);
        panel.add(lblSignUp);

        JLabel lblforgotPsw = new JLabel("Forgot Password?");
        lblforgotPsw.setForeground(Color.RED);
        lblforgotPsw.setFont(new Font("BIZ UDGothic", Font.PLAIN, 18));
        lblforgotPsw.setBounds(324, 359, 161, 41);
        panel.add(lblforgotPsw);

        txtphnum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnlogin.doClick();
                }
            }
        });
        txtpsw.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnlogin.doClick();
                }
            }
        });
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        lblforgotPsw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Relax and try to remember your password","Forgot Password",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        lblSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignUp signUppage = new SignUp();
                signUppage.setVisible(true);
                dispose();
            }
        });
    }

    private void login() {
        String phone = txtphnum.getText();
        String password = txtpsw.getText();

        if (phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in both the phone number and password","Error",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (UtilityDb.checkPhNumber(phone)) {
            if (UtilityDb.checkUserLogIn(phone, password)) {
                JOptionPane.showMessageDialog(null, "Login Success!!!");
                isLoggedIn = true;
                loggedInPhone = phone;
                openOptionsPage(phone);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect password. Try again.","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Phone number does not exist. Please sign up.","Sign Up",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void openOptionsPage(String phone) {
        Options options = new Options(phone, isLoggedIn);
        options.setVisible(true);
        dispose();
    }

}

