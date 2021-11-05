import java.util.Scanner;
public class test
{
	public static void main(String[] args)
    {
    	// OrderDatabase orders = new OrderDatabase();
     //    orders.newOrder("12", 2);
     //    orders.newOrder("12", 1);
     //    orders.newOrder("11", 1);

     //    orders.printCurrentOrders();
        String test = "abcde";
        for(int i = 0; i < 10000; i++)
        {
            System.out.println(StringUtil.incrementString(test, i));
        }
        
    }
}