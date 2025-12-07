package gui;

import model.*;
import gui.utils.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Main Dashboard GUI
 * Modern design with purple theme, gradient background, and card-based layout
 */
public class MainGUI extends JFrame {
    private Account account;
    private HashAccount hashAccount;
    private JLabel jSaldoLabel;
    private RoundedButton jLogout;
    private JCheckBox jShowBalance;

    public MainGUI(Account account, HashAccount ha) {
        this.account = account;
        this.hashAccount = ha;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Background Panel with Gradient
        JPanel backgroundPanel = new JPanel() {
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
        backgroundPanel.setLayout(new BorderLayout());

        // Scroll pane for main content
        JScrollPane scrollPane = new JScrollPane(createMainContent());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(backgroundPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplikasi Pengelolaan Keuangan - Dashboard");
        setSize(600, 750);
        setResizable(false);
    }

    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 25, 10, 25);
        gbc.weightx = 1.0;

        // Welcome Card
        gbc.gridy = 0;
        mainPanel.add(createWelcomeCard(), gbc);

        // Balance Card
        gbc.gridy = 1;
        mainPanel.add(createBalanceCard(), gbc);

        // Menu Buttons - Row 1
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 25, 5, 25);
        mainPanel.add(createButtonRow(
                new String[]{"Pendapatan", "Pengeluaran"},
                new String[]{"Tambah pendapatan", "Catat pengeluaran"}
        ), gbc);

        // Menu Buttons - Row 2
        gbc.gridy = 3;
        mainPanel.add(createButtonRow(
                new String[]{"Riwayat", "Informasi"},
                new String[]{"Lihat transaksi", "Kelola akun"}
        ), gbc);

        // Logout Button
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 25, 20, 25);
        jLogout = new RoundedButton("Logout", new Color(220, 20, 60), new Color(200, 10, 50));
        jLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLogout.setPreferredSize(new Dimension(150, 45));
        jLogout.addActionListener(e -> logout());
        mainPanel.add(jLogout, gbc);

        return mainPanel;
    }

    private ModernPanel createWelcomeCard() {
        ModernPanel card = new ModernPanel(new Color(255, 255, 255), 15);
        card.setPreferredSize(new Dimension(500, 80));
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);

        JLabel welcomeLabel = new JLabel("Selamat Datang, " + account.nama + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(65, 0, 155));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(welcomeLabel, gbc);

        return card;
    }

    private ModernPanel createBalanceCard() {
        ModernPanel card = new ModernPanel(new Color(255, 255, 255), 15);
        card.setPreferredSize(new Dimension(500, 140));
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 15);

        // Title
        JLabel titleLabel = new JLabel("Saldo Anda");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(new Color(150, 150, 150));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        card.add(titleLabel, gbc);

        // Balance Value
        jSaldoLabel = new JLabel("Rp " + String.format("%,d", account.saldo));
        jSaldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        jSaldoLabel.setForeground(new Color(65, 0, 155));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 15, 10, 15);
        card.add(jSaldoLabel, gbc);

        // Checkbox
        jShowBalance = new JCheckBox("Tampilkan saldo");
        jShowBalance.setSelected(true);
        jShowBalance.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jShowBalance.setForeground(new Color(100, 100, 100));
        jShowBalance.setOpaque(false);
        jShowBalance.addActionListener(e -> toggleBalance());
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 15, 5, 15);
        card.add(jShowBalance, gbc);

        return card;
    }

    private JPanel createButtonRow(String[] labels, String[] tooltips) {
        JPanel rowPanel = new JPanel();
        rowPanel.setOpaque(false);
        rowPanel.setLayout(new GridLayout(1, 2, 15, 0));

        for (int i = 0; i < labels.length; i++) {
            RoundedButton btn = new RoundedButton(labels[i], new Color(65, 0, 155), new Color(80, 10, 180));
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setPreferredSize(new Dimension(140, 50));
            btn.setToolTipText(tooltips[i]);

            String label = labels[i];
            btn.addActionListener(e -> handleButtonClick(label));
            rowPanel.add(btn);
        }

        return rowPanel;
    }

    private void handleButtonClick(String buttonLabel) {
        switch (buttonLabel) {
            case "Pendapatan" -> openPendapatan();
            case "Pengeluaran" -> openPengeluaran();
            case "Riwayat" -> openRiwayat();
            case "Informasi" -> showAccountInfo();
        }
    }

    private void toggleBalance() {
        if (jShowBalance.isSelected()) {
            jSaldoLabel.setText("Rp " + String.format("%,d", account.saldo));
        } else {
            jSaldoLabel.setText("Rp ••••••");
        }
    }

    private void openPendapatan() {
        String inputStr = JOptionPane.showInputDialog(this, "Masukkan nominal pendapatan:", "Pendapatan Baru", JOptionPane.PLAIN_MESSAGE);
        if (inputStr != null && !inputStr.isEmpty()) {
            try {
                long nominal = Long.parseLong(inputStr);
                Transaction trans = new Transaction(nominal, "Pemasukan");
                account.addTransaksi(trans);
                account.saldo += nominal;
                jSaldoLabel.setText("Rp " + String.format("%,d", account.saldo));
                JOptionPane.showMessageDialog(this, "Pendapatan berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                DataStorage.saveAllAccounts(hashAccount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nominal harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openPengeluaran() {
        String[] categories = {"Makanan", "Belanja", "Liburan", "Lainnya"};
        String category = (String) JOptionPane.showInputDialog(this, "Pilih kategori pengeluaran:",
                "Pengeluaran", JOptionPane.QUESTION_MESSAGE, null, categories, categories[0]);

        if (category != null) {
            String nominalStr = JOptionPane.showInputDialog(this, "Masukkan nominal pengeluaran:", "Pengeluaran Baru", JOptionPane.PLAIN_MESSAGE);
            if (nominalStr != null && !nominalStr.isEmpty()) {
                try {
                    long nominal = Long.parseLong(nominalStr);
                    if (account.saldo >= nominal) {
                        Transaction trans = new Transaction(nominal, "Pengeluaran", category);
                        account.addTransaksi(trans);
                        account.saldo -= nominal;
                        jSaldoLabel.setText("Rp " + String.format("%,d", account.saldo));
                        JOptionPane.showMessageDialog(this, "Pengeluaran berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        DataStorage.saveAllAccounts(hashAccount);
                    } else {
                        JOptionPane.showMessageDialog(this, "Saldo tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Nominal harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void openRiwayat() {
        JFrame riwayatFrame = new JFrame("Riwayat Transaksi");
        riwayatFrame.setSize(600, 400);
        riwayatFrame.setLocationRelativeTo(this);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Tanggal", "Tipe", "Kategori", "Nominal"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Iterate through transactions using reflection to access private first field
        try {
            java.lang.reflect.Field firstField = account.transaction.getClass().getDeclaredField("first");
            firstField.setAccessible(true);
            Transaction current = (Transaction) firstField.get(account.transaction);
            
            while (current != null) {
                model.addRow(new Object[]{
                        current.nomor,
                        current.date,
                        current.type,
                        current.category != null ? current.category : "-",
                        "Rp " + String.format("%,d", current.nominal)
                });
                current = current.next;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading transactions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        riwayatFrame.add(new JScrollPane(table));
        riwayatFrame.setVisible(true);
    }

    private void showAccountInfo() {
        // Count transactions
        int transactionCount = 0;
        try {
            java.lang.reflect.Field firstField = account.transaction.getClass().getDeclaredField("first");
            firstField.setAccessible(true);
            Transaction current = (Transaction) firstField.get(account.transaction);
            while (current != null) {
                transactionCount++;
                current = current.next;
            }
        } catch (Exception e) {
            transactionCount = 0;
        }

        StringBuilder info = new StringBuilder();
        info.append("=== INFORMASI AKUN ===\n\n");
        info.append("Nama: ").append(account.nama).append("\n");
        info.append("Nomor Akun: ").append(account.id).append("\n");
        info.append("Saldo: Rp ").append(String.format("%,d", account.saldo)).append("\n");
        info.append("Jumlah Transaksi: ").append(transactionCount).append("\n");

        JTextArea textArea = new JTextArea(info.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Informasi Akun", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        DataStorage.saveAllAccounts(hashAccount);
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            LoginGUI login = new LoginGUI(hashAccount);
            login.setVisible(true);
            this.dispose();
        }
    }
}
