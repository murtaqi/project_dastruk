package model;

import java.time.LocalDate;

/**
 * Class untuk merepresentasikan satu akun pengguna
 */
public class Account {
    public int id;
    public String nama;
    public String password;
    public long saldo;
    public Account next;
    public LinkedTransaction transaction;
    
    public Account(int id, String nama, String password, long saldo) {
        this.id = id;
        this.nama = nama;
        this.password = password;
        this.saldo = saldo;
        transaction = new LinkedTransaction();
    }
    
    public int getKey() {
        return id;
    }
    
    public void displayLink() {
        System.out.print(id + ", ");
    }
    
    public void displayAccount() {
        System.out.print("\tId : " + id);
        System.out.print(", Nama : " + nama);
        System.out.println(", Saldo : " + saldo);
    }
    
    public void addTransaksi(Transaction newTrans) {
        transaction.insert(newTrans);
    }

    public void addTransaksiAtTail(Transaction newTrans) {
        transaction.insertAtTail(newTrans);
    }
    
    public void displayRiwayat() {
        System.out.println("\t---Riwayat Transaksi---");
        transaction.displayList();
        System.out.println("\t-----------------------");
    }

    public void displayRiwayatBulanIni() {
        System.out.println("\t---Riwayat Transaksi Bulan Ini---");
        LocalDate now = LocalDate.now();
        Transaction current = transaction.getFirst();
        boolean found = false;
        while (current != null) {
            if (current.date.getMonthValue() == now.getMonthValue() && current.date.getYear() == now.getYear()) {
                current.displayLink();
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("\tBelum Ada Transaksi Bulan Ini");
        }
        System.out.println();
    }

    public void displayRiwayatBulanLalu() {
        System.out.println("\t---Riwayat Transaksi Bulan Lalu---");
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        Transaction current = transaction.getFirst();
        boolean found = false;
        while (current != null) {
            if (current.date.getMonthValue() == lastMonth.getMonthValue() && current.date.getYear() == lastMonth.getYear()) {
                current.displayLink();
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("\tBelum Ada Transaksi Bulan Lalu");
        }
        System.out.println();
    }
}
