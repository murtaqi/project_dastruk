/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
/**
 *
 * @author murta
 */
public class App {
    public static void main(String[] args) throws IOException{
        int no = 0;
        long value;
        String data;
        Account akun = null;
        Transaction transaksi;
        boolean login = false;
        HashAccount ha = new HashAccount(16);
        ha.insertAdmin("admin", "admin123");
        ha.insert("taqi","taqi123", 100000);
        ha.insert("aziz", "123", 50000);
        while(!login){
            System.out.print("Masukkan nomor akun :");
            no = getInt();
            System.out.print("Masukkan Password :");
            data = getString();
            akun = ha.find(no);
            if(akun != null && data.equals(akun.password)){
                System.out.println("Selamat Datang "+akun.nama);
                login = true;
            }else{
                System.out.println("Nomor Akun atau Password Salah!");
            }
        }
        while(login){
            if(akun.id == 100){
                    System.out.println("Anda login sebagai Admin");
                    ha.displayAccountsTable();
                    System.out.println("Ketik 'exit' untuk keluar");
                    String command = getString();
                    if(command.equalsIgnoreCase("exit")){
                        akun = null;
                        login = false;
                        System.out.println("Terima Kasih");
                        continue;
                    }
                }
            System.out.println("Pilih angka dari opsi dibawah ini!");
            System.out.println("1.Informasi Akun");
            System.out.println("2.Pemasukan");
            System.out.println("3.Pengeluaran");
            System.out.println("4.Riwayat Transaksi");
            System.out.println("5.Exit");
            System.out.print("Masukkan Angka :");
            int choice = getInt();
            switch(choice){
                case 1:
                    akun.displayAccount();
                    break;
                case 2:
                    System.out.print("Masukkan Nominal yang Akan Dimasukkan :");
                    value = getLong();
                    transaksi = new Transaction(value, "Pemasukan");
                    akun.addTransaksi(transaksi);
                    akun.saldo += value;
                    break;
                case 3:
                    System.out.print("Masukkan Nominal yang Akan Dikeluarkan :");
                    value = getLong();
                    System.out.print("Masukkan Kategori Pengeluaran :");
                    String kategori = getString();
                    transaksi = new Transaction(value, "Pengeluaran", kategori);
                    if(akun.saldo >= value){
                        akun.addTransaksi(transaksi);
                        akun.saldo -= value;
                    }else{
                        System.out.println("Saldo Tidak Cukup");
                    }
                    break;
                case 4:
                    akun.displayRiwayat();
                    break;
                case 5:
                    akun = null;
                    login = false;
                    System.out.println("Terima Kasih");
                    break;
                default:
                    System.out.println("Invalid Entry");
            }
        }
    }
    
    public static String getString() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    
    public static int getInt() throws IOException{
        String s = getString();
        return Integer.parseInt(s);
    }
    
    public static long getLong() throws IOException{
        String s = getString();
        return Long.parseLong(s);
    }
}
