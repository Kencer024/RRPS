// package Assignment;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);

        OrderDatabase orders = new OrderDatabase();
        MenuList menu = new MenuList();

        // Reservation[] r = new Reservation[30];
        // r[0].checkEmptyTable();

        //Test
        orders.newOrder("12", 2);
        orders.newOrder("12", 1);
        orders.newOrder("11", 1);

        Item dish1 = new Item();
        dish1.setId(menu.getNewItemId());
        dish1.setName("Dish1");
        dish1.setType("main");
        dish1.setSaleCost(10);
        dish1.setBaseCost(6);
        menu.appendItem(dish1);

        Item dish2 = new Item();
        dish1.setId(menu.getNewItemId());
        dish1.setName("Dish2");
        dish1.setType("main");
        dish1.setSaleCost(11);
        dish1.setBaseCost(7);
        menu.appendItem(dish2);

        Item drink1 = new Item();
        dish1.setId(menu.getNewItemId());
        dish1.setName("Drink1");
        dish1.setType("drink");
        dish1.setSaleCost(11);
        dish1.setBaseCost(7);
        menu.appendItem(drink1);
        // System.out.println(menu.getNewItemId());

        int choice = -1;
        String currentOrderId = null;
        while(true) {
            //Order part
            System.out.println("1. See current orders");
            System.out.println("2. Access current orders");
            System.out.println("3. Create new order");

            System.out.printf("Choice  : ");
            choice = input.nextInt();
            switch(choice) {
                case 1:
                    orders.printCurrentOrders();
                    break;
                case 2:
                    if(orders.size() == 0) {
                        System.out.println("Currently no active orders");
                    }
                    else {
                        orders.printCurrentOrders();
                        System.out.println("Select table to access order  : ");
                        currentOrderId = input.nextLine();

                        if(orders.isInOrderList(currentOrderId)) {
                            System.out.println("1. Add item");
                            System.out.println("2. Remove item");

                            System.out.printf("Choice  : ");
                            choice = input.nextInt();

                            switch(choice) {
                                case 1:
                                    
                            }
                        }
                    }
                    break;
                case 3:
                    break;
            }
            

        }
    }
}
