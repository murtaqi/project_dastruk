/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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

    public int displayTableRows(int startNo){
        Account current = first;
        int no = startNo;
        while(current != null){
            if(current.id == 100){
                current = current.next;
                continue;
            }
            System.out.print(no+"\t|");
            System.out.print("\t"+current.id+"\t|");
            System.out.print("\t"+current.nama+"\t|");
            System.out.print(current.saldo);
            System.out.println();
            no++;
            current = current.next;
        }
        return no;
    }
}
