package Assignment;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

/** A class of order object to represent each order
 *
 */
public class Order
{
	private String tableId;
	private int pax;
	private LocalDateTime dateTime = LocalDateTime.now();
	private Boolean hasMembership;
	private ArrayList<Pair<String, Integer>> items;
	private ArrayList<Pair<String, Integer>> sets;
	private float baseCost, subTotal, memberDiscount, svcCharge, gst;
	private String orderId;

	/** creating the order
	 * @param tableId represents the table identity no.
	 * @param pax represents the no. of customer(s)
	 */
	public Order(String tableId, int pax)
	{
		this.tableId = tableId;
		this.pax = pax;
		items = new ArrayList<Pair<String, Integer>>(0);
		sets = new ArrayList<Pair<String, Integer>>(0);
		dateTime = LocalDateTime.now();
		hasMembership = true;
		subTotal = 0;
		baseCost = 0;
		svcCharge = 0;
		gst = 0;
		this.generateOrderId();
	}

	/**
	 * Generates the order identity no.
	 */
	public void generateOrderId()
	{
		orderId = dateTime.toString()+ tableId;
	}

	/** Getting the order identity no.
	 *
	 * @return A string that represents the order identity
	 */
	public String getOrderId()
	{
		return orderId;
	}

	/** Getting the table identity no.
	 *
	 * @return A string that represents the table identity
	 */
	public String getTableId()
	{
		return tableId;
	}

	/** Getting the no. of customer(s)
	 *
	 * @return An integer that represents the no. of customer
	 */
	public int getPax()
	{
		return pax;
	}

	/** Getting the local date and time
	 *
	 * @return LocalDateTime of the current date time
	 */
	public LocalDateTime getDateTime()
	{
		return dateTime;
	}

	/** Checks for membership
	 *
	 * @return A boolean representing the membership status
	 */
	public Boolean hasMembership()
	{
		return hasMembership;
	}

	/**
	 * Printing the date and time
	 */
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

	/** adding the number of item ordered
	 *
	 * @param id A string that represents the identity of the item that is going to be added
	 * @param amount An integer that represents the no. of item ordered
	 */
	public void addItem(String id, int amount)
	{
		Pair<String, Integer> tmp = new Pair<String, Integer>(id, amount);
		if(items.size() == 0)items.add(tmp);
		else items.add(getItemIndex(id), tmp);
	}

	/** Removes an item from the order
	 *
	 * @param id A string that represents the identity of the item that is going to be removed
	 * @return Boolean that returns true if the item exists and is found and returns false otherwise
	 */
	public Boolean removeItem(String id)
	{
		if(items.size() == 0)return false;
		int itemIndex = getItemIndex(id);
		if(!items.get(itemIndex).getFirst().matches(id)) return false;
		items.remove(itemIndex);
		return true;
	}

	/** Adding a set into the order
	 *
	 * @param id A string that represents the identity of the item that is going to be added
	 * @param amount An integer that represents the no. of item ordered
	 */
	public void addSet(String id, int amount)
	{
		Pair<String, Integer> tmp = new Pair<String, Integer>(id, amount);
		if(sets.size() == 0)sets.add(tmp);
		else sets.add(getSetIndex(id), tmp);
	}

	/** Removing the set from the order
	 *
	 * @param id A string that represents the identity of the item that is going to be removed
	 * @return
	 */
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

	/**
	 * Clearing all the item(s) and/or set(s)
	 */
	public void clearAll()
	{
		items.clear();
		sets.clear();
	}

	/**
	 * Prints all the items
	 */
	public void printItems()
	{
		System.out.println("Items : ");
		for(int i = 0; i < items.size(); i++)
		{
			System.out.println(items.get(i).getFirst() + " Quantity = " + items.get(i).getSecond());
		}
	}

	/**
	 * Prints all the sets
	 */
	public void printSets()
	{
		System.out.println("Sets : ");
		for(int i = 0; i < sets.size(); i++)
		{
			System.out.println(sets.get(i).getFirst() + " Quantity = " + sets.get(i).getSecond());
		}
	}

	/** Applies membership for the customer, where attribute hasMembership will be true once called
	 *
	 */
	public void applyMembership()
	{
		hasMembership = true;
	}

	/** Computing the bill which is (subTotal - memberDiscount + svcCharge + gst)
	 *
	 * @param menu MenuList object that represents list of items in menu
	 * @return A float that represents the computed bill
	 */
	public float computeBill(MenuList menu)
	{
		this.subTotal = 0f;
		this.baseCost = 0f;
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
		for(int i=0; i<sets.size(); i++)
		{
			Pair<String, Integer> local_set_pair = sets.get(i);
			List<String> local_set = menu.getSet(local_set_pair.getFirst()).getallItemIds();
			for(int j = 0; j < local_set.size(); j++)
			{
				subTotal += menu.getItem(local_set.get(j)).getSaleCost() * local_set_pair.getSecond();
				baseCost += menu.getItem(local_set.get(j)).getBaseCost() * local_set_pair.getSecond();
			}

		}
		if(hasMembership) memberDiscount = (float) (subTotal * 0.1);
		svcCharge = (float) ((subTotal - memberDiscount) * 0.1);
		gst = (float) ((subTotal - memberDiscount + svcCharge) * 0.07);
		return subTotal - memberDiscount + svcCharge + gst;
	}

	/** Prints the bill for a MenuList object
	 *
	 * @param menu MenuList object that represents an array of item
	 */
	public void printBill(MenuList menu)
	{
		String printLine = "";
		int spaces = 0;

		// width = 58
		System.out.print("|========================================================|\n"
				+ "|                         RECEIPT                        |\n"
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
		for(int i = 0; i < sets.size(); i++)
		{
			printLine = "|| " + sets.get(i).getSecond() + "\t" + menu.getSet(sets.get(i).getFirst()).getName();
			String saleCost = String.valueOf(menu.getSet(items.get(i).getFirst()).getSaleCost() * sets.get(i).getSecond());
			spaces = 58 - printLine.length() - saleCost.length() - 6;
			for(int j = 0; j < spaces; j++)
			{
				printLine += " ";
			}
			printLine += saleCost + " ||";
			System.out.println(printLine);

			List<String> local_set = menu.getSet(sets.get(i).getFirst()).getallItemIds();
			for(int j = 0; j < local_set.size(); j++)
			{
				printLine = "||        - " + menu.getItem(local_set.get(j)).getName();
				spaces = 58 - printLine.length() - 2;
				for(int k = 0; k < spaces; k++)
				{
					printLine += " ";
				}
				printLine += "||";
				System.out.println(printLine);
			}
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

		if(hasMembership)
		{
			printLine = "|| MEMBERSHIP DSCT -10%";
			spaces = 58 - String.valueOf(-Math.round(memberDiscount * 100.0)/100.0).length() - 26;
			for(int j = 0; j < spaces; j++)
			{
				printLine += " ";
			}
			printLine += "-" + Math.round(memberDiscount * 100.0)/100.0 + " ||";
			System.out.println(printLine);
		}

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
		spaces = 58 - String.valueOf(Math.round((subTotal - memberDiscount + svcCharge + gst) * 100.0) / 100.0).length() - 11;
		for(int j = 0; j < spaces; j++)
		{
			printLine += " ";
		}
		printLine += Math.round((subTotal - memberDiscount + svcCharge + gst) * 100.0) / 100.0 + " ||";
		System.out.println(printLine);

		System.out.println("|========================================================|");
	}

	/** Gets the base cost of an order
	 *
	 * @return A float representing base cost
	 */
	public float getBaseCost()
	{
		return baseCost;
	}

	/** Gets the subtotal of an order
	 *
	 * @return A float representing subtotal
	 */
	public float getSubTotal()
	{
		return subTotal;
	}

	/** Gets the membership discount of an order
	 *
	 * @return A float representing membership discount
	 */
	public float getMemberDiscount()
	{
		return memberDiscount;
	}

	/** Gets the service charge of an order
	 *
	 * @return A float representing service charge
	 */
	public float getSvcCharge()
	{
		return svcCharge;
	}

	/** Gets the total cost of an order
	 *
	 * @return A float representing total cost
	 */
	public float getTotal()
	{
		return subTotal - memberDiscount + svcCharge + gst;
	}

//	// For Cloning
//	private String tableId;
//	private int pax;
//	private LocalDateTime dateTime = LocalDateTime.now();
//	private Boolean hasMembership;
//	private ArrayList<Pair<String, Integer>> items;
//	private ArrayList<Pair<String, Integer>> sets;
//	private float baseCost, subTotal, memberDiscount, svcCharge, gst;
//	private String orderID;
//
//	public void setDateTime(LocalDateTime dt)
//	{
//		dateTime = dt;
//	}
//	public void
//	public Order clone()
//	{
//		Order cloneObj = new Order(tableId, pax);
//		cloneObj.setd
//
//
//	}
}