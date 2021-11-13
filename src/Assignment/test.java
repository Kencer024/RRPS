
package Assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class test
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Item tmp = new Item();
        System.out.println(tmp.getClassName() == "Item");

        MenuList menu = new MenuList();
        MenuImporter sth = new MenuImporter();
//
        menu = sth.importExcel(menu);
//
        PromoSet tmpSet = new PromoSet();
        tmpSet.setId(menu.getNewFoodId("Promotion Set"));
        tmpSet.setName("Classic Angus cheese burger meal");
        tmpSet.setSaleCost(10.95f);
        tmpSet.setallItemIds(new ArrayList<String>(Arrays.asList("1000", "6001", "5001")));
        menu.insertFood(tmpSet);
        menu.printFoods();
        menu.printMenu("Promotion Set");
//        menu.printSets();
//        menu.removeSet("7000");
//        System.out.println("\n===================================================");
//        menu.printItems();
//        menu.printSets();

        OrderDatabase orders = new OrderDatabase();
        int select = -1;
        Boolean newTable = false;
        String tableId = "0";

//        SalesDatabase sales = new SalesDatabase();

        orders.newOrder("12", 4);
        orders.addFood("12", "1000", 1);
        orders.addFood("12", "6000", 2);
        orders.addFood("12", "7000", 2);
//        orders.printItems("12");
        // orders.printBill("12", menu);
//        sales.appendOrder(orders.removeOrder("12"));
//        System.out.println(orders.removeOrder("12").getTotal());

        orders.newOrder("10", 4);
        orders.addFood("10", "1000", 1);
        orders.addFood("10", "6000", 2);
        orders.addFood("10", "7000", 2);
        orders.printItems("10", menu);
        orders.printBill("10", menu);
//        sales.appendOrder(orders.removeOrder("12"));
//
//        sales.analyseTotalOrders();
//        System.out.println(sales.getTotalRevenue());
//
//        while(select != -2)
//        {
//            System.out.print("Choice : ");
//            select = sc.nextInt();
//            newTable = false;
//            switch(select)
//            {
//                case 1:
//                    System.out.print("Table Id : ");
//                    tableId = sc.next();
//                    System.out.print("Pax : ");
//                    int pax = sc.nextInt();
//                    orders.newOrder(tableId, pax);
//                    newTable = true;
//                    orders.printDateTime(tableId);
//                case 2:
//                    if(!newTable)
//                    {
//                        System.out.print("Table Id : ");
//                        tableId = sc.next();
//                    }
//                    while(select != -1)
//                    {
//                        System.out.print("Table " + tableId + " | Choice : ");
//                        select = sc.nextInt();
//                        switch(select)
//                        {
//                            case 1:
//                                menu.printItems();
//                                break;
//                            case 2:
//                                orders.printItems(tableId);
//                                orders.printSets(tableId);
//                                break;
//                            case 3:
//                                System.out.print("Item id : ");
//                                String itemid = sc.next();
//                                System.out.print("Amount : ");
//                                int amount = sc.nextInt();
//                                orders.addItem(tableId, itemid, amount);
//                                break;
//                            case 4:
//                                System.out.print("Item id : ");
//                                itemid = sc.next();
//                                orders.removeItem(tableId, itemid);
//                                break;
//                            case 5:
//                                System.out.print("Set id : ");
//                                String setid = sc.next();
//                                System.out.print("Amount : ");
//                                amount = sc.nextInt();
//                                orders.addSet(tableId, setid, amount);
//                                break;
//                            case 6:
//                                System.out.print("Set id : ");
//                                setid = sc.next();
//                                orders.removeSet(tableId, setid);
//                                break;
//
//                            case -1:
//                                break;
//                        }
//                    }
//                    break;
//            }
//        }
    }
}