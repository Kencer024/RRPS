package Assignment;

import java.util.ArrayList;

/**
 * Used to create an array of reservation as each reservation object
 * represents 30 tables, hence the time class will create an array of
 * reservation objects in accordance to the constructor parameter "month"
 * e.g. if 10 is inputted, it represents october hence an array of 31 reservation
 * objects will be created as October has 31 days
 */
public class time {
    private int month;
    private Reservation[] r = new Reservation[0];

    /** Constructor class taking in month as integer and initiating the respective
     * number of reservation object needed for the month.
     *
     * @param month An integer representing which month is it
     */
    public time(int month){
        this.month = month;
        getListfromMonth(month);
        for(int i =0; i< r.length; i++){
            r[i] = new Reservation();               //initialising each instance of reservation
        }
    }

    private void getListfromMonth(int month){        //each month have a different amount of days
        switch(month){
            case 1 :
            case 3 :
            case 5 :
            case 7 :
            case 8 :
            case 10 :
            case 12 :
                r = new Reservation[31];
                break;
            case 2 :
                r = new Reservation[28];
                break;
            case 4 :
            case 6 :
            case 9 :
            case 11 :
                r = new Reservation[30];
                break;
        }
    }

    /** Returns array of reservation based on what month has been input in
     * the constructor class
     *
     * @return Array of reservation.
     */
    public Reservation[] getR() {
        return r;
    }


}
