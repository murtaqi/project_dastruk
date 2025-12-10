package model;

import java.io.*;
import java.time.LocalDate;

/**
 * Class untuk menangani penyimpanan dan pemuatan data akun ke file txt
 */
public class DataStorage {
    private static final String DATA_FILE = "akun_data.txt";
    
    /**
     * Menyimpan semua akun ke file txt
     */
    public static void saveAllAccounts(HashAccount ha) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            ha.saveToFile(writer);
            System.out.println("Data berhasil disimpan ke " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Error menyimpan data: " + e.getMessage());
        }
    }
    
    /**
     * Memuat semua akun dari file txt
     */
    public static void loadAllAccounts(HashAccount ha) {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("File data tidak ditemukan. Memulai dengan akun kosong.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("=")) {
                    continue;
                }
                
                // Parse account line
                if (line.startsWith("ACCOUNT:")) {
                    String[] parts = line.substring(8).split("\\|");
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0]);
                        String nama = parts[1];
                        String password = parts[2];
                        long saldo = Long.parseLong(parts[3]);
                        
                        // Insert account preserving original id
                        ha.insertWithId(id, nama, password, saldo);
                    }
                }
                // Parse transaction lines
                else if (line.startsWith("TRANS:")) {
                    String[] parts = line.substring(6).split("\\|");
                    if (parts.length >= 5) {
                        int accountId = Integer.parseInt(parts[0]);
                        String nomor = parts[1];
                        String type = parts[2];
                        long nominal = Long.parseLong(parts[3]);
                        LocalDate date = LocalDate.parse(parts[4]);
                        
                        Transaction trans = new Transaction(nominal, type);
                        trans.nomor = nomor;
                        trans.date = date;
                        
                        if (parts.length >= 6) {
                            trans.category = parts[5];
                        }
                        
                        Account acc = ha.find(accountId);
                        if (acc != null) {
                            acc.addTransaksiAtTail(trans);
                        } else {
                            System.out.println("Warning: account id " + accountId + " tidak ditemukan saat memuat transaksi " + nomor);
                        }
                    }
                }
            }
            System.out.println("Data berhasil dimuat dari " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Error memuat data: " + e.getMessage());
        }
    }
}
