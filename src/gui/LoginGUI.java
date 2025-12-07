package gui;

import model.*;
import gui.utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Modern Login GUI with enhanced design
 */
public class LoginGUI extends JFrame {
    private ModernTextField jUsername;
    private JPasswordField jPassword;
    private RoundedButton jLogin;
    private RoundedButton jSignUp;
    private HashAccount hashAccount;

    public LoginGUI(HashAccount ha) {
        this.hashAccount = ha;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Main Panel with gradient-like background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(0, 0, new Color(65, 0, 155),
                        0, getHeight(), new Color(41, 0, 98));
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 20, 15, 20);

        // Card panel for input
        ModernPanel cardPanel = new ModernPanel(new Color(255, 255, 255), 20);
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcCard = new GridBagConstraints();
        gbcCard.fill = GridBagConstraints.HORIZONTAL;
        gbcCard.insets = new Insets(15, 25, 15, 25);

        // Title
        gbcCard.gridwidth = GridBagConstraints.REMAINDER;
        gbcCard.gridy = 0;
        gbcCard.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 28));
        titleLabel.setForeground(new Color(65, 0, 155));
        cardPanel.add(titleLabel, gbcCard);

        // Subtitle
        gbcCard.gridy = 1;
        JLabel subtitleLabel = new JLabel("Masuk ke akun Anda");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(150, 150, 150));
        cardPanel.add(subtitleLabel, gbcCard);

        // Username Label
        gbcCard.gridy = 2;
        gbcCard.insets = new Insets(20, 25, 5, 25);
        gbcCard.anchor = GridBagConstraints.WEST;
        JLabel userLabel = new JLabel("Nomor Akun");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        userLabel.setForeground(new Color(50, 50, 50));
        cardPanel.add(userLabel, gbcCard);

        // Username Field
        gbcCard.gridy = 3;
        gbcCard.insets = new Insets(5, 25, 15, 25);
        jUsername = new ModernTextField(25);
        jUsername.setPreferredSize(new Dimension(280, 40));
        cardPanel.add(jUsername, gbcCard);

        // Password Label
        gbcCard.gridy = 4;
        gbcCard.insets = new Insets(10, 25, 5, 25);
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passLabel.setForeground(new Color(50, 50, 50));
        cardPanel.add(passLabel, gbcCard);

        // Password Field
        gbcCard.gridy = 5;
        gbcCard.insets = new Insets(5, 25, 20, 25);
        jPassword = new JPasswordField();
        jPassword.setPreferredSize(new Dimension(280, 40));
        jPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jPassword.setBorder(BorderFactory.createLineBorder(new Color(153, 51, 255), 2));
        jPassword.setMargin(new Insets(8, 12, 8, 12));
        jPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginAction();
                }
            }
        });
        cardPanel.add(jPassword, gbcCard);

        // Button Panel
        gbcCard.gridy = 6;
        gbcCard.insets = new Insets(25, 25, 15, 25);
        gbcCard.gridwidth = GridBagConstraints.REMAINDER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));

        jSignUp = new RoundedButton("Daftar", new Color(153, 51, 255), new Color(130, 30, 200));
        jSignUp.setPreferredSize(new Dimension(130, 45));
        jSignUp.addActionListener(e -> openRegister());
        buttonPanel.add(jSignUp);

        jLogin = new RoundedButton("Login", new Color(65, 0, 155), new Color(50, 0, 120));
        jLogin.setPreferredSize(new Dimension(130, 45));
        jLogin.addActionListener(e -> loginAction());
        buttonPanel.add(jLogin);

        cardPanel.add(buttonPanel, gbcCard);
        cardPanel.setPreferredSize(new Dimension(350, 450));

        // Add card to main panel
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(cardPanel, gbc);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplikasi Pengelolaan Keuangan - Login");
        setSize(500, 650);
        setResizable(false);
    }

    private void loginAction() {
        String usernameText = jUsername.getText();
        String passwordText = new String(jPassword.getPassword());

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nomor Akun dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, "Nomor Akun atau Password Salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                jPassword.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nomor Akun harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
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
