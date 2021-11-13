package Assignment;

import java.util.Arrays;
import java.util.Scanner;

/*
* Change the number of pax per table at the constructor
* Reservation for the individual slot(1 hr)
* Each slot there are 30 tables, everyone is assumed to reserve
* only for 1 hour
* */

/**
 * Represents the reservations available per timeslot e.g. 1pm has 30 tables
 */
public class Reservation {
    private Table[] table = new Table[30];      //assume 30 tables
    private boolean reserveDateAllowed = true;         //boolean to see whether its eligible for reservation

    Scanner sc = new Scanner(System.in);

    /**
     * Constructor to set all tables to be unreserved, and give table identity
     * as its index. It also sets the maximum pax of each table, example table 0 to 10
     * has a maximum pax of 4 etc
     */
    public Reservation(){
            for(int i =0; i< table.length;i++){
                table[i] = new Table(i);
                table[i].setTableId(i);
                table[i].setReserved(false);

                // This is where the tables are set
                if(i < 10){                     //1st 10 tables 4 pax
                    table[i].setPax(4);

                }
                else if(i<20){                  //2nd 10 tables 6 pax
                    table[i].setPax(6);
                }
                else
                    table[i].setPax(8);
            }
    }

    /** Displays all empty tables that are more than or equals to
     * the pax specified. It then reserves the table based on the name
     *
     * @param pax An integer representing the number of pax to book
     * @param name A string representing the name of the person that would be
     *             reserving the table
     */
    public void reserve(int pax, String name){
        checkEmptyTable(pax);

        boolean doesTableExist = false;
        System.out.println("Enter choice of Table : ");
        int tableId = sc.nextInt();
        for(int i =0; i< table.length;i++){             //brute force search
            if(table[i].getTableId() == tableId && !table[i].getReserved()) {       //if table[i] is tableid
                table[i].setReserved(true);             //set it to reserved
                table[i].setName(name);
                System.out.println("Table is reserved");
                doesTableExist = true;

            }
        }if(!doesTableExist){
            System.out.println("Cannot book table");
        }

    }

    /** Removes reservation based on the tableId by searching through the array
     * to find its id then setting all its values like reservation status to false and
     * name to null
     *
     * @param tableId An integer representing the table identity, it could also be
     *
     */
    public void removeReservation(int tableId){
        for(int i =0; i< table.length;i++){             //brute force search
            if(table[i].getTableId() == tableId){       //if table[i] is tableid
                if(table[i].getName() != null){         //if a reservation is found
                    table[i].setReserved(false);        //set it to !reserved
                    table[i].setName(null);
                    System.out.println("Table reservation removed");
                }else{
                    System.out.println("No reservation found");
                }

            }
        }

    }

    /** Checks the status of the table whether it is reserved.
     *
     * @param tableId An integer representing the identity of the table
     * @return A boolean where true means it is reserved and false means
     *            it is not
     */
    public boolean checkReservation(int tableId){       //checks whether a certain table is reserved
        for(int i =0; i< table.length;i++){             //brute force search
            if(table[i].getTableId() == tableId){       //if table[i] is tableid
                return table[i].getReserved();             //set it to !reserved
            }
            else{
                System.out.println("Table doesn't exist");
            }
        }return false;
    }

    /** Updates the reservation, for example, change in date or time or increase
     * number of pax
     *
     * @param tableId An integer representing the identity of the table
     * @param pax An integer representing the new number of pax required
     *            e.g. originally reserved for 2 pax but now wants 8pax
     *            hence the parameter takes in 8
     */
    public void updateReservation(int tableId, int pax){    //change table not change time
        String Name;
        int i;
        for(i =0; i< table.length;i++) {             //brute force search
            if (table[i].getTableId() == tableId) {       //if table[i] is tableid
                Name = table[i].getName();
                removeReservation(i);
                reserve(pax,Name);
                return;
            }
        }
        System.out.println("Table updated");
    }

    /** Checks for empty table in accordance to the number of pax required
     * e.g. if it requires 3 pax, the function will display all tables with
     * attribute pax of 3 or more
     *
     * @param pax An integer representing the number of pax required
     * @return Array of tables that are empty with attribute pax more than or
     * equal to the pax defined
     */
    public Table[] checkEmptyTable(int pax){
        Table[] eTable = new Table[0];
        for(int i =0;i< table.length; i++){
            if(!table[i].getReserved() && table[i].getPax() >= pax
            && this.getIsReserveDateAllowed()){
                System.out.println(table[i].getTableId() + "["
                            + table[i].getPax() +"pax] is empty");
                eTable = Arrays.copyOf(eTable, eTable.length+1);        //append eTable array
                eTable[eTable.length-1] = table[i];
            }

        }
        if(!this.getIsReserveDateAllowed()){
            System.out.println("Reservation for this slot is over");
        }
        return eTable;
    }

    /** Checks all the tables that are reserved and not past the date, it will print it out
     *
     */
    public void checkReservedTable(){
        if(!this.getIsReserveDateAllowed()){
            System.out.println("Reservation for this date is over");
            return;
        }
        for(int i =0;i<table.length;i++){
            if(table[i].getReserved() && this.getIsReserveDateAllowed()
            && this.getIsReserveDateAllowed()){
                System.out.println("table " + i + " is reserved for " + table[i].getName() );
                return;
            }

        }

        System.out.println("No reservations");
    }

    /** Gets the array of table because the reservation class has
     * an array of table
     *
     * @return An array of table to represent the total number of table per
     * reservation slot
     */
    public Table[] getTable(){
        return table;
    }

    /** Returns the status of reservation of the table
     *
     * @return A boolean representing whether the table is allowed to be reserved
     */
    public boolean getIsReserveDateAllowed() {
        return reserveDateAllowed;
    }

    /** A method to set the reservations for that specific time to be false/true
     *
     * @param allowed A boolean representing whether reservation is allowed for that timeslot
     */
    public void setIsReserveDateAllowed(boolean allowed){
        reserveDateAllowed = allowed;
    }
}
