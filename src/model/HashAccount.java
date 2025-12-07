package model;

import java.io.PrintWriter;

/**
 * Class untuk hash table akun
 */
public class HashAccount {
    private LinkedAccount[] data;
    private int size;
    private int id;
    
    public HashAccount(int size) {
        this.size = size;
        data = new LinkedAccount[size];
        id = 100;
        for (int i = 0; i < size; i++) {
            data[i] = new LinkedAccount();
        }
    }
    
    public void displayHash() {
        System.out.println("Table :");
        for (int j = 0; j < size; j++) {
            System.out.print(j + "| ");
            data[j].displayList();
            System.out.println();
        }
    }
    
    public int hashFunc1(int key) {
        return key % size;
    }
    
    public Account insert(String nama, String password, long saldo) {
        ++id;
        Account newItem = new Account(id, nama, password, saldo);
        int key = newItem.getKey();
        int hashVal = hashFunc1(key);
        data[hashVal].insert(newItem);
        newItem.displayAccount();
        return newItem;
    }
    
    public void delete(int key) {
        int hashVal = hashFunc1((int) key);
        data[hashVal].delete(key);
    }
    
    public Account find(int key) {
        int hashVal = hashFunc1((int) key);
        Account theLink = data[hashVal].find(key);
        return theLink;
    }
    
    public void insertWithId(int id, String nama, String password, long saldo) {
        Account newItem = new Account(id, nama, password, saldo);
        int key = newItem.getKey();
        int hashVal = hashFunc1(key);
        data[hashVal].insert(newItem);
        if (id > this.id) {
            this.id = id;
        }
    }
    
    public void saveToFile(PrintWriter writer) {
        writer.println("========== DATA AKUN ==========");
        for (int j = 0; j < size; j++) {
            data[j].saveToFile(writer);
        }
        writer.println("==============================");
    }
    
    public Account getFirstAccount() {
        for (int j = 0; j < size; j++) {
            Account acc = data[j].getFirst();
            if (acc != null) {
                return acc;
            }
        }
        return null;
    }
    
    public Account getNextAccount(Account current) {
        if (current == null) return null;
        
        if (current.next != null) {
            return current.next;
        }
        
        int hashVal = hashFunc1(current.getKey());
        for (int j = hashVal + 1; j < size; j++) {
            Account acc = data[j].getFirst();
            if (acc != null) {
                return acc;
            }
        }
        return null;
    }
}
