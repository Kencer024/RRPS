package Assignment;

import java.util.ArrayList;

public class time {
    private int month;
    private Reservation[] r = new Reservation[0];

    public time(int month){
        this.month = month;
        getListfromMonth(month);
        for(int i =0; i< r.length; i++){
            r[i] = new Reservation();               //initialising each instance of reservation
        }
    }

    public void getListfromMonth(int month){        //each month have a different amount of days
        switch(month){
            case 1 :
            case 3 :
            case 5 :
            case 7 :
            case 8 :
            case 10 :
            case 12 :
                r = new Reservation[30];
                break;
            case 2 :
                r = new Reservation[27];
                break;
            case 4 :
            case 6 :
            case 9 :
            case 11 :
                r = new Reservation[29];
                break;
        }
    }

    public Reservation[] getR() {
        return r;
    }


}
