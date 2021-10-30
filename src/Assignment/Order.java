import java.util.ArrayList;

public class Order
{
	String tableId;
	int pax;
	Boolean hasMembership;
	ArrayList<Pair<String, Integer>> items;
	ArrayList<Pair<String, Integer>> sets;

	public Order(String tableId_, int pax_)
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
			if(id.compareTo(items.get(middle).getFirst()) == 0)
			{
				return middle;
			}
			else if(id.compareTo(items.get(middle).getFirst()) == 1)
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
			if(id.compareTo(sets.get(middle).getFirst()) == 0)
			{
				return middle;
			}
			else if(id.compareTo(sets.get(middle).getFirst()) == 1)
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
		Pair<String, Integer> tmp = new Pair<String, Integer>(id, amount);
		items.add(getItemIndex(id) + 1, tmp);
	}
	public Boolean removeItem(String id)
	{
		int itemIndex = getItemIndex(id);
		if(!items.get(itemIndex).getFirst().matches(id)) return false;
		items.remove(itemIndex);
		return true;
	}
	public void addSet(String id, int amount)
	{
		Pair<String, Integer> tmp = new Pair<String, Integer>(id, amount);
		sets.add(getSetIndex(id) + 1, tmp);
	}
	public Boolean removeSet(String id)
	{
		int setIndex = getSetIndex(id);
		if(!sets.get(setIndex).getFirst().matches(id)) return false;
		sets.remove(setIndex);
		return true;
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