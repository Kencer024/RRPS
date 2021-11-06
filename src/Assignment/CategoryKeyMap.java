package Assignment;


import java.util.ArrayList;

public class CategoryKeyMap
{
	private ArrayList<Pair<String, String>> categoriesToKeys;
	private ArrayList<Pair<String, String>> keysToCategories;

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

	public String getKey(String type)
	{
		// System.out.println(type);
		// System.out.println(categoriesToKeys.get(binarySearchCategory(type)).getSecond());
		return categoriesToKeys.get(binarySearchCategory(type)).getSecond();
	}

	public String getCategory(String key)
	{
		return keysToCategories.get(binarySearchKey(key)).getSecond();
	}

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
	public void printkey()
	{
		for(int i = 0; i < keysToCategories.size(); i++)
		{
			System.out.println(keysToCategories.get(i).getFirst() + " : " + keysToCategories.get(i).getSecond());
		}
		System.out.print("\n");
		for(int i = 0; i < categoriesToKeys.size(); i++)
		{
			System.out.println(categoriesToKeys.get(i).getFirst() + " : " + categoriesToKeys.get(i).getSecond());
		}
	}
}