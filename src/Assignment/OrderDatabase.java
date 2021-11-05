import java.util.ArrayList;

public class OrderDatabase
{
	private ArrayList<Order> currentOrders;

	public OrderDatabase()
	{
		currentOrders = new ArrayList<Order>(0);
	}

	private int getOrderIndex(String tableId)
	{
		int left = 0, right = currentOrders.size() - 1, middle;

		//Binary insertion to the sorted list
		while(left < right)
		{
			middle = (left + right)/2;
			if(tableId.compareTo(currentOrders.get(middle).tableId) == 0)
			{
				return middle;
			}
			else if(tableId.compareTo(currentOrders.get(middle).tableId) > 0)
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

	public Order searchOrder(String tableId)
	{
		int index;
		for(index = 0; index < currentOrders.size(); index++)
		{
			if(currentOrders.get(index).tableId.matches(tableId))
			{
				return currentOrders.get(index);
			}
		}
		return null;
	}

	public ArrayList<Order> getCurrentOrders()
	{
		return currentOrders;
	}

	public void printCurrentOrders()
	{
		int index;
		for(index = 0; index < currentOrders.size(); index++)
		{
			System.out.println("Table " + currentOrders.get(index).getTableId());
		}
	}
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
			if(tableId.compareTo(currentOrders.get(middle).tableId) == 0)
			{
				System.out.println("Duplicate Order!");
				return;
			}
			else if(tableId.compareTo(currentOrders.get(middle).tableId) > 0)
			{
				left = middle + 1;
			}
			else
			{
				right = middle;
			}
		}
		if(tableId.compareTo(currentOrders.get(left).tableId) == 0)
		{
			System.out.println("Duplicate Order!");
			return;
		}
		currentOrders.add(left, newOrder);
	}

	public Boolean addItem(String tableId, String itemId, int amount)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(orderIndex).addItem(itemId, amount);
		return true;
	}

	public Boolean removeItem(String tableId, String itemId)
	{
		if(currentOrders.size() == 0)return false;
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).removeItem(itemId);
		return true;
	}

	public Boolean addSet(String tableId, String setId, int amount)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).addSet(setId, amount);
		return true;
	}

	public Boolean removeSet(String tableId, String setId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).removeSet(setId);
		return true;
	}

	public ArrayList<Pair<String, Integer>> listItems(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return null;
		return currentOrders.get(getOrderIndex(tableId)).listItems();
	}
	public ArrayList<Pair<String, Integer>> listSets(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return null;
		return currentOrders.get(getOrderIndex(tableId)).listSets();
	}
	public Boolean clearAll(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).clearAll();
		return true;
	}

	public Boolean printItems(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).printItems();
		return true;
	}

	public Boolean printSets(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).printSets();
		return true;
	}

	public Boolean applyMembership(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		currentOrders.get(getOrderIndex(tableId)).applyMembership();
		return true;
	}

	public Boolean isInOrderList(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return false;
		return true;
	}

	public float computeBill(String tableId, MenuList menu)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders.get(orderIndex).tableId.matches(tableId)) return 0;
		return currentOrders.get(getOrderIndex(tableId)).computeBill(menu);
	}

	public int size()
	{
		return currentOrders.size();
	}
}