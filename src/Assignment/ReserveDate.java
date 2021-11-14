package Assignment;

import java.util.Scanner;

public class ReserveDate {
    private int month;
    private ReserveTime[] t = new ReserveTime[0];


    /** Constructor to create array of reservation day slots for each timeslot
     * It is also to check whether the time now has expired
     *
     * @param month An integer representing the current month now
     * @param date An integer representing the current date now
     * @param time An integer representing the current time now
     */
    ReserveDate(int month, int date,int time){
        this.month = month;
        getListfromMonth(month);
        for(int k=0; k<t.length;k++){
            t[k] = new ReserveTime();
        }
        for(int i=0;i<date-1;i++){                    //from day 0 to the day before date selected
            t[i].removeDateReservation();           //removes all reservation from that day
        }
        for(int j=0;j<time;j++){                    //on the date selected remove all timings before the selected one
            t[date-1].getList()[j].setIsReserveDateAllowed(false);
        }
    }

    /** The main method to reserve table using date, time, pax and Name
     *
     * @param time An integer representing the time the customer would like to reserve
     * @param date An integer representing the date that the customer would like to reserve
     * @param pax An integer representing the number of pax that would be reserved
     * @param Name A string representing the name of the customer reserving
     */
    public int reserveR(int time, int date, int pax, String Name){
        Reservation[] r = t[date-1].getList();
        if(r[time].getIsReserveDateAllowed()){     //checks whether the date has passed
            int tableId = r[time].reserve(pax,Name);             //cos date starts from 0
            return tableId;
        }
        else{
            System.out.println("Reservation slot has passed choose another date or time");
            return -1;
        }


    }

    /** As each month has a different number of days, this method would create an array of different lengths
     * based on the month that was input
     * i.e. July is "7" and July has 31 days hence this method would create an array of 31 reserveTime objects
     *
     * @param month An integer representing the current month
     */
    private void getListfromMonth(int month) {        //each month have a different amount of days
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                t = new ReserveTime[31];
                break;
            case 2:
                t = new ReserveTime[28];
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                t = new ReserveTime[30];
                break;
        }
    }

        /**
         * Removes reservation base on date, time and table
         *
         * @param time An integer that represents the time that the customer booked
         *             0 means 12pm and 9 means 9pm (after 9pm it's not allowed)
         * @param date An integer that represents a date that the customer booked
         *             1 means 1st day while 31 means the 31st day
         * @param table An integer representing the table that the staff will remove reservation from
         */
        public void removeReserveR(int time, int date, int table, boolean printResult){
            Reservation[] r = t[date-1].getList();
            int tableId = r[time].getTable()[table].getTableId();
            if(r[time].getTable()[tableId].getReserved())
                r[time].removeReservation(tableId, printResult);             //cos date starts from 0
            else{
                if(printResult)
                    System.out.println("It is not reserved");
            }
        }

        /**
         * Removes reservation base on date, time and table
         *
         * @param time An integer that represents the time that the customer booked
         *             0 means 12pm and 10 means 10pm
         * @param date An integer that represents a date that the customer booked
         *             1 means 1st day while 31 means the 31st day
         * @param choice A boolean to choose whether to see the empty tables or reserved table
         *               true checks for empty tables and false checks for reserved tables
         * @param pax An integer representing the number of pax for the NEW reserved table
         */
        public void checkReservation(boolean choice, int time,int date, int pax){
            Reservation[] r = t[date-1].getList();
            if(choice){                                     //check empty table
                r[time].checkEmptyTable(pax);             //cos date starts from 0
            }
            else{
                r[time].checkReservedTable();
            }
        }

        /**  time is the original time slot
         *   date is the original date slot
         *   pax is the new pax wanted, for example, the customer used to require
         *   table is the original table index
         */


    /** A method to update the reservation, could update to a new date/time or just increase
     * the pax, so they have to change to another table
     *
     * @param date An integer representing the original reservation date slot
     * @param time An integer representing the original reservation time slot
     * @param pax An integer representing the new pax required, e.g. the customer
     *            might have originally requested for only 2pax but changed their
     *            mind to 6 pax hence input 6
     * @param table An integer representing the original table booked
     */
    public void updateReservation(int time,int date, int pax, int table){
            Reservation[] r = t[date-1].getList();
            Scanner sc = new Scanner(System.in);

            int tableId = r[time].getTable()[table].getTableId();
            if(r[time].getTable()[table].getReserved()){
                System.out.println( "Change Time/Date ? Your current reservation is table " + table + " at "
                        + (time)+ "pm for "+r[time].getTable()[table].getName());
                System.out.println(
                        "0 for just change table/pax\n" +
                                "1 for change time/date");
                int choice = sc.nextInt();
                switch(choice){
                    case 0 :
                        r[time].updateReservation(tableId, pax);break;
                    case 1 :
                        String name = r[time].getTable()[table].getName();
                        r[time].removeReservation(table, true);
                        System.out.println("Which date ?");
                        int date2 = sc.nextInt();
                        System.out.println("What time ?");
                        int time2 = sc.nextInt();
                        Reservation[] r2 = t[date2-1].getList();
                        r2[time2].reserve(pax, name);break;

                }
                System.out.println("Table updated");
            }
            else
                System.out.println("Reservation doesn't exist");
            }

    /** Checking reservation for a specific table currently
     *
     * @param date An integer representing current date
     * @param time An integer representing current time
     * @param table An integer representing original tableId
     * @return
     */
    public boolean checkTableReservation(  int date,int time,int table){
        Reservation[] r = t[date-1].getList();
        if(r[time].getTable()[table].getReserved()){
            return true;
        }
        else return false;

    }


    }
