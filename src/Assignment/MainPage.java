package Assignment;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

public class MainPage {
    public static void main (String args[]) {

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
        int time = cal.get(Calendar.HOUR); //JAN =0
        int day = cal.get(Calendar.DATE); //JAN =0
        int month = cal.get(Calendar.MONTH); //JAN =0
        //System.out.println(month);
        ReserveDate reserveDate = new ReserveDate(month, day, time);

        // For Login
        String staffId = "", staffPassword;
        StaffDatabase staffs = new StaffDatabase();
        Boolean loggedIn = false;

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
        String newOrderId, orderId = "";
        int orderPax, orderIndex;
        Boolean proceedToOrder;

        String orderFoodId;
        int orderFoodQuantity;
        Boolean successful = false;

        OrderDatabase orders = new OrderDatabase();
        SalesDatabase sales = new SalesDatabase();

        // For Menu Editing
        int editChoice, editInnerChoice = 0, foodPromoChoice;
        String strInput, foodId;
        float floatInput;
        ArrayList<String> itemList;

        // For Sales Report
        float salesValue;
        HashMap<Integer, Float> salesData;

        // For testing
        menu = MenuImporter.importExcel(menu);

        PromoSet tmpSet = new PromoSet();
        tmpSet.setId(menu.getNewFoodId("Promotion Set"));
        tmpSet.setName("Classic Angus cheese burger meal");
        tmpSet.setSaleCost(10.95f);
        tmpSet.setallItemIds(new ArrayList<String>(Arrays.asList("1000", "6001", "5001")), menu);
        menu.insertFood(tmpSet);

        Staff manager, cashier1, cashier2;
        manager = new Staff(staffs.getNewStaffId(), "Lin Guo Qiang", 'M', "Manager", "password");       // ID 0001
        staffs.addStaff(manager);
        cashier1 = new Staff(staffs.getNewStaffId(), "Andreane Gerlach", 'F', "Cashier", "password");   // ID 0002
        staffs.addStaff(cashier1);
        cashier2 = new Staff(staffs.getNewStaffId(), "Darryl Waelchi", 'M', "Cashier", "password");     // ID 0003
        staffs.addStaff(cashier2);

        do {
            if(!loggedIn) {
                System.out.print("|========================================================|\n"
                        + "|                         MENU                           |\n"
                        + "|========================================================|\n"
                        + "||     1: Login                                         ||\n"
                        + "||     2: Quit Program                                  ||\n"
                        + "|========================================================|\n");
                System.out.print("Enter your action: ");
                choice = sc.nextInt();
                if(choice==1) {
                    System.out.print("|========================================================|\n"
                            + "|                         LOGIN                          |\n"
                            + "|========================================================|\n");
                    System.out.print("Enter ID: ");
                    staffId = sc.next();
                    System.out.print("Enter Password: ");
                    staffPassword = sc.next();

                    if(staffs.staffLogin(staffId, staffPassword)) {
                        staffPassword = "";
                        System.out.println("Welcome, " + staffs.getStaff(staffId).getName());
                        loggedIn = true;
                    }
                    else {
                        System.out.println("Incorrect ID or Password");
                    }
                    staffPassword = "";
                }
                else if(choice!=2) System.out.println("Invalid Input");
                else {
                    System.out.println("You have ended the program");
                    System.exit(0);
                }
            }
            if(!loggedIn) continue;

            if(staffs.getStaff(staffId).getJobTitle().equals("Manager")) {
                System.out.print("|========================================================|\n"
                        + "|                         MENU                           |\n"
                        + "|========================================================|\n"
                        + "||     1: Manage Orders                                 ||\n"
                        + "||     2: Manage Reservations                           ||\n"
                        + "||     3: Edit Menu                                     ||\n"
                        + "||     4: View Sales Data                               ||\n"
                        + "||     5: Logout                                        ||\n"
                        + "||     6: Quit Program                                  ||\n"
                        + "|========================================================|\n");
            }
            else {
                System.out.print("|========================================================|\n"
                        + "|                         MENU                           |\n"
                        + "|========================================================|\n"
                        + "||     1: Manage Orders                                 ||\n"
                        + "||     2: Manage Reservations                           ||\n"
                        + "||     3: Logout                                        ||\n"
                        + "||     4: Quit Program                                  ||\n"
                        + "|========================================================|\n");
            }

            System.out.print("Enter your action: ");
            choice = sc.nextInt();

            if(choice==1) { //Order
                do {
                    System.out.print("|========================================================|\n"
                            + "|                         MENU                           |\n"
                            + "|========================================================|\n"
                            + "||     1: Create a New Order                            ||\n"
                            + "||     2: Manage an Order                               ||\n"
                            + "||     3: Display All Current Orders                    ||\n"
                            + "||     4: Remove Order                                  ||\n"
                            + "||     5: Exit.                                         ||\n"
                            + "|========================================================|\n");
                    System.out.print("Enter your action: ");

                    choice = sc.nextInt();

                    proceedToOrder = false;
                    if(choice==1) {
                        System.out.println("============================== CREATE A NEW ORDER ==============================");
                        System.out.print("Enter Table ID: ");
                        orderId = sc.next();
                        System.out.print("Enter #pax: ");
                        orderPax = sc.nextInt();
                        orders.newOrder(orderId, orderPax, staffId);
                        orderIndex = orders.getOrderIndex(orderId);
                        proceedToOrder = true;
                    }
                    else if(choice==2) {
                        System.out.println("============================== MANAGE AN ORDER ==============================");
                        orders.printDatabase();
                        System.out.print("Enter Table ID: ");
                        orderId = sc.next();
                        orderIndex = orders.getOrderIndex(orderId);
                        proceedToOrder = true;
                    }
                    else if(choice==3) {
                        System.out.println("============================== DISPLAY ALL CURRENT ORDERS ==============================");
                        if(orders.getTotalNumelements() == 0) System.out.println("No Current Orders");
                        orders.printDatabase();
                    }
                    else if(choice==4) {
                        System.out.println("============================== REMOVE AN ORDER ==============================");
                        orders.printDatabase();
                        System.out.print("Enter Table ID: ");
                        orderId = sc.next();
                        orderIndex = orders.getOrderIndex(orderId);
                        orders.removeOrder(orderId);
                    }

                    if(proceedToOrder) {
                        do{
                            System.out.print("|========================================================|\n"
                                    + "|                         MENU                           |\n"
                                    + "|========================================================|\n"
                                    + "||     1: Add to cart.                                  ||\n"
                                    + "||     2: Remove from cart.                             ||\n"
                                    + "||     3: Display Menu.                                 ||\n"
                                    + "||     4: Display Current Order.                        ||\n"
                                    + "||     5: Checkout.                                     ||\n"
                                    + "||     6: Exit.                                         ||\n"
                                    + "|========================================================|\n");
                            System.out.print("Enter your action: ");

                            orderChoice = sc.nextInt();

                            switch(orderChoice){
                                case 1://add to cart
                                    System.out.println("============================== ADD TO CART ==============================");
                                    System.out.print("Enter Item ID: ");
                                    orderFoodId = sc.next();
                                    System.out.print("Enter Quantity: ");
                                    orderFoodQuantity = sc.nextInt();
                                    successful = orders.addFood(orderId, orderFoodId, orderFoodQuantity);
                                    if(!successful) {
                                        System.out.println("Invalid Input!");
                                    }
                                    break;
                                case 2: //remove from cart
                                    System.out.println("============================== REMOVE FROM CART ==============================");
                                    orders.printFoods(orderId, menu);
                                    System.out.print("Enter Item ID to be Removed: ");
                                    orderFoodId = sc.next();
                                    successful = orders.removeFood(orderId, orderFoodId);
                                    if(!successful) {
                                        System.out.println("Invalid Input!");
                                    }
                                    break;
                                case 3: //Display menu
                                    System.out.println("============================== FOOD MENU ==============================");
                                    menu.printFoods(true, false);
                                    System.out.print("Select Item ID to Display Details? (y/n): ");
                                    strInput = sc.next();
                                    if(strInput.equals("y")){
                                        System.out.print("Enter Item ID to View: ");
                                        strInput = sc.next();
                                        if (menu.isFood(strInput)) {
                                            System.out.println("===== Item Details =====");
                                            if(menu.isItem(strInput)){
                                                Item localItem = (Item) menu.getFood(strInput);
                                                System.out.println("ID:          " + localItem.getId());
                                                System.out.println("Name:        " + localItem.getName());
                                                System.out.println("Description: " + localItem.getFoodDesc());
                                                System.out.println("Type:        " + localItem.getType());
                                                System.out.println("Sales Cost:  $" + localItem.getSaleCost());
                                                System.out.println("Base Cost:   $" + localItem.getBaseCost());
                                            }
                                            else{
                                                PromoSet localSet = (PromoSet) menu.getFood(strInput);
                                                System.out.println("ID:          " + localSet.getId());
                                                System.out.println("Name:        " + localSet.getName());
                                                System.out.println("Description: " + localSet.getFoodDesc());

                                                System.out.println("Items within this Set: ");
                                                List<String> localSetIds = localSet.getAllItemIds();
                                                for(int i = 0; i < localSetIds.size(); i++){
                                                    System.out.println(" - " + menu.getFood(localSetIds.get(i)).getId() + " " + menu.getFood(localSetIds.get(i)).getName());
                                                }
                                                System.out.println("Sales Cost:  $" + localSet.getSaleCost());
                                                System.out.println("Base Cost:   $" + localSet.getBaseCost());
                                            }
                                        }
                                        else{
                                            System.out.println("Invalid Input");
                                        }
                                    }
                                    else if(!strInput.equals("n")){
                                        System.out.println("Invalid Input");
                                    }
                                    // add option to display info
                                    break;
                                case 4: //Retrieve Order Info
                                    System.out.println("============================== CURRENT ORDERS ==============================");
                                    if(orders.getOrderInfo(orderId).getOrderSize() == 0) System.out.println("No items within this order");
                                    orders.printFoods(orderId, menu);
                                    break;
                                case 5: //Checkout
                                    System.out.println("============================== CHECK OUT ==============================");
                                    System.out.print("Apply Membership Discount? (y/n): ");
                                    strInput = sc.next();
                                    if(strInput.toLowerCase().equals("y")){
                                        orders.applyMembership(orderId);
                                    }
                                    else if(!strInput.toLowerCase().equals("n")){
                                        System.out.println("Invalid Input");
                                        break;
                                    }
                                    orders.computeBill(orderId, menu);
                                    orders.printBill(orderId, menu, staffs);
                                    sales.appendOrder(orders.removeOrder(orderId));
                                    orderChoice = 6;
                                    break;
                            }
                        } while(orderChoice != 6);
                    }
                } while(choice!=5);
                choice = 0;
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

            else if (choice==3 && staffs.getStaff(staffId).getJobTitle().equals("Manager")){
                do{
                    System.out.print("|========================================================|\n"
                            + "|                    EDITING MENU                        |\n"
                            + "|========================================================|\n"
                            + "||     1: Add a Food Item or Promotional Set             ||\n"
                            + "||     2: Edit a Food Item or Promotional Set            ||\n"
                            + "||     3: Hide a Food Item or Promotional Set            ||\n"
                            + "||     4: Remove a Food Item or Promotional Set          ||\n"
                            + "||     5: View Menu                                      ||\n"
                            + "||     6: Exit.                                          ||\n"
                            + "|========================================================|\n");
                    System.out.print("Enter your action: ");

                    editChoice = sc.nextInt();

                    switch (editChoice){
                        case 1: // Add Food/Set
                            System.out.println("=== Enter the following info ===");
                            System.out.println("Available Categories: ");
                            menu.printCategoryList();
                            System.out.print("Input Category Code: ");
                            strInput = sc.next();

                            // Invalid input with length > 1 or category code
                            if(strInput.length() > 1 ||  menu.getCategoryKeyMap().getCategory(strInput) == ""){
                                System.out.println("Invalid Input");
                            }

                            // Food Item
                            else if(menu.getCategoryKeyMap().getCategory(strInput) != "Promotion Set"){
                                Item newItem = new Item();
                                newItem.setType(menu.getCategoryKeyMap().getCategory(strInput));
                                newItem.setId(menu.getNewFoodId(strInput));

                                System.out.print("Name: ");
                                strInput = sc.next();
                                newItem.setName(strInput);

                                System.out.print("Description: ");
                                strInput = sc.next();
                                newItem.setFoodDesc(strInput);

                                System.out.print("Sales Cost: ");
                                floatInput = sc.nextFloat();
                                newItem.setSaleCost(floatInput);

                                System.out.print("Base Cost: ");
                                floatInput = sc.nextFloat();
                                newItem.setSaleCost(floatInput);

                                if(newItem.attributesAreValid(menu)){
                                    menu.insertFood(newItem);
                                    System.out.println("Item Added");
                                }
                                else{
                                    System.out.println("Invalid Input(s)");
                                }
                            }
                            // Promotional Set
                            else{
                                PromoSet newSet = new PromoSet();
                                newSet.setType(menu.getCategoryKeyMap().getCategory(strInput));
                                newSet.setId(menu.getNewFoodId("Promotion Set"));

                                System.out.print("Name: ");
                                strInput = sc.next();
                                newSet.setName(strInput);

                                System.out.print("Description: ");
                                strInput = sc.next();
                                newSet.setFoodDesc(strInput);

                                System.out.println("==== Select Food Items to Add to Set ====");
                                menu.printFoods(false, false);
                                System.out.print("Enter Food IDs (type -1 to exit): ");
                                itemList = new ArrayList<String>();
                                do{
                                    System.out.print("ID: ");
                                    strInput = sc.next();
                                    if(!strInput.matches("-1"))itemList.add(strInput);
                                }while(!strInput.matches("-1"));

                                newSet.setallItemIds(itemList, menu);
                                System.out.print("Sales Cost: ");
                                floatInput = sc.nextFloat();
                                newSet.setSaleCost(floatInput);

                                if(newSet.attributesAreValid(menu)){
                                    menu.insertFood(newSet);
                                    System.out.println("Promotional Set Added");
                                }
                                else{
                                    System.out.println("Invalid Input(s)");
                                }
                            }
                            break;
                        case 2: // Edit Food/Set
                            menu.printFoods(true, true);
                            System.out.print("Select Food ID to Edit: ");
                            strInput = sc.next();
                            if(!menu.isFood(strInput))
                            {
                                System.out.println("Invalid Input");
                            }
                            else
                            {
                                foodId = strInput;
                                if(menu.isItem(foodId))
                                {
                                    Item newItem = (Item) menu.getFood(foodId);
                                    newItem.setId(menu.getNewFoodId(newItem.getType()));
                                    do{
                                        System.out.println("Select an attribute to edit or -1 to exit: ");
                                        System.out.println(" 1. Name");
                                        System.out.println(" 2. Description");
                                        System.out.println(" 3. Type");
                                        System.out.println(" 4. Sales cost");
                                        System.out.println(" 5. Base cost");

                                        System.out.print("Choice: ");
                                        editInnerChoice = sc.nextInt();

                                        switch (editInnerChoice){
                                            case 1:
                                                System.out.print("Enter a new name: ");
                                                strInput = sc.next();
                                                newItem.setName(strInput);
                                                break;
                                            case 2:
                                                System.out.print("Enter a new description: ");
                                                strInput = sc.next();
                                                newItem.setFoodDesc(strInput);
                                                break;
                                            case 3:
                                                System.out.println("Available Categories: ");
                                                menu.printCategoryList();
                                                System.out.print("Input Category Code: ");
                                                strInput = sc.next();

                                                // Invalid input with length > 1 or category code
                                                if(strInput.length() > 1 ||  menu.getCategoryKeyMap().getCategory(strInput) == ""){
                                                    System.out.println("Invalid Input");
                                                }
                                                else{
                                                    newItem.setType(menu.getCategoryKeyMap().getCategory(strInput));
                                                    newItem.setId(menu.getNewFoodId(strInput));
                                                }
                                                break;
                                            case 4:
                                                System.out.println("Enter a new sales cost: ");
                                                floatInput = sc.nextFloat();
                                                if(floatInput < 0) System.out.println("Invalid Input");
                                                else newItem.setSaleCost(floatInput);
                                                break;
                                            case 5:
                                                System.out.println("Enter a new base cost: ");
                                                floatInput = sc.nextFloat();
                                                if(floatInput < 0) System.out.println("Invalid Input");
                                                else newItem.setBaseCost(floatInput);
                                                break;
                                        }
                                    } while(editInnerChoice != -1);
                                    menu.hideFood(foodId);
                                    menu.insertFood(newItem);
                                }
                                else{
                                    PromoSet newSet = (PromoSet) menu.getFood(foodId);
                                    do{
                                        System.out.println("Select an attribute to edit or -1 to exit: ");
                                        System.out.println(" 1. Name");
                                        System.out.println(" 2. Description");
                                        System.out.println(" 3. Item List");
                                        System.out.println(" 4. Sales cost");

                                        System.out.print("Choice: ");
                                        editInnerChoice = sc.nextInt();

                                        switch (editInnerChoice){
                                            case 1:
                                                System.out.print("Enter a new name: ");
                                                strInput = sc.next();
                                                newSet.setName(strInput);
                                                break;
                                            case 2:
                                                System.out.print("Enter a new description: ");
                                                strInput = sc.next();
                                                newSet.setFoodDesc(strInput);
                                                break;
                                            case 3:
                                                System.out.println("==== Select New Set of Food Items for the Set ====");
                                                menu.printFoods(false, false);
                                                System.out.print("Enter Food IDs (type -1 to exit): ");
                                                itemList = new ArrayList<String>();
                                                do{
                                                    System.out.print("ID: ");
                                                    strInput = sc.next();
                                                    if(!strInput.matches("-1"))itemList.add(strInput);
                                                }while(!strInput.matches("-1"));
                                                newSet.setallItemIds(itemList, menu);
                                                break;
                                            case 4:
                                                System.out.println("Enter a new sales cost: ");
                                                floatInput = sc.nextFloat();
                                                if(floatInput < 0) System.out.println("Invalid Input");
                                                else newSet.setSaleCost(floatInput);
                                                break;
                                        }
                                    } while(editInnerChoice != -1);
                                    menu.hideFood(foodId);
                                    menu.insertFood(newSet);
                                }

                            }
                            break;
                        case 3: // Hide Food/Set
                            menu.printFoods(true, true);
                            System.out.print("Select Food ID to Hide: ");
                            strInput = sc.next();
                            if(!menu.isFood(strInput)) {
                                System.out.println("Invalid Input");
                            }
                            else {
                                menu.hideFood(strInput);
                            }
                            break;
                        case 4: // Remove Food/Set
                            menu.printFoods(true, true);
                            System.out.print("Select Food ID to Remove: ");
                            strInput = sc.next();
                            if(!menu.isFood(strInput)) {
                                System.out.println("Invalid Input");
                            }
                            else {
                                menu.removeFood(strInput);
                            }
                            break;
                        case 5: // View Menu
                            menu.printFoods(true, true);
                            break;

                    }
                } while(editChoice != 6);
            }
            else if(choice==4 && staffs.getStaff(staffId).getJobTitle().equals("Manager")){
                System.out.println("============================== SALES REPORT ==============================");
                int startMonth, endMonth;
                System.out.print("Enter starting month: ");
                startMonth = sc.nextInt();
                if(month < 1 || month > 12){
                    System.out.println("Invalid Input");
                    break;
                }
                System.out.print("Enter ending month: ");
                endMonth = sc.nextInt();
                if(month < 1 || month > 12){
                    System.out.println("Invalid Input");
                    break;
                }

                System.out.println("============================== SALES REPORT ==============================");
                sales.analyseTotalOrders();
                if(endMonth - startMonth == 1) 
                {
                    System.out.println("Sales Report for " + new DateFormatSymbols().getMonths()[startMonth - 1]);
                    System.out.println(" - # of Orders           = " + sales.getTotalNumelements());
                    System.out.println(" - Revenue               = $" + sales.getTotalRevenue());
                    System.out.println(" - Profit                = $" + sales.getTotalProfit());
                    System.out.println(" - Membership Discounts  = " + sales.getTotalMembershipDiscount());
                }
                else 
                {
                    System.out.println("Sales Report for "
                        + new DateFormatSymbols().getMonths()[startMonth - 1]
                        + " until " + new DateFormatSymbols().getMonths()[endMonth - 1]  + " : ");
                    System.out.println(" - # of Orders           = " + sales.getTotalNumelements());
                    System.out.println(" - Revenue               = $" + sales.getTotalRevenue());
                    System.out.println(" - Profit                = $" + sales.getTotalProfit());
                    System.out.println(" - Membership Discounts  = " + sales.getTotalMembershipDiscount());

                salesValue = 0f;
                salesData = sales.getMonthwiseOrders();
                System.out.println("\nMonthwise Analysis Orders");
                for(int i = startMonth; i < endMonth + 1; i++){
                    salesValue += salesData.get(i);
                    System.out.println("Month " + new DateFormatSymbols().getMonths()[i - 1] + " got " + salesData.get(i)+ " orders");
                }
                // System.out.println(" - # of Orders           = " + (Integer)Math.round(salesValue));
                
                System.out.println("\nMonthwise Analysis Revenue");
                salesValue = 0f;
                salesData = sales.getMonthwiseRevenue();
                for(int i = startMonth; i < endMonth + 1; i++){
                    salesValue += salesData.get(i);
                    System.out.println("Month " + new DateFormatSymbols().getMonths()[i - 1] + " got $" + salesData.get(i)+ " as revenue");
                }
                
                System.out.println("\nMonthwise Analysis Profit");
                salesValue = 0f;
                salesData = sales.getMonthwiseProfit();
                for(int i = startMonth; i < endMonth + 1; i++){
                    salesValue += salesData.get(i);
                    System.out.println("Month " + new DateFormatSymbols().getMonths()[i - 1] + " got $" + salesData.get(i)+ " as profit");
                }
                // System.out.println(" - Profit                = $" + sales.getTotalProfit());

                System.out.println("\nMonthwise Analysis Membership Discount");
                salesValue = 0f;
                salesData = sales.getMonthwiseMembershipDiscount();
                for(int i = startMonth; i < endMonth + 1; i++){
                    salesValue += salesData.get(i);
                    System.out.println("Month " + new DateFormatSymbols().getMonths()[i - 1] + " gave $" + salesData.get(i)+ " amount as membership discount");
                }
                // System.out.println(" - Membership Discounts  = " + sales.getTotalMembershipDiscount());
                }
            }
            else if ((choice==3 && !staffs.getStaff(staffId).getJobTitle().equals("Manager")) || (choice==5 && staffs.getStaff(staffId).getJobTitle().equals("Manager"))){
                System.out.println("You have been logged out");
                loggedIn = false;
            }
            else if ((choice==4 && !staffs.getStaff(staffId).getJobTitle().equals("Manager")) || (choice==6 && staffs.getStaff(staffId).getJobTitle().equals("Manager"))){
                System.out.println("You have ended the program");
                System.exit(0);
            }
            else {
                System.out.println("Please enter a valid input");
            }
        } while (true);


    }
}
