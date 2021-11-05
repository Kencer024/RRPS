package Assignment;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainPage {
    public static void main (String [] args) {

        Scanner sc = new Scanner(System.in);

        //ArrayList<order> order = new ArrayList<order>();
        //ArrayList<ReserveDate> reserve = new ArrayList<ReserveDate>();
        //ReserveDate[] reserve = new ReserveDate[30];

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH); //JAN =0
        //System.out.println(month);
        ReserveDate reserveDate = new ReserveDate(month);

        int choice = 0; //userInputOfChoice
        int reservationTableId = 0;
        int newReservationTableId = 0;
        int noOfPax = 0;
        int reservationTime = 0;
        String reservationName = "";
        int reservationDate = 0;
        int checkReservationChoice;
        boolean BooleanReservationChoice;

        do {
            System.out.printf("|========================================================|\n"
                    + "|                         MENU                           |\n"
                    + "|========================================================|\n"
                    + "||     1: Order Category                               ||\n"
                    + "||     2: Reservation Category                          ||\n"
                    + "||     3: Quit                                          ||\n"
                    + "|========================================================|\n");

            choice = sc.nextInt();

            if(choice==1) { //Order
                int orderChoice = 0;
                do {
                    System.out.printf("|========================================================|\n"
                            + "|                         MENU                           |\n"
                            + "|========================================================|\n"
                            + "||     1: Add to cart.                                  ||\n"
                            + "||     2: Remove from cart.                             ||\n"
                            + "||     3: Display Menu.                                 ||\n"
                            + "||     4: Retrieve Order Info.                          ||\n"
                            + "||     5: Checkout.                                     ||\n"
                            + "||     6: Exit.                                         ||\n"
                            + "|========================================================|\n");
                    System.out.print("Enter your action: ");

                    orderChoice = sc.nextInt();

                    switch(orderChoice){
                        case 1://add to cart
                            System.out.println("============================== ADD TO CART ==============================");
                            System.out.println(" ");
                            break;
                        case 2: //remove from cart
                            break;
                        case 3: //Display menu
                            break;
                        case 4: //Retrieve Order Info
                            break;
                        case 5: //Checkout
                            break;
                    }

                } while(orderChoice!=6);
            }

            else if (choice==2) { //Reservation
                int reservationChoice = 0;
                do {
                    System.out.printf("|========================================================|\n"
                            + "|                         MENU                           |\n"
                            + "|========================================================|\n"
                            + "||     1: Reserve Table.                                 ||\n"
                            + "||     2: Remove Reservation.                            ||\n"
                            + "||     3: Check Tables.                                  ||\n"
                            + "||     4: Update Reservation Info.                       ||\n"
                            + "||     5: Exit.                                          ||\n"
                            + "|========================================================|\n");
                    System.out.print("Enter your action: ");

                    reservationChoice = sc.nextInt();

                    switch(reservationChoice){
                        case 1://Reserve Table
                            System.out.println("============================== RESERVE TABLE ==============================");
                            System.out.println("Enter Reservation Date: ");
                            reservationDate = sc.nextInt();
                            System.out.println("Enter Reservation Time: ");
                            reservationTime = sc.nextInt();
                            System.out.println("Enter No. Of Pax: ");
                            noOfPax = sc.nextInt();
                            System.out.println("Enter Name: ");
                            reservationName = sc.next();
                            reserveDate.reserveR(reservationTime, reservationDate, noOfPax, reservationName); //time date, pax, name
                            break;

                        case 2: //Remove Reservation
                            System.out.println("============================== REMOVE RESERVATION ==============================");
                            System.out.println("Enter Reservation Date: ");
                            reservationDate = sc.nextInt();
                            System.out.println("Enter Reservation Time: ");
                            reservationTime = sc.nextInt();
                            System.out.println("Enter Table Id: "); //should check by name instead
                            reservationTableId = sc.nextInt();
                            reserveDate.removeReserveR(reservationTime, reservationDate, reservationTableId); //time, date, table
                            break;

                        case 3: //Check Reservation
                            System.out.println("============================== CHECK TABLES ==============================");
                            System.out.println(
                                    "|==================================|\n"
                                  + "||  1: Check for Vacant Table       ||\n"
                                  + "||  2: Check for Reserved Table      ||\n");

                            checkReservationChoice = sc.nextInt();
                            if (checkReservationChoice == 1){
                                BooleanReservationChoice = true;
                            }
                            else{
                                BooleanReservationChoice = false;
                            }

                            System.out.println(checkReservationChoice);
                            System.out.println("Enter Reservation Date: ");
                            reservationDate = sc.nextInt();
                            System.out.println("Enter Reservation Time: ");
                            reservationTime = sc.nextInt();
                            System.out.println("Enter No. Of Pax: ");
                            noOfPax = sc.nextInt();
                            reserveDate.checkReservation(BooleanReservationChoice,reservationTime, reservationDate, noOfPax); //choice, time, date, pax
                            break;

                        case 4: //Update Reservation Info
                            System.out.println("============================== RESERVATION TABLE ==============================");

                            reservationTableId = sc.nextInt();
                            System.out.println("Enter New Table Id: ");
                            newReservationTableId = sc.nextInt();
                            reserveDate.updateReservation(reservationTableId, newReservationTableId); //time, date, pax, table
                            break;

                        case 5: //Exit
                            break;
                    }

                } while(reservationChoice!=5);
            }

            else if (choice==3){
                System.out.println("You have ended the program");
            }

            else {
                System.out.println("Please enter a valid input");
            }
        }

        while (choice!=3);


    }
}
