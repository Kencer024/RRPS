package Assignment;

import java.util.*;
import java.time.LocalDate; // import the LocalDate class

public class ReserveDate {
    private time[] t = new time[10];            //0 for 12pm, 1 for 1pm etc until 10pm

    public ReserveDate(int month){
        for(int i=0;i<t.length;i++){
            t[i] = new time(month);
        }
    }

    public time[] getList(){
        return t;
    }

    public void reserveR(int time, int date, int pax){
        Reservation[] r = t[time].getR();
        r[date-1].reserve(pax);             //cos date starts from 0

    }

    public void removeReserveR(int time, int date, int table){
        Reservation[] r = t[time].getR();
        int tableId = r[date-1].getTable()[table].getTableId();
        r[date-1].removeReservation(tableId);             //cos date starts from 0

    }

    public void checkReservation(boolean choice, int time, int date, int pax){
        Reservation[] r = t[time].getR();
        if(choice){                                     //check empty table
            r[date-1].checkEmptyTable(pax);             //cos date starts from 0
        }
        else{
            r[date-1].checkReservedTable();
        }
    }

    public void updateReservation(int time, int date, int pax, int table){
        Reservation[] r = t[time].getR();
        int tableId = r[date-1].getTable()[table].getTableId();
        r[date-1].updateReservation(tableId, pax);
    }
}