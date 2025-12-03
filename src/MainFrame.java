import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Main Frame untuk Aplikasi Manajemen Keuangan
 * Terintegrasi dengan HashAccount dan Account data structure
 */
public class MainFrame extends javax.swing.JFrame {
    
    private HashAccount hashAccount;
    private Account currentAccount;

    public MainFrame(HashAccount hashAccount, Account account) {
        this.hashAccount = hashAccount;
        this.currentAccount = account;
        initComponents();
        setLocationRelativeTo(null);
        updateSaldoDisplay();
    }

    private void updateSaldoDisplay() {
        lblSaldoValue.setText("Rp " + String.format("%,d", currentAccount.saldo));
        if (jCheckBox1.isSelected()) {
            lblSaldoValue.setEchoChar((char) 0);
        } else {
            lblSaldoValue.setEchoChar('•');
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        outline1 = new javax.swing.JPanel();
        lblSaldoValue = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnPendapatan = new javax.swing.JButton();
        btnPengeluaran = new javax.swing.JButton();
        btnInfoAkun = new javax.swing.JButton();
        btnRiwayat = new javax.swing.JButton();
        jKembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manajemen Keuangan - Dashboard");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        outline1.setBackground(new java.awt.Color(65, 0, 155));
        outline1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lblSaldoValue.setBackground(new java.awt.Color(65, 0, 155));
        lblSaldoValue.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldoValue.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblSaldoValue.setText("0");
        lblSaldoValue.setBorder(null);
        lblSaldoValue.setEditable(false);

        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setBackground(new java.awt.Color(65, 0, 155));
        jCheckBox1.setText("Tampilkan Saldo");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sisa Saldo:");

        jPanel3.setBackground(new java.awt.Color(38, 0, 91));

        btnPendapatan.setBackground(new java.awt.Color(65, 0, 155));
        btnPendapatan.setForeground(new java.awt.Color(255, 255, 255));
        btnPendapatan.setText("Pendapatan");
        btnPendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendapatanActionPerformed(evt);
            }
        });

        btnPengeluaran.setBackground(new java.awt.Color(65, 0, 155));
        btnPengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        btnPengeluaran.setText("Pengeluaran");
        btnPengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPengeluaranActionPerformed(evt);
            }
        });

        btnInfoAkun.setBackground(new java.awt.Color(65, 0, 155));
        btnInfoAkun.setForeground(new java.awt.Color(255, 255, 255));
        btnInfoAkun.setText("Informasi Akun");
        btnInfoAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoAkunActionPerformed(evt);
            }
        });

        btnRiwayat.setBackground(new java.awt.Color(65, 0, 155));
        btnRiwayat.setForeground(new java.awt.Color(255, 255, 255));
        btnRiwayat.setText("Riwayat Transaksi");
        btnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiwayatActionPerformed(evt);
            }
        });

        jKembali.setBackground(new java.awt.Color(65, 0, 155));
        jKembali.setForeground(new java.awt.Color(255, 255, 255));
        jKembali.setText("Logout");
        jKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnPendapatan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPengeluaran)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInfoAkun))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRiwayat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jKembali)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPendapatan)
                    .addComponent(btnPengeluaran)
                    .addComponent(btnInfoAkun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRiwayat)
                    .addComponent(jKembali))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome.setText("Selamat Datang, " + (currentAccount != null ? currentAccount.nama : ""));

        javax.swing.GroupLayout outline1Layout = new javax.swing.GroupLayout(outline1);
        outline1.setLayout(outline1Layout);
        outline1Layout.setHorizontalGroup(
            outline1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outline1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(outline1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome)
                    .addComponent(jLabel2)
                    .addGroup(outline1Layout.createSequentialGroup()
                        .addComponent(lblSaldoValue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        outline1Layout.setVerticalGroup(
            outline1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outline1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblWelcome)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outline1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaldoValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14));
        jLabel1.setForeground(new java.awt.Color(65, 0, 155));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outline1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(outline1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void jKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin logout?",
            "Konfirmasi Logout",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            LoginFrame loginFrame = new LoginFrame(hashAccount);
            loginFrame.setVisible(true);
            this.dispose();
        }
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox1.isSelected()) {
            lblSaldoValue.setEchoChar((char) 0);
        } else {
            lblSaldoValue.setEchoChar('•');
        }
    }

    private void btnPendapatanActionPerformed(java.awt.event.ActionEvent evt) {
        String input = JOptionPane.showInputDialog(
            this,
            "Masukkan nominal pemasukan:",
            "Pemasukan",
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                long nominal = Long.parseLong(input.trim());
                if (nominal <= 0) {
                    JOptionPane.showMessageDialog(this, "Nominal harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Transaction transaksi = new Transaction(nominal, "Pemasukan");
                currentAccount.addTransaksi(transaksi);
                currentAccount.saldo += nominal;
                updateSaldoDisplay();
                
                JOptionPane.showMessageDialog(
                    this,
                    "Pemasukan sebesar Rp " + String.format("%,d", nominal) + " berhasil ditambahkan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nominal harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {
        String[] categories = {"Makanan", "Belanja", "Liburan", "Lainnya"};
        String category = (String) JOptionPane.showInputDialog(
            this,
            "Pilih kategori pengeluaran:",
            "Kategori Pengeluaran",
            JOptionPane.QUESTION_MESSAGE,
            null,
            categories,
            categories[0]
        );
        
        if (category != null) {
            String input = JOptionPane.showInputDialog(
                this,
                "Masukkan nominal pengeluaran:",
                "Pengeluaran - " + category,
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (input != null && !input.trim().isEmpty()) {
                try {
                    long nominal = Long.parseLong(input.trim());
                    if (nominal <= 0) {
                        JOptionPane.showMessageDialog(this, "Nominal harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (currentAccount.saldo >= nominal) {
                        Transaction transaksi = new Transaction(nominal, "Pengeluaran", category);
                        currentAccount.addTransaksi(transaksi);
                        currentAccount.saldo -= nominal;
                        updateSaldoDisplay();
                        
                        JOptionPane.showMessageDialog(
                            this,
                            "Pengeluaran sebesar Rp " + String.format("%,d", nominal) + " untuk " + category + " berhasil dicatat!",
                            "Sukses",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(this, "Saldo tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Nominal harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void btnInfoAkunActionPerformed(java.awt.event.ActionEvent evt) {
        String info = "=== Informasi Akun ===\n\n" +
                      "ID Akun: " + currentAccount.id + "\n" +
                      "Nama: " + currentAccount.nama + "\n" +
                      "Saldo: Rp " + String.format("%,d", currentAccount.saldo);
        
        JOptionPane.showMessageDialog(
            this,
            info,
            "Informasi Akun",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void btnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {
        RiwayatFrame riwayatFrame = new RiwayatFrame(hashAccount, currentAccount);
        riwayatFrame.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HashAccount ha = new HashAccount(16);
                ha.insert("Test User", "test123", 100000);
                Account acc = ha.find(101);
                if (acc != null) {
                    new MainFrame(ha, acc).setVisible(true);
                }
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton btnPendapatan;
    private javax.swing.JButton btnPengeluaran;
    private javax.swing.JButton btnInfoAkun;
    private javax.swing.JButton btnRiwayat;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JButton jKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField lblSaldoValue;
    private javax.swing.JPanel outline1;
}
