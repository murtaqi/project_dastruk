package gui;

import model.*;
import gui.utils.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

/**
 * GUI untuk menampilkan riwayat transaksi dengan filter
 * - Semua transaksi
 * - Bulan ini
 * - Bulan lalu
 * - Pencarian berdasarkan ID
 */
public class RiwayatGUI extends JFrame {
    private Account account;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel infoLabel;
    private JTextField searchField;
    
    public RiwayatGUI(Account account) {
        this.account = account;
        initComponents();
        setLocationRelativeTo(null);
        loadAllTransactions();
    }
    
    private void initComponents() {
        setTitle("Riwayat Transaksi");
        setSize(750, 550);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel atas untuk filter dan pencarian
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 250));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel filter (kiri)
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filterPanel.setOpaque(false);
        
        JLabel filterLabel = new JLabel("Filter:");
        filterLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        filterPanel.add(filterLabel);
        
        // Tombol filter
        RoundedButton btnSemua = createFilterButton("Semua");
        RoundedButton btnBulanIni = createFilterButton("Bulan Ini");
        RoundedButton btnBulanLalu = createFilterButton("Bulan Lalu");
        
        btnSemua.addActionListener(e -> loadAllTransactions());
        btnBulanIni.addActionListener(e -> loadTransactionsBulanIni());
        btnBulanLalu.addActionListener(e -> loadTransactionsBulanLalu());
        
        filterPanel.add(btnSemua);
        filterPanel.add(btnBulanIni);
        filterPanel.add(btnBulanLalu);
        
        // Panel pencarian (kanan)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        searchPanel.setOpaque(false);
        
        JLabel searchLabel = new JLabel("Cari ID:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchPanel.add(searchLabel);
        
        searchField = new JTextField(15);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchField.setPreferredSize(new Dimension(150, 30));
        searchPanel.add(searchField);
        
        RoundedButton btnCari = createFilterButton("Cari");
        btnCari.setBackground(new Color(0, 128, 0));
        btnCari.addActionListener(e -> searchById());
        searchPanel.add(btnCari);
        
        // Enter key untuk pencarian
        searchField.addActionListener(e -> searchById());
        
        topPanel.add(filterPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
        
        // Tabel
        tableModel = new DefaultTableModel(
                new String[]{"ID Transaksi", "Tanggal", "Tipe", "Kategori", "Nominal"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(65, 0, 155));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(200, 180, 230));
        table.setGridColor(new Color(220, 220, 220));
        
        // Klik dua kali untuk melihat detail
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        showTransactionDetail(row);
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Panel info bawah
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(245, 245, 250));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        infoLabel = new JLabel("Total: 0 transaksi | Pemasukan: Rp 0 | Pengeluaran: Rp 0");
        infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoLabel.setForeground(new Color(65, 0, 155));
        bottomPanel.add(infoLabel, BorderLayout.WEST);
        
        JLabel hintLabel = new JLabel("Klik dua kali untuk melihat detail transaksi");
        hintLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        hintLabel.setForeground(new Color(120, 120, 120));
        bottomPanel.add(hintLabel, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private RoundedButton createFilterButton(String text) {
        RoundedButton btn = new RoundedButton(text, new Color(65, 0, 155), new Color(85, 20, 175));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.setPreferredSize(new Dimension(90, 30));
        return btn;
    }
    
    /**
     * Memuat semua transaksi
     */
    private void loadAllTransactions() {
        tableModel.setRowCount(0);
        long totalPemasukan = 0, totalPengeluaran = 0;
        int count = 0;
        
        try {
            java.lang.reflect.Field firstField = account.transaction.getClass().getDeclaredField("first");
            firstField.setAccessible(true);
            Transaction current = (Transaction) firstField.get(account.transaction);
            
            while (current != null) {
                tableModel.addRow(new Object[]{
                        current.nomor,
                        current.date,
                        current.type,
                        current.category != null ? current.category : "-",
                        "Rp " + String.format("%,d", current.nominal)
                });
                
                if (current.type.equalsIgnoreCase("Pemasukan")) {
                    totalPemasukan += current.nominal;
                } else {
                    totalPengeluaran += current.nominal;
                }
                count++;
                current = current.next;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        updateInfoLabel("Semua Transaksi", count, totalPemasukan, totalPengeluaran);
    }
    
    /**
     * Memuat transaksi bulan ini
     */
    private void loadTransactionsBulanIni() {
        tableModel.setRowCount(0);
        long totalPemasukan = 0, totalPengeluaran = 0;
        int count = 0;
        
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();
        
        try {
            java.lang.reflect.Field firstField = account.transaction.getClass().getDeclaredField("first");
            firstField.setAccessible(true);
            Transaction current = (Transaction) firstField.get(account.transaction);
            
            while (current != null) {
                if (current.date.getMonthValue() == currentMonth && 
                    current.date.getYear() == currentYear) {
                    
                    tableModel.addRow(new Object[]{
                            current.nomor,
                            current.date,
                            current.type,
                            current.category != null ? current.category : "-",
                            "Rp " + String.format("%,d", current.nominal)
                    });
                    
                    if (current.type.equalsIgnoreCase("Pemasukan")) {
                        totalPemasukan += current.nominal;
                    } else {
                        totalPengeluaran += current.nominal;
                    }
                    count++;
                }
                current = current.next;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        String monthName = now.getMonth().toString();
        updateInfoLabel("Bulan Ini (" + monthName + " " + currentYear + ")", count, totalPemasukan, totalPengeluaran);
    }
    
    /**
     * Memuat transaksi bulan lalu
     */
    private void loadTransactionsBulanLalu() {
        tableModel.setRowCount(0);
        long totalPemasukan = 0, totalPengeluaran = 0;
        int count = 0;
        
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        int lastMonthValue = lastMonth.getMonthValue();
        int lastMonthYear = lastMonth.getYear();
        
        try {
            java.lang.reflect.Field firstField = account.transaction.getClass().getDeclaredField("first");
            firstField.setAccessible(true);
            Transaction current = (Transaction) firstField.get(account.transaction);
            
            while (current != null) {
                if (current.date.getMonthValue() == lastMonthValue && 
                    current.date.getYear() == lastMonthYear) {
                    
                    tableModel.addRow(new Object[]{
                            current.nomor,
                            current.date,
                            current.type,
                            current.category != null ? current.category : "-",
                            "Rp " + String.format("%,d", current.nominal)
                    });
                    
                    if (current.type.equalsIgnoreCase("Pemasukan")) {
                        totalPemasukan += current.nominal;
                    } else {
                        totalPengeluaran += current.nominal;
                    }
                    count++;
                }
                current = current.next;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        String monthName = lastMonth.getMonth().toString();
        updateInfoLabel("Bulan Lalu (" + monthName + " " + lastMonthYear + ")", count, totalPemasukan, totalPengeluaran);
    }
    
    /**
     * Mencari transaksi berdasarkan ID
     */
    private void searchById() {
        String searchId = searchField.getText().trim();
        
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID transaksi yang ingin dicari!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        tableModel.setRowCount(0);
        Transaction found = null;
        
        try {
            java.lang.reflect.Field firstField = account.transaction.getClass().getDeclaredField("first");
            firstField.setAccessible(true);
            Transaction current = (Transaction) firstField.get(account.transaction);
            
            while (current != null) {
                if (current.nomor.contains(searchId)) {
                    tableModel.addRow(new Object[]{
                            current.nomor,
                            current.date,
                            current.type,
                            current.category != null ? current.category : "-",
                            "Rp " + String.format("%,d", current.nominal)
                    });
                    if (current.nomor.equals(searchId)) {
                        found = current;
                    }
                }
                current = current.next;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Transaksi dengan ID '" + searchId + "' tidak ditemukan!", 
                    "Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
            loadAllTransactions();
        } else {
            infoLabel.setText("Hasil Pencarian: " + tableModel.getRowCount() + " transaksi ditemukan");
            
            // Jika ditemukan yang persis sama, tampilkan detailnya
            if (found != null) {
                showTransactionDetailPopup(found);
            }
        }
    }
    
    /**
     * Menampilkan detail transaksi dari baris terpilih
     */
    private void showTransactionDetail(int row) {
        String transId = (String) tableModel.getValueAt(row, 0);
        
        try {
            java.lang.reflect.Field firstField = account.transaction.getClass().getDeclaredField("first");
            firstField.setAccessible(true);
            Transaction current = (Transaction) firstField.get(account.transaction);
            
            while (current != null) {
                if (current.nomor.equals(transId)) {
                    showTransactionDetailPopup(current);
                    return;
                }
                current = current.next;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Menampilkan popup detail transaksi
     */
    private void showTransactionDetailPopup(Transaction trans) {
        StringBuilder detail = new StringBuilder();
        detail.append("╔══════════════════════════════════════╗\n");
        detail.append("║       DETAIL TRANSAKSI                       ║\n");
        detail.append("╠══════════════════════════════════════╣\n");
        detail.append("║ ID        : ").append(trans.nomor).append("\n");
        detail.append("║ Tanggal   : ").append(trans.date).append("\n");
        detail.append("║ Waktu     : ").append(trans.now != null ? trans.now.toLocalTime() : "-").append("\n");
        detail.append("║ Tipe      : ").append(trans.type).append("\n");
        detail.append("║ Kategori  : ").append(trans.category != null ? trans.category : "-").append("\n");
        detail.append("║ Nominal   : Rp ").append(String.format("%,d", trans.nominal)).append("\n");
        detail.append("╚══════════════════════════════════════╝");
        
        JTextArea textArea = new JTextArea(detail.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setBackground(new Color(250, 250, 255));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        
        JOptionPane.showMessageDialog(this, textArea, "Detail Transaksi", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Update label info di bagian bawah
     */
    private void updateInfoLabel(String filter, int count, long pemasukan, long pengeluaran) {
        infoLabel.setText(String.format("%s - Total: %d transaksi | Pemasukan: Rp %,d | Pengeluaran: Rp %,d", 
                filter, count, pemasukan, pengeluaran));
    }
}
