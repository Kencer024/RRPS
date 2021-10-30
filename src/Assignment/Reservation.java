package Assignment;

import java.util.Arrays;

public class Reservation {
    // private Table[] table = new Table[30];      //assume 30 tables


    // public Reservation(){
    //         for(int i =0; i< table.length;i++){
    //             table[i] = new Table(i);
    //         }
    // }

    // public void reserve(int tableId){
    //     for(int i =0; i< table.length;i++){             //brute force search
    //         if(table[i].getTableId() == tableId){       //if table[i] is tableid
    //             table[i].setReserved(true);             //set it to reserved
    //             System.out.println("Table is reserved");
    //         }
    //     }
    // }

    // public void removeReservation(int tableId){
    //     for(int i =0; i< table.length;i++){             //brute force search
    //         if(table[i].getTableId() == tableId){       //if table[i] is tableid
    //             table[i].setReserved(false);             //set it to !reserved
    //         }
    //     }
    // }

    // public boolean checkReservation(int tableId){       //checks whether a certain table is reserved
    //     for(int i =0; i< table.length;i++){             //brute force search
    //         if(table[i].getTableId() == tableId){       //if table[i] is tableid
    //             return table[i].getreserved();             //set it to !reserved
    //         }
    //         else{
    //             System.out.println("Table doesnt exist");
    //         }
    //     }return false;
    // }

    // public void updateReservation(int tableId, int tableId2){
    //     for(int i =0; i< table.length;i++){             //brute force search
    //         if(table[i].getTableId() == tableId) {       //if table[i] is tableid
    //                 table[i].setReserved(false);
    //         }
    //         if(table[i].getTableId() == tableId2){
    //             table[i].setReserved(true);
    //         }
    //     }
    // }

    // public Table[] checkEmptyTable(){
    //     Table[] eTable = new Table[0];
    //     for(int i =0;i< table.length; i++){
    //         if(table[i].getreserved() == false){
    //             System.out.println(table[i].getTableId() + " is empty ");
    //             eTable = Arrays.copyOf(eTable, eTable.length+1);        //append eTable array
    //             eTable[eTable.length-1] = table[i];
    //         }
    //     }
    //     return eTable;
    // }


}
