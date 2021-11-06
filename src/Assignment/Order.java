package Assignment;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Order
{
	String tableId;
	int pax;
	LocalDateTime dateTime = LocalDateTime.now();
	Boolean hasMembership;
	ArrayList<Pair<String, Integer>> items;
	ArrayList<Pair<String, Integer>> sets;
	private float bill;

	public Order(String tableId_, int pax_)
	{
		tableId = tableId_;
		pax = pax_;
		items = new ArrayList<Pair<String, Integer>>(0);
		sets = new ArrayList<Pair<String, Integer>>(0);
		dateTime = LocalDateTime.now();
		bill = 0;
	}

	private int getItemIndex(String id)
	{
		int left = 0, right = items.size() - 1, middle = 0;

		//Binary search
		while(left < right)
		{
			middle = (left + right)/2;
			if(id.compareTo(items.get(middle).getFirst()) == 0)
			{
				return middle;
			}
			else if(id.compareTo(items.get(middle).getFirst()) > 0)
			{
				left = middle + 1;
			}
			else
			{
				right = middle;
			}
		}
		if(id.compareTo(items.get(middle).getFirst()) > 0)
			return left + 1;
		else
			return left;
	}

	private int getSetIndex(String id)
	{
		int left = 0, right = sets.size() - 1, middle = 0;

		//Binary search
		while(left < right)
		{
			middle = (left + right)/2;
			if(id.compareTo(sets.get(middle).getFirst()) == 0)
			{
				return middle;
			}
			else if(id.compareTo(sets.get(middle).getFirst()) > 0)
			{
				left = middle + 1;
			}
			else
			{
				right = middle;
			}
		}
		if(id.compareTo(sets.get(middle).getFirst()) > 0)
			return left + 1;
		else
			return left;
	}

	public void addItem(String id, int amount)
	{
		Pair<String, Integer> tmp = new Pair<String, Integer>(id, amount);
		if(items.size() == 0)items.add(tmp);
		else items.add(getItemIndex(id), tmp);
	}
	public Boolean removeItem(String id)
	{
		if(items.size() == 0)return false;
		int itemIndex = getItemIndex(id);
		if(!items.get(itemIndex).getFirst().matches(id)) return false;
		items.remove(itemIndex);
		return true;
	}
	public void addSet(String id, int amount)
	{
		Pair<String, Integer> tmp = new Pair<String, Integer>(id, amount);
		if(sets.size() == 0)sets.add(tmp);
		else sets.add(getSetIndex(id), tmp);
	}
	public Boolean removeSet(String id)
	{
		if(sets.size() == 0)return false;
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
		System.out.println("Items : ");
		for(int i = 0; i < items.size(); i++)
		{
			System.out.println(items.get(i).getFirst() + " Quantity = " + items.get(i).getSecond());
		}
	}

	public void printSets()
	{
		System.out.println("Sets : ");
		for(int i = 0; i < sets.size(); i++)
		{
			System.out.println(sets.get(i).getFirst() + " Quantity = " + sets.get(i).getSecond());
		}
	}

	public void applyMembership()
	{
		hasMembership = true;
	}

	public String getTableId()
	{
		return tableId;
	}

	public float computeBill(MenuList menu)
	{
		this.bill = 0f;
		for(int i=0; i<items.size(); i++)
		{
			Pair<String,Integer>  local_pair = items.get(i); 
			String local_item_id = local_pair.getFirst();
			Item local_item = menu.getItem(local_item_id);
			double cost_local_item = local_item.getSaleCost();
			this.bill+= (cost_local_item*local_pair.getSecond());
			System.out.println("Bill: " + this.bill);
		}
		return this.bill;
	}

	public float getBill(){ 
		System.out.println("Bill: " + this.bill);
		return this.bill;}

}