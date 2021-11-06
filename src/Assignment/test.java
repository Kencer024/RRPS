
package Assignment;

import java.util.Scanner;
public class test
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        MenuList menu = new MenuList();
        MenuImporter sth = new MenuImporter();
        menu = sth.importExcel(menu); 
        menu.printItems();

        OrderDatabase orders = new OrderDatabase();
        int select = -1;
        Boolean newTable = false;
        String tableId = "0";

        orders.newOrder("12", 6);
        orders.addItem("12", "1001", 2);
        orders.addItem("12", "6003", 2);
        orders.printBill("12", menu);

        while(true)
        {
            System.out.print("Choice : ");
            select = sc.nextInt();
            newTable = false;
            switch(select)
            {
                case 1:
                    System.out.print("Table Id : ");
                    tableId = sc.next();
                    System.out.print("Pax : ");
                    int pax = sc.nextInt();
                    orders.newOrder(tableId, pax);
                    newTable = true;
                    orders.printDateTime(tableId);
                case 2:
                    if(!newTable)
                    {
                        System.out.print("Table Id : ");
                        tableId = sc.next();
                    }
                    while(select != -1)
                    {
                        System.out.print("Table " + tableId + " | Choice : ");
                        select = sc.nextInt();
                        switch(select)
                        {
                            case 1:
                                menu.printItems();
                                break;
                            case 2:
                                orders.printItems(tableId);
                                orders.printSets(tableId);
                                break;
                            case 3:
                                System.out.print("Item id : ");
                                String itemid = sc.next();
                                System.out.print("Amount : ");
                                int amount = sc.nextInt();
                                orders.addItem(tableId, itemid, amount);
                                break;
                            case 4:
                                System.out.print("Item id : ");
                                itemid = sc.next();
                                orders.removeItem(tableId, itemid);
                                break;
                            case 5:
                                System.out.print("Set id : ");
                                String setid = sc.next();
                                System.out.print("Amount : ");
                                amount = sc.nextInt();
                                orders.addSet(tableId, setid, amount);
                                break;
                            case 6:
                                System.out.print("Set id : ");
                                setid = sc.next();
                                orders.removeSet(tableId, setid);
                                break;

                            case -1:
                                break;
                        }
                    }
                    break;
            }
        }
    }
}