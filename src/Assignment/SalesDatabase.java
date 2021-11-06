package Assignment;

import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;  


public class SalesDatabase
{
	// ArrayList
	private ArrayList<Order> all_orders_;
	private float total_revenue;
	private float total_profit;
	private float total_basecost;
	private float total_membership_discount;
	private HashMap<Integer, Float> year_revenue = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> year_profit = new HashMap<Integer, Float>();

	public SalesDatabase()
	{
		all_orders_ = new ArrayList<Order>();
		this.total_revenue = 0;
		this.total_basecost = 0;
		this.total_profit = 0;
		this.total_membership_discount = 0;

		for(int i = 1; i<13 ; i++)
		{
			year_revenue.put(i, 0f);
			year_profit.put(i,0f);
		}
	}
	
	public void analyseTotalOrders()
    {
        for(int i = 0; i< this.all_orders_.size();i++)
        {
            Order local_order = this.all_orders_.get(i);
            total_revenue+= local_order.getTotal();
			total_basecost+= local_order.getBaseCost();
			total_membership_discount+= local_order.getMemberDiscount();
			float profit = local_order.getTotal() - local_order.getBaseCost();
			total_profit+= profit ;

			int month = local_order.getDateTime().getMonthValue();
			year_revenue.put(month, year_revenue.get(month) + local_order.getTotal());
			year_profit.put(month, year_revenue.get(month) + profit);

        }
    }


	public ArrayList<String> getOrderstimePeriod(LocalDateTime start, LocalDateTime end)
	{
		ArrayList<String> searched_orders = new ArrayList<String>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			boolean isAfter = order_datetime.isAfter(start);
			boolean isBefore = order_datetime.isBefore(end);
			if(isBefore && isAfter)
			{
				searched_orders.add(local_order.getOrderID());
			}
		}
		return sort_ids(searched_orders);
	}

	public ArrayList<String> getOrderstimePeriodTableId(LocalDateTime start, LocalDateTime end, String tableId_input)
	{
		ArrayList<String> orders_table = new ArrayList<String>();
		ArrayList<String> all_orders_period = getOrderstimePeriod(start,end);
		for(int i =0; i<all_orders_period.size(); i++)
		{
			String local_order_id = all_orders_period.get(i);
			if(local_order_id.substring(local_order_id.length() - 3) == tableId_input)
			{
				orders_table.add(local_order_id);
			}
		} 
		return sort_ids(orders_table);
	}

	public Order getOrderInfo(String orderId_input)
	{
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			if(orderId_input == local_order.getOrderID())
				{
					return local_order;
				}

		}
		System.out.println("OrderID : " + orderId_input + " not found");
		return null;

	}

	private ArrayList<String> sort_ids(ArrayList<String> orderIds_input_unsorted)
	{
		Collections.sort(orderIds_input_unsorted);
		return orderIds_input_unsorted;
	}

	
	// not used
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