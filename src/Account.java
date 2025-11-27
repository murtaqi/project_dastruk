/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Random;
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
}
