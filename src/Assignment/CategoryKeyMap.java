package Assignment;


import java.util.ArrayList;

/**
 * 	Stores all the keys and category names used as the starting digit for indexing items and promotional sets in the menu.
 * 	Can hold a maximum of 62 categories limited by the single alpha-numeric key
 */
public class CategoryKeyMap
{
	private ArrayList<Pair<String, String>> categoriesToKeys;
	private ArrayList<Pair<String, String>> keysToCategories;

	/**e
	 * 	Constructor to initialize the containers used to store the keys and category names.
	 */
	public CategoryKeyMap()
	{
		categoriesToKeys = new ArrayList<Pair<String, String>>();
		keysToCategories = new ArrayList<Pair<String, String>>();
	}

	private int binarySearchKey(String key)
	{
		int left = 0, right = keysToCategories.size() - 1, center;
		while(left < right)
		{
			center = (left + right) / 2;
			if(keysToCategories.get(center).getFirst().compareTo(key) == 0)
			{
				return center;
			}
			else if(keysToCategories.get(center).getFirst().compareTo(key) < 0)
			{
				left = center + 1;
			}
			else
			{
				right = center;
			}
		}
		if(keysToCategories.get(left).getFirst().compareTo(key) < 0)
			return left + 1;
		else
			return left;
	}

	private int binarySearchCategory(String type)
	{
		int left = 0, right = categoriesToKeys.size() - 1, center;
		while(left < right)
		{
			center = (left + right) / 2;
			if(categoriesToKeys.get(center).getFirst().compareTo(type) == 0)
			{
				return center;
			}
			else if(categoriesToKeys.get(center).getFirst().compareTo(type) < 0)
			{
				left = center + 1;
			}
			else
			{
				right = center;
			}
		}
		// System.out.print(categoriesToKeys.get(left).getFirst().compareTo(type) + type + "\n");
		if(categoriesToKeys.get(left).getFirst().compareTo(type) < 0)
			return left + 1;
		else
			return left;
	}

	/** Converts a full category name to its corresponding shortened key. Prints an error message if the category isn't
	 * found in this CategoryKeyMap and returns an empty string.
	 *
	 * @param type A String representing the full category name of the items/promotional sets in the menu.
	 * @return A 1-character String representing the Shortened key associated to the category name.
	 */
	public String getKey(String type)
	{
		String key = categoriesToKeys.get(binarySearchCategory(type)).getSecond();
		if(key.compareTo(type) == 0)
		{
			System.out.println("Invalid Type");
			return "";
		}
		else return key;
	}

	/** Converts a shortened key to its corresponding full category name. Prints an error message if the key isn't
	 * found in this CategoryKeyMap and returns an empty string.
	 *
	 * @param key A 1-character String representing the shortened key associated to the category name.
	 * @return A String representing the full category name for the items/promotional sets in the menu.
	 */
	public String getCategory(String key)
	{
		String type =  keysToCategories.get(binarySearchKey(key)).getSecond();
		if(type.compareTo(key) == 0)
		{
			System.out.println("Invalid Key");
			return "";
		}
		else return type;
	}

	/** Appends a new category to the object's library as well as automatically generates a new corresponding key.
	 * Prints an error message when there is an attempt to call the method when the maximum number of stored categories
	 * has already been reached.
	 *
	 * @param type A String representing the full category name for the items/promotional sets in the menu.
	 */
	public void addCategory(String type)
	{
		String previousKey;
		if(keysToCategories.size() == 0)
		{
			keysToCategories.add(new Pair("0", type));
			categoriesToKeys.add(new Pair(type, "0"));
		}
		else
		{
			previousKey = keysToCategories.get(keysToCategories.size() - 1).getFirst();
			if(StringUtil.incrementString(previousKey, 1).toString().length() > previousKey.length())
			{
				System.out.println("Key Overflow!");
			}
			else
			{
				keysToCategories.add(new Pair(StringUtil.incrementString(previousKey, 1).toString(), type));
				categoriesToKeys.add(binarySearchCategory(type), new Pair(type, StringUtil.incrementString(previousKey, 1).toString()));
				// System.out.println(binarySearchCategory(type));
			}
		}
	}

	/** Returns an ArrayList of all the categories within this CategoryKeyMap
	 *
	 * @return the ArrayList of all the categories
	 */
	public ArrayList<String> getCategoryList()
	{
		ArrayList<String> categories = new ArrayList<String>();
		for(int i = 0; i < keysToCategories.size(); i++)
		{
			categories.add(keysToCategories.get(i).getSecond());
		}
		return categories;
	}

}