/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.PrintWriter;

/**
 *
 * @author murta
 */
public class HashAccount {
    private LinkedAccount [] data;
    private int size;
    private int id;
    
    public HashAccount(int size){
        this.size = size;
        data = new LinkedAccount[size];
        id = 100;
        for(int i = 0;i < size;i++){
            data[i] = new LinkedAccount();
        }
    }
    
    // public void displayHash(){
    //     System.out.println("Table :");
    //     for(int j = 0;j < size;j++){
    //         System.out.print(j+"| ");
    //         data[j].displayList();
    //         System.out.println();
    //     }
    // }
    
    public int hashFunc1(int key){
        return key % size;
    }
    
    public void insert(String nama,String password,long saldo){
        ++id;
        Account newItem = new Account(id,nama,password, saldo);
        int key = newItem.getKey();
        int hashVal = hashFunc1(key);
        data[hashVal].insert(newItem);
        newItem.displayAccount();
    }
    
    // public void delete(int key){
    //     int hashVal = hashFunc1((int) key);
    //     data[hashVal].delete(key);
    // }
    
    public Account find(int key){
        int hashVal = hashFunc1((int) key);
        Account theLink = data[hashVal].find(key);
        return theLink;
    }
    
    public void insertWithId(int id, String nama, String password, long saldo){
        Account newItem = new Account(id, nama, password, saldo);
        int key = newItem.getKey();
        int hashVal = hashFunc1(key);
        data[hashVal].insert(newItem);
        if(id > this.id){
            this.id = id;
        }
    }
    
    public void saveToFile(PrintWriter writer){
        writer.println("========== DATA AKUN ==========");
        for(int j = 0; j < size; j++){
            data[j].saveToFile(writer);
        }
        writer.println("==============================");
    }
}
