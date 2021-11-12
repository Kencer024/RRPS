package Assignment;

import java.util.*;
import java.time.LocalDate; // import the LocalDate class

// do the auto removal by making those past the period null

/** Reserve according to date
 *
 */
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

    public void reserveR(int time, int date, int pax, String Name){
        Reservation[] r = t[time].getR();
        r[date-1].reserve(pax,Name);             //cos date starts from 0

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

    /*  time is the original time slot
    *   date is the original date slot
    *   pax is the NEW pax wanted
    *   table is the original table index
    * */
    public void updateReservation(int time, int date, int pax, int table){
        Reservation[] r = t[time].getR();
        Scanner sc = new Scanner(System.in);

        int tableId = r[date-1].getTable()[table].getTableId();
        System.out.println( "Change Time/Date ? Your current reservation is table " + table + " at "
                + time+ " for "+r[date-1].getTable()[table].getName());
        System.out.println(
                "0 for just change table/pax\n" +
                "1 for change time/date");
        int choice = sc.nextInt();
        switch(choice){
            case 0 :
                r[date-1].updateReservation(tableId, pax);
            case 1 :
                String name = r[date-1].getTable()[table].getName();
                r[date-1].removeReservation(table);
                System.out.println("Which date ?");
                int date2 = sc.nextInt();
                System.out.println("What time ?");
                int time2 = sc.nextInt();
                Reservation[] r2 = t[time2].getR();

                r2[date2-1].reserve(pax, name);

        }
        System.out.println("Table updated");
    }
}