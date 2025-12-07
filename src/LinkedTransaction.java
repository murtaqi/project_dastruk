/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;
import java.io.PrintWriter;

/**
 *
 * @author murta
 */
public class LinkedTransaction {
    private Transaction first;
    
    public LinkedTransaction(){
        first = null;
    }
    
    public void insert(Transaction newTrans){
        newTrans.next = first;
        first = newTrans;
    }
    
    public Transaction find(String key){
        Transaction current = first;
        while(current != null){
            if(current.nomor.equals(key)){
                return current;
            }else{
                current = current.next;
            }
        }
        return null;
    }
    
    public Transaction find(LocalDate key){
        Transaction current = first;
        while(current != null){
            if(current.date.equals(key)){
                return current;
            }else{
                current = current.next;
            }
        }
        return null;
    }
    
    public void displayList(){
        Transaction current = first;
        if(current == null){
            System.out.println("Belum Ada Transaksi");
            return;
        }
        while(current != null){
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }
    
    public void saveToFile(PrintWriter writer, int accountId){
        Transaction current = first;
        while(current != null){
            String category = (current.category != null) ? current.category : "";
            writer.println("TRANS:" + accountId + "|" + current.nomor + "|" + current.type + "|" + current.nominal + "|" + current.date + "|" + category);
            current = current.next;
        }
    }
}
