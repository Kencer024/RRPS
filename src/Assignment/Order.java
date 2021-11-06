package Assignment;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.text.DateFormat;  

public class Order
{
	private String tableId;
	private int pax;
	private LocalDateTime dateTime = LocalDateTime.now();
	private Boolean hasMembership;
	private ArrayList<Pair<String, Integer>> items;
	private ArrayList<Pair<String, Integer>> sets;
	private float baseCost, subTotal, memberDiscount, svcCharge, gst;
	private String orderID;

	public Order(String tableId_, int pax_)
	{
		tableId = tableId_;
		pax = pax_;
		items = new ArrayList<Pair<String, Integer>>(0);
		sets = new ArrayList<Pair<String, Integer>>(0);
		dateTime = LocalDateTime.now();
		hasMembership = true;
		subTotal = 0;
		baseCost = 0;
		svcCharge = 0;
		gst = 0;
		this.generate_orderID();
	}

	public void generate_orderID()
	{
		orderID = tableId + dateTime.toString();
	}
	public String getOrderID()
	{
		return orderID;
	}
	public String getTableId()
	{
		return tableId;
	}
	public int getPax()
	{
		return pax;
	}
	public LocalDateTime getDateTime()
	{
		return dateTime;
	}
	public Boolean hasMembership()
	{
		return hasMembership;
	}

	public void printDateTime()
	{
		System.out.println(dateTime);
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

	public float computeBill(MenuList menu)
	{
		this.subTotal = 0f;
		for(int i=0; i<items.size(); i++)
		{
			Pair<String,Integer>  local_pair = items.get(i); 
			String local_item_id = local_pair.getFirst();
			Item local_item = menu.getItem(local_item_id);
			float cost_local_item = local_item.getSaleCost();
			float base_local_item = local_item.getBaseCost();
			subTotal += (cost_local_item*local_pair.getSecond());
			baseCost += (base_local_item*local_pair.getSecond());
//			System.out.println("Bill: " + this.bill);
		}
		if(hasMembership) memberDiscount = (float) (subTotal * 0.1);
		svcCharge = (float) ((subTotal - memberDiscount) * 0.1);
		gst = (float) ((subTotal - memberDiscount + svcCharge) * 0.07);
		return subTotal - memberDiscount + svcCharge + gst;
	}

	public void printBill(MenuList menu)
	{
		String printLine = "";
		int spaces = 0;

		// width = 58
		System.out.print("|========================================================|\n"
				+ "|                      RECIEPT                           |\n"
				+ "|========================================================|\n");
		System.out.println("|  " + dateTime.toString().substring(0, 19) + "                                   |");
		System.out.println("|  Cashier :                                             |");
		printLine = "|  Table : " + tableId + "\tPax : " + pax;
		spaces = 58 - printLine.length() - 4;
		for(int j = 0; j < spaces; j++)
		{
			printLine += " ";
		}
		printLine += " |";
		System.out.println(printLine);
		System.out.println("|========================================================|");

		for(int i = 0; i < items.size(); i++)
		{
			printLine = "|| " + items.get(i).getSecond() + "\t" + menu.getItem(items.get(i).getFirst()).getName();
			String saleCost = String.valueOf(menu.getItem(items.get(i).getFirst()).getSaleCost() * items.get(i).getSecond());
			spaces = 58 - printLine.length() - saleCost.length() - 6;
			for(int j = 0; j < spaces; j++)
			{
				printLine += " ";
			}
			printLine += saleCost + " ||";
			System.out.println(printLine);
		}
//		System.out.println("||                                                      ||");
		printLine = "|| SUBTOTAL";
		computeBill(menu);
		spaces = 58 - String.valueOf(subTotal).length() - 14;
		for(int j = 0; j < spaces; j++)
		{
			printLine += " ";
		}
		printLine += subTotal + " ||";
		System.out.println(printLine);

		printLine = "|| SVC CHARGE 10%";
		spaces = 58 - String.valueOf(Math.round(svcCharge * 100.0)/100.0).length() - 20;
		for(int j = 0; j < spaces; j++)
		{
			printLine += " ";
		}
		printLine += Math.round(svcCharge * 100.0)/100.0 + " ||";
		System.out.println(printLine);
		printLine = "|| GST 7%";
		spaces = 58 - String.valueOf(Math.round(gst * 100.0)/100.0).length() - 12;
		for(int j = 0; j < spaces; j++)
		{
			printLine += " ";
		}
		printLine += Math.round(gst * 100.0)/100.0 + " ||";
		System.out.println(printLine);

		System.out.println("|========================================================|");

		printLine = "|| TOTAL";
		spaces = 58 - String.valueOf(subTotal + svcCharge + gst).length() - 11;
		for(int j = 0; j < spaces; j++)
		{
			printLine += " ";
		}
		printLine += subTotal + svcCharge + gst + " ||";
		System.out.println(printLine);

		System.out.println("|========================================================|");
	}

	public float getBill(){ 
		System.out.println("Bill: " + subTotal + svcCharge + gst);
		return subTotal + svcCharge + gst;}

}