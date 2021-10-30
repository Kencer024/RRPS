import java.util.Scanner;
public class test
{
	public static void main(String[] args)
    {
    	OrderDatabase orders = new OrderDatabase();
        orders.newOrder("12", 2);
        orders.newOrder("12", 1);
        orders.newOrder("11", 1);

        orders.printCurrentOrders();
    }
}