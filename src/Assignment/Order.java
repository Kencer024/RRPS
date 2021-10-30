import javafx.util.Pair;
import java.util.ArrayList;

public class Order
{
	String tableId;
	int pax;
	Boolean hasMembership;
	ArrayList<Pair<String, Integer>> items;
	ArrayList<Pair<String, Integer>> sets;

	public Order(int tableId_, int pax_)
	{
		tableId = tableId_;
		pax = pax_;
		items = new ArrayList<Pair<String, Integer>>(0);
		sets = new ArrayList<Pair<String, Integer>>(0);
	}

	private int getItemIndex(String id)
	{
		int left = 0, right = items.size(), middle;

		//Binary search
		while(left + 1 < right)
		{
			middle = (left + right)/2;
			if(!id.compareTo(items.get(middle).getKey()))
			{
				return middle;
			}
			else if(id.compareTo(items.get(middle).getKey() == 1))
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

	private int getSetIndex(String id)
	{
		int left = 0, right = sets.size(), middle;

		//Binary search
		while(left + 1 < right)
		{
			middle = (left + right)/2;
			if(!id.compareTo(sets.get(middle).getKey()))
			{
				return middle;
			}
			else if(id.compareTo(sets.get(middle).getKey() == 1))
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

	public void addItem(String id, int amount)
	{
		ArrayList<Pair<String, Integer>> tmp = new ArrayList<Pair<String, Integer>>(id, amount);
		items.add(getItemIndex(id) + 1, tmp);
	}
	public Boolean removeItem(String id)
	{
		int itemIndex = getItemIndex(id);
		if(!items.get(itemIndex).getKey().matches(id)) return false;
		items.remove(itemIndex);
	}
	public void addSet(String id, int amount)
	{
		ArrayList<Pair<String, Integer>> tmp = new ArrayList<Pair<String, Integer>>(id, amount);
		sets.add(getSetIndex(id) + 1, tmp);
	}
	public Boolean removeSet(String id)
	{
		int setIndex = getSetIndex(id);
		if(!sets.get(setIndex).getKey().matches(id)) return false;
		sets.remove(setIndex);
	}

	public ArrayList<Pair<String, Integer>> listItems()
	{
		return items;
	}

	public ArrayList<Pair<String, Integer>> listSets()
	{
		return sets;
	}

	public void clearAll()
	{
		items.clear();
		sets.clear();
	}

	public void printItems()
	{

	}

	public void printSets()
	{

	}

	public void applyMembership()
	{
		hasMembership = true;
	}
}