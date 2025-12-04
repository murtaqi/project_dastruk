/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;
/**
 *
 * @author murta
 */
public class Account {
    public int id;
    public String nama;
    public String password;
    public long saldo;
    public Account next;
    public LinkedTransaction transaction;
    
    public Account(int id,String nama,String password,long saldo){
        this.id = id;
        this.nama = nama;
        this.password = password;
        this.saldo = saldo;
        transaction = new LinkedTransaction();
    }
    
    public Account(int id,String nama,String password){
        this.id = id;
        this.nama = nama;
        this.password = password;
    }
    
    public int getKey(){
        return id;
    }
    
    public void displayLink(){
        System.out.print(id+", ");
    }
    
    public void displayAccount(){
        System.out.print("\tId : "+id);
        System.out.print(", Nama : "+nama);
        System.out.println(", Saldo : "+saldo);
    }
    
    public void addTransaksi(Transaction newTrans){
        transaction.insert(newTrans);
    }
    
    public void displayRiwayat(){
        System.out.println("\t---Riwayat Transaksi---");
        transaction.displayList();
        System.out.println("\t-----------------------");
    }

    public void displayRiwayatBulanIni(){
        System.out.println("\t---Riwayat Transaksi Bulan Ini---");
        LocalDate now = LocalDate.now();
        Transaction current = transaction.find(now);
        if(current == null){
            System.out.println("\tBelum Ada Transaksi Bulan Ini");
            return;
        }
        while(current != null){
            if(current.date.getMonth() == now.getMonth() && current.date.getYear() == now.getYear()){
                current.displayLink();
            }
            current = current.next;
        }
        System.out.println();
    }

    public void displayRiwayatBulanLalu(){
        System.out.println("\t---Riwayat Transaksi Bulan Lalu---");
        LocalDate now = LocalDate.now().minusMonths(1);
        Transaction current = transaction.find(now);
        if(current == null){
            System.out.println("\tBelum Ada Transaksi Bulan Lalu");
            return;
        }
        while(current != null){
            if(current.date.getMonth() == now.getMonth() && current.date.getYear() == now.getYear()){
                current.displayLink();
            }
            current = current.next;
        }
        System.out.println();
    }
}
