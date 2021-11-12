package Assignment;

//javadocs

/**Represents a class where all the records of Orders are stored
* and analysis is performed to generate SalesReport
*
*/
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;


public class SalesDatabase implements DatabaseInterface
{
	// ArrayList
	private ArrayList<Order> all_orders_;
	private float total_revenue;
	private float total_profit;
	private float total_basecost;
	private float total_membership_discount;
	private HashMap<Integer, Float> month_revenue = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> month_profit = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> month_orders = new HashMap<Integer, Float>();

	/** Overriding the default constructor to initialise the array list and all the private variables
	 * used for analysis to 0
	 */
	public SalesDatabase()
	{
		this.all_orders_ = new ArrayList<Order>();
		this.total_revenue = 0;
		this.total_basecost = 0;
		this.total_profit = 0;
		this.total_membership_discount = 0;

		for(int i = 1; i<13 ; i++)
		{
			this.month_revenue.put(i, 0f);
			this.month_profit.put(i,0f);
			this.month_orders.put(i,0f);
		}
	}

	/** Gets the Total Revenue of all the orders in the database
    * @return A float representing the total revenue
    */
	public float getTotalRevenue() { return this.total_revenue;}

	/** Gets the Total Profit of all the orders in the database
    * Total Profit = Total Revenue - Cost Price(Base Cost)
	 * @return A float representing the total profit
    */
	public float getTotalProfit() { return this.total_profit;}

	/** Gets the Total Base Cost of all the orders in the database
	 * Total Base cost is the cost price of all the ingredients (Cost Price)
    * @return A float representing the total revenue
    */
	public float getTotalBaseCost() { return this.total_basecost;}

	/** Gets the Total Membership Discount for all the orders in the database
    * Total Membership Discount is the total value people have got as a discount
	 * @return A float representing the total revenue
    */
	public float getTotalMembershipDiscount() { return this.total_membership_discount;}

	/** Gets the revenue for each month as a HashMap
	 * @return A HashMap with keys as months(integer 1 to 12) and values representing the total revenue
    */
	public HashMap<Integer, Float> getMonthwiseRevenue() { return this.month_revenue;}

	/** Gets the profit for each month as a HashMap
	 * @return A HashMap with keys as months(integer 1 to 12) and values representing the total profit for the particular month
    */
	public HashMap<Integer, Float> getMonthwiseProfit() { return this.month_profit;}

	/** Gets the total orders for each month as a HashMap
	 * @return A HashMap with keys as months(integer 1 to 12) and values representing the total orders for the particular month
    */
	public HashMap<Integer, Float> getMonthwiseOrders() { return this.month_orders;}

	/** Fuction is used to add the orders to the database
	 * @param order_add object of type Order that has all the information about the order including time stamps
    */

	public void appendOrder(Order order_add)
	{
		this.all_orders_.add(order_add);
	}

	/** Fuction is used to remove the orders from the database
	 * @param order_id of type String is used to find the unique order from the order database
    */
	public Order removeOrder(String order_id)
	{
		for(int i = 0; i< this.all_orders_.size();i++)
       {
			Order local_order = this.all_orders_.get(i);
			if(order_id.equals(local_order.getOrderId()))
				{
					this.all_orders_.remove(local_order);
                    return local_order;
				}
		}
        return null;
	}

	/** Fuction is used to analyse all orders from the database
	 * It loops through all the orders in the database and computes the total revenue, total profit,
	 * total basecost, total discount given on membership as a part of overall analysis
	 * To look at the growth of the the business across the months,
	 * hashmaps are used to store the the revenue, profit and number of orders monthwise
	 * ASSUMPTION: This database is only for 1 year (It can be exteneded easily by adding another parameter year
	 * as LocalDateTime Class gives us access to the year)
    */
	public void analyseTotalOrders()
   {
       for(int i = 0; i< this.all_orders_.size();i++)
       {
           Order local_order = this.all_orders_.get(i);
           this.total_revenue+= local_order.getTotal();
			this.total_basecost+= local_order.getBaseCost();
			this.total_membership_discount+= local_order.getMemberDiscount();
			float profit = local_order.getTotal() - local_order.getBaseCost();
			this.total_profit+= profit ;

			int month = local_order.getDateTime().getMonthValue();
			this.month_revenue.put(month, this.month_revenue.get(month) + local_order.getTotal());
			this.month_profit.put(month, this.month_profit.get(month) + profit);
			this.month_orders.put(month,this.month_orders.get(month)+1);

       }
   }

	/** Fuction is used to retrieve all orders within a given time period
	 * It loops through all the orders in the database and checks whether the timestamp of the order
	 * falls in the desired range( start and end )
	 * @param start of the type LocalDateTime represents the start of the desired time period
	 * @param end of the type LocalDateTime represents the end of the desired time period
	 * @return A sorted array list of all the orderIds that fall within the desired time period
    */

	public ArrayList<String> getOrderstimePeriod(LocalDateTime start, LocalDateTime end)
	{
		ArrayList<String> searched_orders = new ArrayList<String>();
		for(int i=0; i<this.all_orders_.size() ;i++)
		{
			Order local_order = this.all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			boolean isAfter = order_datetime.isAfter(start);
			boolean isBefore = order_datetime.isBefore(end);
			if(isBefore && isAfter)
			{
				searched_orders.add(local_order.getOrderId());
			}
		}
		return sort_ids(searched_orders);
	}

	/** Fuction is used to retrieve all orders from a particular Table within a given time period
	 * It uses the above function getOrderstimePeriod to retrieve all the orders within the given time period
	 * and then checks all the retrieved strings for the TableID that is the last 3 characters of the OrderID
	 * @param start of the type LocalDateTime represents the start of the desired time period
	 * @param end of the type LocalDateTime represents the end of the desired time period
	 * @param tableId_input of the type String represents the table for which you want to retrieve orders
	 * @return A sorted array list of all the orderIds that fall within the desired time period belonging to the given tableID
    */
	public ArrayList<String> getOrderstimePeriodTableId(LocalDateTime start, LocalDateTime end, String tableId_input)
	{
		ArrayList<String> orders_table = new ArrayList<String>();
		ArrayList<String> all_orders_period = getOrderstimePeriod(start,end);
		for(int i =0; i<all_orders_period.size(); i++)
		{
			String local_order_id = all_orders_period.get(i);
			if(local_order_id.substring(local_order_id.length() - 3).equals( tableId_input))
			{
				orders_table.add(local_order_id);
			}
		}
		return sort_ids(orders_table);
	}

	/** Fuction is used to retrieve the full Order information given the OrderID
	 * It loops through all the orders and returns the Order object that matches the given given OrderID
	 * @param orderId_input of the type String represents the unique OrderID to be searched for in the database
	 * @return An object of the type Order that matches with the input OrderID
    */
	public Order getOrderInfo(String orderId_input)
	{
		for(int i=0; i<this.all_orders_.size() ;i++)
		{
			Order local_order = this.all_orders_.get(i);
			if(orderId_input.equals(local_order.getOrderId()))
				{
					return local_order;
				}

		}
		System.out.println("OrderID : " + orderId_input + " not found");
		return null;

	}

	/** Fuction is used to sort all the strings
	 * It sorts the given arraylist of strings using the sort function from Collections class
	 * @param orderIds_input_sort is an arraylist containing all the strings(OrderIds)
	 * @return A sorted arraylist
    */
	private ArrayList<String> sort_ids(ArrayList<String> orderIds_input_sort)
	{
		Collections.sort(orderIds_input_sort);
		return orderIds_input_sort;
	}

	/** Fuction is used print all the OrderIds in the database
    */
	public void printDatabase()
	{
		for( int i = 0; i< this.all_orders_.size();i++)
		{
			System.out.println("Order ID: " + this.all_orders_.get(i).getOrderId());
		}
	}

    public int getTotalNumelements()
    {
        return this.all_orders_.size();
    }

	// NOT USED
	// public ArrayList<String> getOrderstimePeriod(int time_start, int time_end,int date_start, int date_end, int month_start, int month_end)
	// {
	// 	ArrayList<String> searched_orders = new ArrayList<String>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		int month_order = order_datetime.getMonthValue();
	// 		int date_order = order_datetime.getDayOfMonth();
	// 		int time_order = order_datetime.getHour();
	// 		if(check_in_range(month_start,month_end,month_order) == 1)
	// 		{
	// 			searched_orders.add(local_order.getOrderID());
	// 		}
	// 		else if(check_in_range(month_start,month_end,month_order) == 0)
	// 		{
	// 			if(check_in_range(date_start,date_end,date_order) == 1)
	// 			{
	// 				searched_orders.add(local_order.getOrderID());
	// 			}
	// 			else if (check_in_range(date_start,date_end,date_order) == 0)
	// 			{
	// 				if(check_in_range(time_start, time_end, time_order) == 1 || check_in_range(time_start, time_end, time_order) == 0)
	// 				{
	// 					searched_orders.add(local_order.getOrderID());
	// 				}
	// 			}
	// 		}
	// 	}
	// 	return sort_ids(searched_orders);
	// }
	//
	// private int check_in_range(int start, int end, int value)
	// {
	// 	if(value > start && value < end)
	// 	{
	// 		return 1;
	// 	}
	// 	if(value == start && value == end)
	// 	{
	// 		return 0;
	// 	}
	// 	return -1;
	// }
	// public ArrayList<String> getOrdersByMonth(int month)
	// {
	// 	ArrayList<String> searched_orders = new ArrayList<String>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		if(	order_datetime.getMonthValue() == month )
	// 			{
	// 				searched_orders.add(local_order.getOrderID());
	// 			}

	// 	}
	// 	return sort_ids(searched_orders);
	// }

	// public ArrayList<String> getOrdersByDate(int date,int month)
	// {
	// 	ArrayList<String> searched_orders = new ArrayList<String>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		if(	order_datetime.getMonthValue() == month &&
	// 			order_datetime.getDayOfMonth() == date )
	// 			{
	// 				searched_orders.add(local_order.getOrderID());
	// 			}

	// 	}
	// 	return sort_ids(searched_orders);
	// }

	// public ArrayList<String> getOrdersByTime(int time_hour,int date,int month)
	// {
	// 	ArrayList<String> searched_orders = new ArrayList<String>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		if(	order_datetime.getMonthValue() == month &&
	// 			order_datetime.getDayOfMonth() == date &&
	// 			order_datetime.getHour() == time_hour)
	// 			{
	// 				searched_orders.add(local_order.getOrderID());
	// 			}

	// 	}
	// 	return sort_ids(searched_orders);
	// }

	// public ArrayList<String> getOrderbyMonthTableId(int month,int year, String tableId_input)
	// {
	// 	ArrayList<String> orders_month = getOrdersByMonth(month);
	// 	ArrayList<String> orders_table = new ArrayList<String>();
	// 	for(int i =0; i<orders_month.size(); i++)
	// 	{
	// 		String local_order_id = orders_month.get(i);
	// 		if(local_order_id.substring(local_order_id.length() - 3) == tableId_input)
	// 		{
	// 			orders_table.add(local_order_id);
	// 		}
	// 	}
	// 	return sort_ids(orders_table);
	// }
	// get order objects (internal purposes)
	// private ArrayList<Order> getOrdersObjtimePeriod(LocalDateTime start, LocalDateTime end)
	// {
	// 	ArrayList<Order> searched_orders = new ArrayList<Order>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		boolean isAfter = order_datetime.isAfter(start);
	// 		boolean isBefore = order_datetime.isBefore(end);
	// 		if(isBefore && isAfter)
	// 		{
	// 			searched_orders.add(local_order);
	// 		}
	// 	}
	// 	return searched_orders;
	// }

	// private ArrayList<Order> getOrdersObjByMonth(int month,int year)
	// {
	// 	ArrayList<Order> searched_orders = new ArrayList<Order>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		if(order_datetime.getYear() == year &&
	// 			order_datetime.getMonthValue() == month )
	// 			{
	// 				searched_orders.add(local_order);
	// 			}

	// 	}
	// 	return searched_orders;
	// }

	// private ArrayList<Order> getOrdersObjByDate(int date,int month,int year)
	// {
	// 	ArrayList<Order> searched_orders = new ArrayList<Order>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		if(order_datetime.getYear() == year &&
	// 			order_datetime.getMonthValue() == month &&
	// 			order_datetime.getDayOfMonth() == date )
	// 			{
	// 				searched_orders.add(local_order);
	// 			}

	// 	}
	// 	return searched_orders;
	// }

	// private ArrayList<Order> getOrdersObjByTime(int time_hour,int date,int month,int year)
	// {
	// 	ArrayList<Order> searched_orders = new ArrayList<Order>();
	// 	for(int i=0; i<all_orders_.size() ;i++)
	// 	{
	// 		Order local_order = all_orders_.get(i);
	// 		LocalDateTime order_datetime = local_order.getDateTime();
	// 		if(order_datetime.getYear() == year &&
	// 			order_datetime.getMonthValue() == month &&
	// 			order_datetime.getDayOfMonth() == date &&
	// 			order_datetime.getHour() == time_hour)
	// 			{
	// 				searched_orders.add(local_order);
	// 			}

	// 	}
	// 	return searched_orders;
	// }
}