package Assignment;

import java.util.ArrayList;

/** Represents a class to store an array of orders to be called by the main function
 * to act as a data storage
 *
 */
public class OrderDatabase implements DatabaseInterface
{
	private ArrayList<Order> currentOrders;

	/** Initialising an array of Order
	 *
	 */
	public OrderDatabase()
	{
		currentOrders = new ArrayList<Order>();
	}

    public Order getOrderInfo(String orderId_input)
	{
		for(int i=0; i<this.currentOrders.size() ;i++)
		{
			Order local_order = this.currentOrders.get(i);
			if(orderId_input.equals(local_order.getOrderId()))
				{
					return local_order;
				}

		}
		System.out.println("OrderID : " + orderId_input + " not found");
		return null;
	}

	//NOT USED
    public void appendOrder(Order order_add)
	{
		currentOrders.add(order_add);
	}

    public int getTotalNumelements()
	{
		return this.currentOrders.size();
	}

	public int getOrderIndex(String tableId)
	{
		int left = 0, right = currentOrders.size() - 1, middle;

		//Binary insertion to the sorted list
		while(left < right)
		{
			middle = (left + right)/2;
			if(tableId.compareTo(currentOrders.get(middle).getTableId()) == 0)
			{
				return middle;
			}
			else if(tableId.compareTo(currentOrders.get(middle).getTableId()) > 0)
			{
				left = middle + 1;
			}
			else
			{
				right = middle;
			}
		}
		return left;
	}

//	/** Used to search for the order object by tableId
//	 *
//	 * @param tableId A string representing the identity of the table
//	 * @return Order object with the tableId input
//	 */
//	public int getOrderIndex(String tableId)
//	{
//		int index;
//		for(index = 0; index < currentOrders.size(); index++)
//		{
//			if(currentOrders.get(index).getTableId().matches(tableId))
//			{
//				return index;
//			}
//		}
//		return -1;
//	}

	/** Getter method to retrieve an array of order objects
	 *
	 * @return An array of order object
	 */
	public ArrayList<Order> getCurrentOrders()
	{
		return currentOrders;
	}

	/**
	 * Prints all the customer orders at that instance
	 */
	public void printCurrentOrders()
	{
		int index;
		for(index = 0; index < currentOrders.size(); index++)
		{
			System.out.println("Table " + currentOrders.get(index).getTableId());
		}
	}

	/** Creates a new order and searches for duplicates
	 *
	 * @param tableId A string representing the identity of the table
	 * @param pax An integer representing the current number of people
	 *            allocated to the table
	 */
	public void newOrder(String tableId, int pax)
	{
		Order newOrder = new Order(tableId, pax);
		int left = 0, right = currentOrders.size() - 1, middle;

		if(currentOrders.size() == 0)
		{
			currentOrders.add(0, newOrder);
			return;
		}
		//Binary insertion to the sorted list
		while(left < right)
		{
			middle = (left + right)/2;
			if(tableId.compareTo(currentOrders.get(middle).getTableId()) == 0)
			{
				System.out.println("Duplicate Order!");
				return;
			}
			else if(tableId.compareTo(currentOrders.get(middle).getTableId()) > 0)
			{
				left = middle + 1;
			}
			else
			{
				right = middle;
			}
		}
		if(tableId.compareTo(currentOrders.get(left).getTableId()) == 0)
		{
			System.out.println("Duplicate Order!");
			return;
		}
		currentOrders.add(left, newOrder);
	}

	/** Removes an order from the array of order
	 *
	 * @param tableId A string represent the identity of the table
	 * @return A new array of order that removed the order with the tableId inputted
	 */
	public Order removeOrder(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return null;
		Order tmp;
		tmp = currentOrders.get(orderIndex);
		currentOrders.remove(orderIndex);
		return tmp;
	}

	/** Adds items to the order, i.e add to cart
	 *
	 * @param tableId A string representing the table identity
	 * @param itemId A string representing the type of item to add
	 * @param amount An integer representing the cost of the item
	 * @return Boolean that returns true if the item is added successfully
	 * and false if order is not found
	 */
	public Boolean addFood(String tableId, String itemId, int amount)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		currentOrders.get(orderIndex).addFood(itemId, amount);
		return true;
	}

	/** Removes item from the order in the array of order
	 *
	 * @param tableId A string representing the table identity
	 * @param itemId A string representing the type of item to add
	 * @return Boolean that returns true if item is removed successfully and false
	 * if order is not found
	 */
	public Boolean removeFood(String tableId, String itemId)
	{
		if(currentOrders.size() == 0)return false;
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).removeFood(itemId);
		return true;
	}

//	/** Adds promotion set to the order object which is saved in an array of order
//	 *
//	 * @param tableId A string representing the table identity
//	 * @param setId A string representing the type of promotion set to add
//	 * @param amount An integer representing the cost of the promotion set
//	 * @return Boolean that returns true if the order with the tableId inputted is found
//	 * hence addition is successful, else false when no such order is found
//	 */
//	public Boolean addSet(String tableId, String setId, int amount)
//	{
//		int orderIndex = getOrderIndex(tableId);
//		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
//		currentOrders.get(getOrderIndex(tableId)).addSet(setId, amount);
//		return true;
//	}
//
//	/** Removes promotion set from the order object which is saved in an array of order
//	 *
//	 * @param tableId A string representing the table identity
//	 * @param setId A string representing the type of promotion set to add
//	 * @return Boolean that returns true if the order with the tableId inputted is found
//	 * hence removal is successful, else false when no such order is found
//	 */
//	public Boolean removeSet(String tableId, String setId)
//	{
//		int orderIndex = getOrderIndex(tableId);
//		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
//		currentOrders.get(getOrderIndex(tableId)).removeSet(setId);
//		return true;
//	}

//	/** Getter method to retrieve array of items of tableId with its respective items
//	 *
//	 * @param tableId A string representing the identity of table
//	 * @return Array of dictionary of the tableId and its respective item
//	 */
//	public ArrayList<Pair<String, Integer>> listItems(String tableId, MenuList menu)
//	{
//		int orderIndex = getOrderIndex(tableId);
//		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return null;
//		return currentOrders.get(getOrderIndex(tableId)).listItems(MenuList menu);
//	}
//
//	/** Getter method to retrieve array of items of tableId with its respective sets
//	 *
//	 * @param tableId A string representing the identity of table
//	 * @return Array of dictionary of the tableId and its respective sets
//	 */
//	public ArrayList<Pair<String, Integer>> listSets(String tableId, MenuList menu)
//	{
//		int orderIndex = getOrderIndex(tableId);
//		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return null;
//		return currentOrders.get(getOrderIndex(tableId)).listSets(MenuList menu);
//	}

	/** Clears the order of tableId
	 *
	 * @param tableId A string representing the identity of table
	 * @return Boolean that returns true if the order with the tableId inputted is found
	 * hence clearing is successful, else false when no such order is found
	 */
	public Boolean clearAll(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).clearAll();
		return true;
	}

	/** Prints the item with the tableId inputted
	 *
	 * @param tableId A string representing the identity of table
	 * @return Boolean that returns true if the order with the tableId inputted is found
	 * hence printing is successful, else false when no such order is found
	 */
	public Boolean printItems(String tableId, MenuList menu)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).printItems(menu);
		return true;
	}

	/** Prints the set with the tableId inputted
	 *
	 * @param tableId A string representing the identity of table
	 * @return Boolean that returns true if the order with the tableId inputted is found
	 * hence printing is successful, else false when no such order is found
	 */
	public Boolean printSets(String tableId, MenuList menu)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).printSets(menu);
		return true;
	}

	/** Applies membership for the order with tableId
	 *
	 * @param tableId A string representing the identity of table
	 * @return Boolean that returns true if the order with the tableId inputted is found
	 * hence addition is successful, else false when no such order is found
	 */
	public Boolean applyMembership(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).applyMembership();
		return true;
	}

	/** Checks if a table is in the order list
	 *
	 * @param tableId A string representing the identity of table
	 * @return Boolean that returns true if the order with the tableId inputted is found,
	 * else false when no such order is found
	 */
	public Boolean isInOrderList(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		return true;
	}

	/** Computes the bill of a specific order
	 *
	 * @param tableId A string representing the identity of table
	 * @param menu A class for holding all the full objects of items
	 *             and promotional sets in the menu
	 * @return A float representing the total bill calculated
	 */
	public float computeBill(String tableId, MenuList menu)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return 0;
		return currentOrders.get(getOrderIndex(tableId)).computeBill(menu);
	}

	/** Prints the bill of a specific order
	 *
	 * @param tableId A string representing the identity of table
	 * @param menu A class for holding all the full objects of items
	 *             and promotional sets in the menu
	 * @return Boolean that is true if the order is found and false otherwise
	 */
	public Boolean printBill(String tableId, MenuList menu)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).printBill(menu);
		return true;
	}

	/** Prints the date and time of a specific order when they first ordered
	 *
	 * @param tableId A string representing the identity of table
	 */
	public void printDateTime(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).getTableId().matches(tableId)) return;
		currentOrders.get(getOrderIndex(tableId)).printDateTime();
	}

	/** Returns the size of the current order
	 *
	 * @return An integer representing the size of current order
	 */
	public int size()
	{
		return currentOrders.size();
	}

}