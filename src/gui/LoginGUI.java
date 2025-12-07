package gui;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Login GUI Frame
 * Modern design with purple theme inspired by ManajemenKeuangan
 */
public class LoginGUI extends JFrame {
    private JTextField jUsername;
    private JPasswordField jPassword;
    private JButton jLogin;
    private JButton jSignUp;
    private HashAccount hashAccount;

    public LoginGUI(HashAccount ha) {
        this.hashAccount = ha;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Login Page");
        titleLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 24));
        titleLabel.setForeground(new Color(65, 0, 155));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Username Label
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel userLabel = new JLabel("Username/Account Number");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(userLabel, gbc);

        // Username Field
        gbc.gridy = 2;
        jUsername = new JTextField();
        jUsername.setPreferredSize(new Dimension(250, 35));
        jUsername.setBorder(BorderFactory.createLineBorder(new Color(153, 51, 255), 2));
        jUsername.setBackground(new Color(255, 255, 255));
        jUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mainPanel.add(jUsername, gbc);

        // Password Label
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 10, 10, 10);
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(passLabel, gbc);

        // Password Field
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        jPassword = new JPasswordField();
        jPassword.setPreferredSize(new Dimension(250, 35));
        jPassword.setBorder(BorderFactory.createLineBorder(new Color(153, 51, 255), 2));
        jPassword.setBackground(new Color(255, 255, 255));
        jPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction();
                }
            }
        });
        mainPanel.add(jPassword, gbc);

        // Button Panel
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 10, 10, 10);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));

        jSignUp = new JButton("Daftar");
        jSignUp.setBackground(new Color(153, 51, 255));
        jSignUp.setForeground(new Color(255, 255, 255));
        jSignUp.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jSignUp.setPreferredSize(new Dimension(100, 40));
        jSignUp.setBorder(null);
        jSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jSignUp.addActionListener(e -> openRegister());

        jLogin = new JButton("Login");
        jLogin.setBackground(new Color(65, 0, 155));
        jLogin.setForeground(new Color(255, 255, 255));
        jLogin.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jLogin.setPreferredSize(new Dimension(100, 40));
        jLogin.setBorder(null);
        jLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLogin.addActionListener(e -> loginAction());

        buttonPanel.add(jSignUp);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(jLogin);

        mainPanel.add(buttonPanel, gbc);

        // Set Frame Properties
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplikasi Pengelolaan Keuangan - Login");
        setSize(400, 400);
        setResizable(false);
    }

    private void loginAction() {
        String usernameText = jUsername.getText();
        String passwordText = new String(jPassword.getPassword());

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int accountNo = Integer.parseInt(usernameText);
            Account account = hashAccount.find(accountNo);

            if (account != null && account.password.equals(passwordText)) {
                JOptionPane.showMessageDialog(this, "Selamat Datang " + account.nama, "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);
                openMain(account);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password Salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                jPassword.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Account number harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegister() {
        RegisterGUI register = new RegisterGUI(hashAccount);
        register.setVisible(true);
        this.dispose();
    }

    private void openMain(Account account) {
        MainGUI main = new MainGUI(account, hashAccount);
        main.setVisible(true);
    }
}
