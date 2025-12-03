/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

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

    public void loadToTable(DefaultTableModel model){
        Transaction current = first;
        int no = 1;
        if(current == null){
            return;
        }
        while(current != null){
            Object[] row = {
                no,
                current.nomor,
                current.type,
                "Rp " + String.format("%,d", current.nominal),
                current.category != null ? current.category : "-",
                current.date.toString()
            };
            model.addRow(row);
            no++;
            current = current.next;
        }
    }
}
