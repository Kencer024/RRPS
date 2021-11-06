package Assignment;

import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

import java.time.LocalDateTime;
import java.util.Collections;  


public class SalesDatabase
{
	// ArrayList
	private ArrayList<Order> all_orders_;
	private double total_revenue;

	public SalesDatabase()
	{
		all_orders_ = new ArrayList<Order>();
		this.total_revenue = 0.0;
	}
	
	public void computeTotalRevenue()
    {
        for(int i = 0; i< this.all_orders_.size();i++)
        {
            Order local_order = this.all_orders_.get(i);
            total_revenue+= local_order.getBill();
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

	public ArrayList<String> getOrdersByMonth(int month)
	{
		ArrayList<String> searched_orders = new ArrayList<String>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			if(	order_datetime.getMonthValue() == month )
				{
					searched_orders.add(local_order.getOrderID());
				}

		}
		return sort_ids(searched_orders);
	}
	
	public ArrayList<String> getOrdersByDate(int date,int month)
	{
		ArrayList<String> searched_orders = new ArrayList<String>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			if(	order_datetime.getMonthValue() == month &&
				order_datetime.getDayOfMonth() == date )
				{
					searched_orders.add(local_order.getOrderID());
				}

		}
		return sort_ids(searched_orders);
	}

	public ArrayList<String> getOrdersByTime(int time_hour,int date,int month)
	{
		ArrayList<String> searched_orders = new ArrayList<String>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			if(	order_datetime.getMonthValue() == month &&
				order_datetime.getDayOfMonth() == date &&
				order_datetime.getHour() == time_hour)
				{
					searched_orders.add(local_order.getOrderID());
				}

		}
		return sort_ids(searched_orders);
	}

	public ArrayList<String> getOrderbyMonthTableId(int month,int year, String tableId_input)
	{
		ArrayList<String> orders_month = getOrdersByMonth(month);
		ArrayList<String> orders_table = new ArrayList<String>();
		for(int i =0; i<orders_month.size(); i++)
		{
			String local_order_id = orders_month.get(i);
			if(local_order_id.substring(local_order_id.length() - 3) == tableId_input)
			{
				orders_table.add(local_order_id);
			}
		} 
		return sort_ids(orders_table);
	}
	// get order objects (internal purposes)
	public ArrayList<Order> getOrdersObjtimePeriod(LocalDateTime start, LocalDateTime end)
	{
		ArrayList<Order> searched_orders = new ArrayList<Order>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			boolean isAfter = order_datetime.isAfter(start);
			boolean isBefore = order_datetime.isBefore(end);
			if(isBefore && isAfter)
			{
				searched_orders.add(local_order);
			}
		}
		return searched_orders;
	}

	private ArrayList<Order> getOrdersObjByMonth(int month,int year)
	{
		ArrayList<Order> searched_orders = new ArrayList<Order>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			if(order_datetime.getYear() == year &&
				order_datetime.getMonthValue() == month )
				{
					searched_orders.add(local_order);
				}

		}
		return searched_orders;
	}
	
	private ArrayList<Order> getOrdersObjByDate(int date,int month,int year)
	{
		ArrayList<Order> searched_orders = new ArrayList<Order>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			if(order_datetime.getYear() == year &&
				order_datetime.getMonthValue() == month &&
				order_datetime.getDayOfMonth() == date )
				{
					searched_orders.add(local_order);
				}

		}
		return searched_orders;
	}

	private ArrayList<Order> getOrdersObjByTime(int time_hour,int date,int month,int year)
	{
		ArrayList<Order> searched_orders = new ArrayList<Order>();
		for(int i=0; i<all_orders_.size() ;i++)
		{
			Order local_order = all_orders_.get(i);
			LocalDateTime order_datetime = local_order.getDateTime();
			if(order_datetime.getYear() == year &&
				order_datetime.getMonthValue() == month &&
				order_datetime.getDayOfMonth() == date &&
				order_datetime.getHour() == time_hour)
				{
					searched_orders.add(local_order);
				}

		}
		return searched_orders;
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
}