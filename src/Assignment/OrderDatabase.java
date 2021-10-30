import java.util.ArrayList;
import java.util.Pair;

public class OrderDatabase
{
	private ArrayList<Order> currentOrders;

	public OrderDatabase()
	{
		currentOrders = new ArrayList<Order>(0);
	}

	private int getOrderIndex(String tableId)
	{
		int left = 0, right = currentOrders.size(), middle;

		//Binary insertion to the sorted list
		while(right > left)
		{
			middle = (left + right)/2;
			if(!tableId.compareTo(currentOrders[middle].tableId))
			{
				return middle;
			}
			else if(tableId.compareTo(currentOrders[middle].tableId == 1))
			{
				left = middle;
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
			if(currentOrders[index].tableId.matches(tableId))
			{
				return currentOrders[index];
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
			if(currentOrders[index].tableId.matches(tableId))
			{
				
			}
		}
	}
	public void newOrder(String tableId, int pax)
	{
		Order newOrder = new Order(tableId, pax);
		int left = 0, right = currentOrders.size(), middle;

		//Binary insertion to the sorted list
		while(left + 1 < right)
		{
			middle = (left + right)/2;
			if(!tableId.compareTo(currentOrders[middle].tableId))
			{
				System.out.println("Duplicate Order!");
				return null;
			}
			else if(tableId.compareTo(currentOrders[middle].tableId == 1))
			{
				left = middle;
			}
			else
			{
				right = middle;
			}
		}
		middle = (left + right)/2;
		currentOrders.add(newOrder, middle);
	}

	public Boolean addItem(String tableId, String itemId, int amount)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[orderIndex].addItem(itemId, amount);
		return true;
	}

	public Boolean removeItem(String tableId, String itemId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].removeItem(itemId);
		return true;
	}

	public Boolean addSet(String tableId, String setId, int amount)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].addSet(setId, amount);
		return true;
	}

	public Boolean removeSet(String tableId, String setId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].removeSet(setId);
		return true;
	}

	public ArrayList<String> listItems(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].listItems(setId);
		return true;
	}
	public ArrayList<String> listSets(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].listSets(setId);
		return true;
	}
	public Boolean clearAll(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].clearAll();
		return true;
	}

	public Boolean printItems(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].printItems();
		return true;
	}

	public Boolean printSets(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].printSets();
		return true;
	}

	public Boolean applyMembership(String tableId)
	{
		int orderIndex = getOrderIndex(tableId);
		if(!currentOrders[orderIndex].tableId.matches(tableId)) return false;
		currentOrders[getOrderIndex(tableId)].applyMembership();
		return true;
	}
}