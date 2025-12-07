package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class untuk merepresentasikan satu transaksi
 */
public class Transaction {
    public String nomor;
    public String type;
    public long nominal;
    public LocalDate date;
    public LocalDateTime now;
    public Transaction next;
    public String category;
    
    public Transaction(long nominal, String type) {
        this.now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(format);
        this.nomor = timestamp;
        this.type = type;
        this.nominal = nominal;
        this.date = LocalDate.now();
    }

    public Transaction(long nominal, String type, String category) {
        this.now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(format);
        this.nomor = timestamp;
        this.type = type;
        this.nominal = nominal;
        this.date = LocalDate.now();
        this.category = category;
    }

    public LocalDate getTimesTamp() {
        return date;
    }
    
    public void displayLink() {
        System.out.print("\tID Transaksi : " + nomor);
        System.out.print(", Type : " + type);
        System.out.print(", Nominal : " + nominal);
        if (type.equalsIgnoreCase("Pengeluaran")) {
            System.out.print(", Kategori : " + category);
        }
        System.out.println(", Tanggal : " + date);
    }
}
