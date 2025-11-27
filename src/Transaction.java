/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
/**
 *
 * @author murta
 */
public class Transaction {
    public String nomor;
    public String type;
    public long nominal;
    public LocalDate date;
    public LocalDateTime now;
    public Transaction next;
    public String category;
    
    public Transaction(long nominal,String type){
        this.now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(format);
        Random random = new Random();
        String norandom = String.valueOf(random.nextInt(900)+100);
        this.nomor = timestamp + norandom;
        this.type = type;
        this.nominal = nominal;
        this.date = LocalDate.now();
    }

    public Transaction(long nominal,String type, String category){
        this.now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(format);
        Random random = new Random();
        String norandom = String.valueOf(random.nextInt(900)+100);
        this.nomor = timestamp + norandom;
        this.type = type;
        this.nominal = nominal;
        this.date = LocalDate.now();
        this.category = category;
    }
    
    public void displayLink(){
        System.out.print("\tID Transaksi : "+nomor);
        System.out.print(", Type : "+type);
        System.out.print(", Nominal : "+nominal);
        if(type.equalsIgnoreCase("Pengeluaran")){
            System.out.print(", Kategori : "+category);
        }
        System.out.println(", Tanggal : "+date);
    }
}
