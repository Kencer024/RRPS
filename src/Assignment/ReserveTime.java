package Assignment;

import java.util.*;
import java.time.LocalDate; // import the LocalDate class

// do the auto removal by making those past the period null

/** The class represents a reservation day with 10 reservation objects
 * each object represents a specific time slot with index 0 being 12pm
 * and index 9 being 9pm, the shop is assumed to be closed after 9
 *
 */
public class ReserveTime {
    /**
     * r represents each time slot i.e. 1 for 1pm, 0 for 12pm etc
     */
    private Reservation[] r = new Reservation[10];            //0 for 12pm, 1 for 1pm etc until 10pm


    public ReserveTime(){
        for(int i =0; i< 10; i++){                  //12pm to 10pm
            r[i] = new Reservation();               //initialising each instance of reservation
        }
    }

    public Reservation[] getList(){
        return r;
    }

    /** sets all the reservation ability timeslots to false, also changes all the table name to null
     *
     */
    public void removeDateReservation(){
        for(int i=0;i<r.length;i++){
            r[i].setIsReserveDateAllowed(false);
            for(int j=0;j<r[i].getTable().length;j++){
                r[i].getTable()[j].setName(null);       //remove name
            }
        }
    }

}