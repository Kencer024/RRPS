package Assignment;

import java.util.Arrays;
import java.util.Scanner;

public class Reservation {
    private Table[] table = new Table[30];      //assume 30 tables
    Scanner sc = new Scanner(System.in);

    public Reservation(){
            for(int i =0; i< table.length;i++){
                table[i] = new Table(i);
                table[i].setTableId(i);
                table[i].setReserved(false);
                if(i < 10){                     //1st 10 tables 2 pax
                    table[i].setPax(4);

                }
                else if(i<20){                  //2nd 10 tables 4 pax
                    table[i].setPax(6);
                }
                else
                    table[i].setPax(8);
            }
    }


    public void reserve(int pax){
        checkEmptyTable(pax);
        boolean doesTableExist = false;
        System.out.println("Enter choice of Table : ");
        int tableId = sc.nextInt();
        for(int i =0; i< table.length;i++){             //brute force search
            if(table[i].getTableId() == tableId && table[i].getreserved() == false) {       //if table[i] is tableid
                table[i].setReserved(true);             //set it to reserved
                System.out.println("Table is reserved");
                doesTableExist = true;
            }
        }if(doesTableExist == false){
            System.out.println("Table does not exist");
        }

    }

    public void removeReservation(int tableId){
        for(int i =0; i< table.length;i++){             //brute force search
            if(table[i].getTableId() == tableId){       //if table[i] is tableid
                table[i].setReserved(false);             //set it to !reserved
            }
        }
    }

    public boolean checkReservation(int tableId){       //checks whether a certain table is reserved
        for(int i =0; i< table.length;i++){             //brute force search
            if(table[i].getTableId() == tableId){       //if table[i] is tableid
                return table[i].getreserved();             //set it to !reserved
            }
            else{
                System.out.println("Table doesnt exist");
            }
        }return false;
    }

    public void updateReservation(int tableId, int pax){
        checkEmptyTable(pax);
        System.out.println("Enter choice of Table : ");
        int tableId2 = sc.nextInt();
        for(int i =0; i< table.length;i++){             //brute force search
            if(table[i].getTableId() == tableId) {       //if table[i] is tableid
                    table[i].setReserved(false);
            }
            if(table[i].getTableId() == tableId2){
                table[i].setReserved(true);
            }
        }
    }

    public Table[] checkEmptyTable(int pax){
        Table[] eTable = new Table[0];
        for(int i =0;i< table.length; i++){
            if(table[i].getreserved() == false && table[i].getpax() >= pax){
                System.out.println(table[i].getTableId() + " is empty ");
                eTable = Arrays.copyOf(eTable, eTable.length+1);        //append eTable array
                eTable[eTable.length-1] = table[i];
            }
        }
        return eTable;
    }

    public void checkReservedTable(){
        for(int i =0;i<table.length;i++){
            if(table[i].getreserved()== true){
                System.out.println("table " + i + " is reserved");
            }
        }
    }

    public Table[] getTable(){
        return table;
    }


}
