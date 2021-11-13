package Assignment;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainPage {
    public static void main (String [] args) {

        /*


        //If remove successfully, no print statement
        //If there is no reservation, no print statement


         */

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

        int choice; //userInputOfChoice

        int reservationChoice;
        int reservationTableId;
        int newReservationTableId;
        int noOfPax;
        int reservationTime;
        String reservationName;
        int reservationDate;
        int checkReservationChoice;
        boolean BooleanReservationChoice;

        int orderChoice;

        MenuList menu = new MenuList();

        // For Order
        String newOrderId, orderId;
        int newOrderPax, orderIndex;
        Boolean proceedToOrder;

        OrderDatabase orders = new OrderDatabase();


        do {
            System.out.print("|========================================================|\n"
                    + "|                         MENU                           |\n"
                    + "|========================================================|\n"
                    + "||     1: Order Category                                ||\n"
                    + "||     2: Reservation Category                          ||\n"
                    + "||     3: Edit Menu Category                            ||\n"
                    + "||     4: Quit                                          ||\n"
                    + "|========================================================|\n");

            choice = sc.nextInt();

            if(choice==1) { //Order
                do {
                    System.out.print("|========================================================|\n"
                            + "|                         MENU                           |\n"
                            + "|========================================================|\n"
                            + "||     1: Create New Order                              ||\n"
                            + "||     2: Manage an Order                               ||\n"
                            + "||     3: Display All Current Orders                    ||\n"
                            + "||     4: Remove Order                                  ||\n"
                            + "||     5: Exit.                                         ||\n"
                            + "|========================================================|\n");
                    System.out.print("Enter your action: ");

                    choice = sc.nextInt();

                    proceedToOrder = false;
                    if(choice==1) {
                        System.out.println("============================== Create New Order ==============================");
                        System.out.println("Enter Table ID: ");
                        newOrderId = sc.next();
                        System.out.println("Enter #pax: ");
                        newOrderPax = sc.nextInt();
                        orders.newOrder(newOrderId, newOrderPax);
                        orderIndex = orders.getOrderIndex(newOrderId);
                        proceedToOrder = true;
                    }
                    else if(choice==2) {
                        System.out.println("============================== Remove Order ==============================");
                        orders.printCurrentOrders();
                        System.out.println("Enter Table ID: ");
                        orderId = sc.next();
                        orderIndex = orders.getOrderIndex(orderId);
                        proceedToOrder = true;
                    }
                    else if(choice==3) {
                        System.out.println("============================== Remove Order ==============================");
                        orders.printCurrentOrders();
                    }
                    else if(choice==4) {

                    }
                    System.out.print("|========================================================|\n"
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
                do {
                    System.out.print("|========================================================|\n"
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
                            System.out.println("===Enter Reservation Time===\n"
                                            + "0: 12pm \n"
                                            + "1: 1pm \n"
                                            + "2: 2pm and so on till 10pm");//0 for 12pm, 1 for 1pm etc until 10pm
                            reservationTime = sc.nextInt();
                            System.out.println("Enter Table Id: "); //should check by name instead
                            reservationTableId = sc.nextInt();
                            reserveDate.removeReserveR(reservationTime, reservationDate, reservationTableId); //time, date, table
                            break;

                        case 3: //Check Reservation
                            System.out.println("============================== CHECK TABLES ==============================");
                            System.out.println(
                                    "|==================================|\n"
                                  + "||  1: Check for Vacant Table      ||\n"
                                  + "||  2: Check for Reserved Table    ||");
                            checkReservationChoice = sc.nextInt();
                            BooleanReservationChoice = checkReservationChoice == 1; //True if 1
                            //System.out.println(checkReservationChoice);
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
                            System.out.println("Enter Reservation Date: ");
                            reservationDate = sc.nextInt();
                            System.out.println("Enter Reservation Time: ");
                            reservationTime = sc.nextInt();
                            System.out.println("Enter No. Of Pax: ");
                            noOfPax = sc.nextInt();
                            System.out.println("Enter New Table Id: ");
                            newReservationTableId = sc.nextInt();
                            reserveDate.updateReservation(reservationTime, reservationDate, noOfPax, newReservationTableId); //time, date, pax, table
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
