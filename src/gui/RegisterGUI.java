package gui;

import model.*;
import javax.swing.*;
import java.awt.*;

/**
 * Register GUI Frame
 * Modern design with purple theme
 */
public class RegisterGUI extends JFrame {
    private JTextField jNama;
    private JPasswordField jPassword;
    private JTextField jSaldo;
    private JButton jSubmit;
    private JButton jBack;
    private HashAccount hashAccount;

    public RegisterGUI(HashAccount ha) {
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
        JLabel titleLabel = new JLabel("Daftar Akun Baru");
        titleLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 24));
        titleLabel.setForeground(new Color(65, 0, 155));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Name Label
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 10, 5, 10);
        JLabel namaLabel = new JLabel("Nama Lengkap");
        namaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        namaLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(namaLabel, gbc);

        // Name Field
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        jNama = new JTextField();
        jNama.setPreferredSize(new Dimension(250, 35));
        jNama.setBorder(BorderFactory.createLineBorder(new Color(104, 0, 204), 2));
        jNama.setBackground(new Color(255, 255, 255));
        jNama.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mainPanel.add(jNama, gbc);

        // Password Label
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 5, 10);
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(passLabel, gbc);

        // Password Field
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 10, 10, 10);
        jPassword = new JPasswordField();
        jPassword.setPreferredSize(new Dimension(250, 35));
        jPassword.setBorder(BorderFactory.createLineBorder(new Color(104, 0, 204), 2));
        jPassword.setBackground(new Color(255, 255, 255));
        jPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mainPanel.add(jPassword, gbc);

        // Saldo Label
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 5, 10);
        JLabel saldoLabel = new JLabel("Saldo Awal (opsional)");
        saldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saldoLabel.setForeground(new Color(0, 0, 0));
        mainPanel.add(saldoLabel, gbc);

        // Saldo Field
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 10, 20, 10);
        jSaldo = new JTextField("0");
        jSaldo.setPreferredSize(new Dimension(250, 35));
        jSaldo.setBorder(BorderFactory.createLineBorder(new Color(104, 0, 204), 2));
        jSaldo.setBackground(new Color(255, 255, 255));
        jSaldo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mainPanel.add(jSaldo, gbc);

        // Button Panel
        gbc.gridy = 7;
        gbc.insets = new Insets(20, 10, 10, 10);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));

        jBack = new JButton("Kembali");
        jBack.setBackground(new Color(153, 51, 255));
        jBack.setForeground(new Color(255, 255, 255));
        jBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jBack.setPreferredSize(new Dimension(100, 40));
        jBack.setBorder(null);
        jBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jBack.addActionListener(e -> goBack());

        jSubmit = new JButton("Daftar");
        jSubmit.setBackground(new Color(65, 0, 155));
        jSubmit.setForeground(new Color(255, 255, 255));
        jSubmit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jSubmit.setPreferredSize(new Dimension(100, 40));
        jSubmit.setBorder(null);
        jSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jSubmit.addActionListener(e -> registerAction());

        buttonPanel.add(jBack);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(jSubmit);

        mainPanel.add(buttonPanel, gbc);

        // Set Frame Properties
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplikasi Pengelolaan Keuangan - Daftar");
        setSize(420, 500);
        setResizable(false);
    }

    private void registerAction() {
        String nama = jNama.getText();
        String password = new String(jPassword.getPassword());
        String saldoText = jSaldo.getText();

        if (nama.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            long saldo = saldoText.isEmpty() ? 0 : Long.parseLong(saldoText);
            hashAccount.insert(nama, password, saldo);
            JOptionPane.showMessageDialog(this, "Akun berhasil dibuat! Silahkan login.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            goBack();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Saldo harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        LoginGUI login = new LoginGUI(hashAccount);
        login.setVisible(true);
        this.dispose();
    }
}
