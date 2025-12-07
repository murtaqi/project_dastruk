package gui;

import model.*;
import gui.utils.*;
import javax.swing.*;
import java.awt.*;

/**
 * Modern Register GUI with enhanced design
 */
public class RegisterGUI extends JFrame {
    private ModernTextField jNama;
    private JPasswordField jPassword;
    private ModernTextField jSaldo;
    private RoundedButton jSubmit;
    private RoundedButton jBack;
    private HashAccount hashAccount;

    public RegisterGUI(HashAccount ha) {
        this.hashAccount = ha;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Main Panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(0, 0, new Color(65, 0, 155),
                        0, getHeight(), new Color(41, 0, 98));
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);

        // Card panel
        ModernPanel cardPanel = new ModernPanel(new Color(255, 255, 255), 20);
        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcCard = new GridBagConstraints();
        gbcCard.fill = GridBagConstraints.HORIZONTAL;
        gbcCard.insets = new Insets(15, 25, 15, 25);

        // Title
        gbcCard.gridwidth = GridBagConstraints.REMAINDER;
        gbcCard.gridy = 0;
        gbcCard.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("Daftar Akun");
        titleLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 28));
        titleLabel.setForeground(new Color(65, 0, 155));
        cardPanel.add(titleLabel, gbcCard);

        // Subtitle
        gbcCard.gridy = 1;
        JLabel subtitleLabel = new JLabel("Buat akun baru Anda");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(150, 150, 150));
        cardPanel.add(subtitleLabel, gbcCard);

        // Name Label
        gbcCard.gridy = 2;
        gbcCard.insets = new Insets(20, 25, 5, 25);
        gbcCard.anchor = GridBagConstraints.WEST;
        JLabel namaLabel = new JLabel("Nama Lengkap");
        namaLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        namaLabel.setForeground(new Color(50, 50, 50));
        cardPanel.add(namaLabel, gbcCard);

        // Name Field
        gbcCard.gridy = 3;
        gbcCard.insets = new Insets(5, 25, 15, 25);
        jNama = new ModernTextField(25);
        jNama.setPreferredSize(new Dimension(280, 40));
        cardPanel.add(jNama, gbcCard);

        // Password Label
        gbcCard.gridy = 4;
        gbcCard.insets = new Insets(10, 25, 5, 25);
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passLabel.setForeground(new Color(50, 50, 50));
        cardPanel.add(passLabel, gbcCard);

        // Password Field
        gbcCard.gridy = 5;
        gbcCard.insets = new Insets(5, 25, 15, 25);
        jPassword = new JPasswordField();
        jPassword.setPreferredSize(new Dimension(280, 40));
        jPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jPassword.setBorder(BorderFactory.createLineBorder(new Color(153, 51, 255), 2));
        jPassword.setMargin(new Insets(8, 12, 8, 12));
        cardPanel.add(jPassword, gbcCard);

        // Saldo Label
        gbcCard.gridy = 6;
        gbcCard.insets = new Insets(10, 25, 5, 25);
        JLabel saldoLabel = new JLabel("Saldo Awal (opsional)");
        saldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        saldoLabel.setForeground(new Color(50, 50, 50));
        cardPanel.add(saldoLabel, gbcCard);

        // Saldo Field
        gbcCard.gridy = 7;
        gbcCard.insets = new Insets(5, 25, 20, 25);
        jSaldo = new ModernTextField(25);
        jSaldo.setText("0");
        jSaldo.setPreferredSize(new Dimension(280, 40));
        cardPanel.add(jSaldo, gbcCard);

        // Button Panel
        gbcCard.gridy = 8;
        gbcCard.insets = new Insets(25, 25, 15, 25);
        gbcCard.gridwidth = GridBagConstraints.REMAINDER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 2, 15, 0));

        jBack = new RoundedButton("Kembali", new Color(153, 51, 255), new Color(130, 30, 200));
        jBack.setPreferredSize(new Dimension(130, 45));
        jBack.addActionListener(e -> goBack());
        buttonPanel.add(jBack);

        jSubmit = new RoundedButton("Daftar", new Color(65, 0, 155), new Color(50, 0, 120));
        jSubmit.setPreferredSize(new Dimension(130, 45));
        jSubmit.addActionListener(e -> registerAction());
        buttonPanel.add(jSubmit);

        cardPanel.add(buttonPanel, gbcCard);
        cardPanel.setPreferredSize(new Dimension(380, 550));

        // Add card to main panel
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(cardPanel, gbc);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplikasi Pengelolaan Keuangan - Daftar");
        setSize(540, 700);
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
            Account newAccount = hashAccount.insert(nama, password, saldo);
            JOptionPane.showMessageDialog(this, 
                "✓ Akun berhasil dibuat!\n\nNomor Akun Anda: " + newAccount.id + 
                "\nGunakan nomor akun ini saat login.", 
                "Pendaftaran Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
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
