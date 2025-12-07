package gui;

import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Main Dashboard GUI
 * Modern design with purple theme for main menu
 */
public class MainGUI extends JFrame {
    private Account account;
    private HashAccount hashAccount;
    private JLabel jSaldoLabel;
    private JButton jPendapatan;
    private JButton jPengeluaran;
    private JButton jInformasi;
    private JButton jRiwayat;
    private JButton jLogout;
    private JCheckBox jShowBalance;

    public MainGUI(Account account, HashAccount ha) {
        this.account = account;
        this.hashAccount = ha;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Header Panel (Purple)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 0, 155));
        headerPanel.setPreferredSize(new Dimension(500, 150));
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(10, 20, 10, 20);

        // Welcome Label
        gbcHeader.gridwidth = GridBagConstraints.REMAINDER;
        gbcHeader.gridy = 0;
        gbcHeader.anchor = GridBagConstraints.WEST;
        JLabel welcomeLabel = new JLabel("Selamat Datang, " + account.nama);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(255, 255, 255));
        headerPanel.add(welcomeLabel, gbcHeader);

        // Balance Label
        gbcHeader.gridy = 1;
        JLabel balanceTextLabel = new JLabel("Sisa Saldo");
        balanceTextLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        balanceTextLabel.setForeground(new Color(255, 255, 255));
        headerPanel.add(balanceTextLabel, gbcHeader);

        // Balance Value
        gbcHeader.gridy = 2;
        jSaldoLabel = new JLabel("Rp " + String.format("%,d", account.saldo));
        jSaldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jSaldoLabel.setForeground(new Color(255, 255, 255));
        headerPanel.add(jSaldoLabel, gbcHeader);

        // Show/Hide Balance Checkbox
        gbcHeader.gridy = 3;
        jShowBalance = new JCheckBox("Tampilkan saldo");
        jShowBalance.setSelected(true);
        jShowBalance.setForeground(new Color(255, 255, 255));
        jShowBalance.setBackground(new Color(65, 0, 155));
        jShowBalance.addActionListener(e -> toggleBalance());
        headerPanel.add(jShowBalance, gbcHeader);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.fill = GridBagConstraints.HORIZONTAL;
        gbcContent.insets = new Insets(15, 20, 15, 20);
        gbcContent.weightx = 1.0;

        // Menu Title
        gbcContent.gridwidth = GridBagConstraints.REMAINDER;
        gbcContent.gridy = 0;
        gbcContent.anchor = GridBagConstraints.CENTER;
        JLabel menuLabel = new JLabel("Menu Utama");
        menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuLabel.setForeground(new Color(65, 0, 155));
        contentPanel.add(menuLabel, gbcContent);

        // Buttons Panel
        gbcContent.gridy = 1;
        gbcContent.insets = new Insets(20, 20, 20, 20);
        JPanel buttonPanelTop = new JPanel();
        buttonPanelTop.setBackground(new Color(255, 255, 255));
        buttonPanelTop.setLayout(new GridLayout(1, 2, 15, 0));

        jPendapatan = createButton("Pendapatan", new Color(65, 0, 155));
        jPendapatan.addActionListener(e -> openPendapatan());
        buttonPanelTop.add(jPendapatan);

        jPengeluaran = createButton("Pengeluaran", new Color(65, 0, 155));
        jPengeluaran.addActionListener(e -> openPengeluaran());
        buttonPanelTop.add(jPengeluaran);

        contentPanel.add(buttonPanelTop, gbcContent);

        // Second Row Buttons
        gbcContent.gridy = 2;
        JPanel buttonPanelMiddle = new JPanel();
        buttonPanelMiddle.setBackground(new Color(255, 255, 255));
        buttonPanelMiddle.setLayout(new GridLayout(1, 2, 15, 0));

        jRiwayat = createButton("Riwayat Transaksi", new Color(65, 0, 155));
        jRiwayat.addActionListener(e -> openRiwayat());
        buttonPanelMiddle.add(jRiwayat);

        jInformasi = createButton("Informasi Akun", new Color(65, 0, 155));
        jInformasi.addActionListener(e -> showAccountInfo());
        buttonPanelMiddle.add(jInformasi);

        contentPanel.add(buttonPanelMiddle, gbcContent);

        // Logout Button
        gbcContent.gridy = 3;
        gbcContent.insets = new Insets(20, 20, 15, 20);
        jLogout = createButton("Logout", new Color(220, 20, 60));
        jLogout.addActionListener(e -> logout());
        contentPanel.add(jLogout, gbcContent);

        // Main Frame Layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplikasi Pengelolaan Keuangan - Dashboard");
        setSize(500, 600);
        setResizable(false);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(new Color(255, 255, 255));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(150, 50));
        btn.setBorder(null);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        return btn;
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

        // Iterate through transactions using reflection or direct access
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
