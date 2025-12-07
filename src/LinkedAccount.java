/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.PrintWriter;

/**
 *
 * @author murta
 */
public class LinkedAccount {
    private Account first;
    
    public LinkedAccount(){
        first = null;
    }
    
    public void insert(Account theLink){
        int key = theLink.getKey();
        Account current = first;
        Account previous = null;
        while(current != null && key > current.getKey()){
            previous = current;
            current = current.next;
        }
        if(previous == null){
            first = theLink;
        }else{
            previous.next = theLink;
        }
        theLink.next = current;
    }
    
    public void delete(int key){
        Account previous = null;
        Account current = first;
        while(current != null && key != current.getKey()){
            previous = current;
            current = current.next;
        }
        if(current == null){
            return; // key not found
        }
        if(previous == null){
            first = first.next;
        }else{
            previous.next = current.next;
        }
    }
    
    public Account find(int key){
        Account current = first;
        while(current != null && current.getKey() <= key){
            if(current.getKey() == key){
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    public void displayList(){
        Account current = first;
        while(current != null){
            System.out.print("\t| ");
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }
    
    public Account getFirst(){
        return first;
    }
    
    public void saveToFile(PrintWriter writer){
        Account current = first;
        while(current != null){
            writer.println("ACCOUNT:" + current.id + "|" + current.nama + "|" + current.password + "|" + current.saldo);
            
            // Simpan transaksi untuk akun ini
            if(current.transaction != null){
                current.transaction.saveToFile(writer, current.id);
            }
            
            current = current.next;
        }
    }
}
