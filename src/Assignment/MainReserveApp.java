package Assignment;// package Assignment;


import java.util.Scanner;

/** A class to test the reservation part of the assignment
 *
 */
public class MainReserveApp {

    public static void main(String[] args) {
	// write your code here
    Scanner sc = new Scanner(System.in);

        /**
         * ReserveDate(Current Month,date,time) when doing the reservation, NOT the
         * date and time that u want to reserve
         * i.e. it's now 6th October, 7pm and you want to reserve on 10 October 4pm
         * then its ReserveDate(10,6,7) but reserveR(4,10,pax,name)
         * note that the date and time of both classes and methods are different
         */
    ReserveDate reserveDate = new ReserveDate(10,6,7);
    while(true) {
        System.out.println("Choose options");
        System.out.println("0 for  reservation\n" +
                "1 for remove reservation\n" +
                "2 for check reservation\n" +
                "3 for update reservation\n" +
                "4 for checking current Table reservation");
        int choice = sc.nextInt();
        switch(choice){
            case 0 :
                System.out.println("Date :");
                int date = sc.nextInt();
                System.out.println("Time :");
                int time = sc.nextInt();
                System.out.println("Name :");
                String name = sc.next();
                System.out.println("Pax :");
                int pax = sc.nextInt();
                reserveDate.reserveR(time, date, pax, name); break;

            case 1 :
                System.out.println("Date :");
                date = sc.nextInt();
                System.out.println("Time :");
                time = sc.nextInt();
                System.out.println("Table number :");
                int tableId = sc.nextInt();
                reserveDate.removeReserveR(time,date,tableId, true);break;

            case 2 :
                System.out.println("Date :");
                date = sc.nextInt();
                System.out.println("Time :");
                time = sc.nextInt();
                System.out.println("Choose 0 for empty, 1 for reserved");
                int select = sc.nextInt();
                if(select == 0){
                    System.out.println("Input number of pax :");
                    pax = sc.nextInt();
                    reserveDate.checkReservation(true,time,date,pax);
                }
                else if(select == 1){
                    reserveDate.checkReservation(false,time,date,-1);
                }
                else{
                    System.out.println("Invalid choice");
                }
                break;

            case 3 :
                System.out.println("Date :");
                date = sc.nextInt();
                System.out.println("Time :");
                time = sc.nextInt();
                System.out.println("Table number :");
                tableId = sc.nextInt();
                System.out.println("Input number of pax :");
                pax = sc.nextInt();
                reserveDate.updateReservation(time,date,pax,tableId); break;

            case 4 :
                // Assuming the current month, date and time is 10,6,7
                System.out.println("TableId(0 to 29) : ?");
                tableId = sc.nextInt();
                boolean reserved;
                reserved = reserveDate.checkTableReservation(6,7,tableId);
                System.out.println("Is table reserved : " + reserved);
                if(!reserved){
                    System.out.println("Number of pax : ");
                    pax = sc.nextInt();
                    reserveDate.checkReservation(true,7,6,pax);
                }
                break;

            default:break;
        }


    }
    }
}
