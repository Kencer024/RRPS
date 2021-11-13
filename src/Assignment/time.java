package Assignment;

import java.util.ArrayList;

/**
 * Used to create an array of reservation as each reservation object
 * represents 30 tables, hence the time class will create an array of
 * reservation objects in accordance to the constructor parameter "month"
 * e.g. if 10 is inputted, it represents october hence an array of 31 reservation
 * objects will be created as October has 31 days
 */

//public class time {
//    private int month;
//    private Reservation[] r = new Reservation[0];
//
//    /** Constructor class taking in month as integer and initiating the respective
//     * number of reservation object needed for the month.
//     *
//     * @param month An integer representing which month is it
//     */
//    public time(int month){
//        this.month = month;
//        getListfromMonth(month);
//        for(int i =0; i< 10; i++){                  //12pm to 10pm
//            r[i] = new Reservation();               //initialising each instance of reservation
//        }
//    }
//
//
//    }
//
//    public void changeReserveAllowed(int day){
//        for(int i =0; i<day-1;i++){
//            r[i].setIsReserveDateAllowed(false);
//        }
//    }
//
//    /** Returns array of reservation based on what month has been input in
//     * the constructor class
//     *
//     * @return Array of reservation.
//     */
//    public Reservation[] getR() {
//        return r;
//    }
//
//
//}
