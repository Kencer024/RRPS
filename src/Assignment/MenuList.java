package Assignment;

import java.lang.reflect.Array;
import java.util.*;

/**
 * A class for holding all the full objects of items and promotional sets in the menu.
 */
public class MenuList
{
    private ArrayList<Food> foods_ = new ArrayList<Food>();
    private CategoryKeyMap map = new CategoryKeyMap();

    public MenuList()
    {
        // Add all the categories of the menu to the map
        map.addCategory("Dessert");
        map.addCategory("Beef");
        map.addCategory("Fish");
        map.addCategory("Salads & Wraps");
        map.addCategory("Chicken");
        map.addCategory("Drinks");
        map.addCategory("Sides");

        map.addCategory("Promotion Set");
    }
    private int searchFoodIndex(String id)
    {
        int left = 0, right = foods_.size() - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            // System.out.print(id + " ");
            // System.out.println(center);
            if(foods_.get(center).getId().compareTo(id) == 0)
            {
                return center;
            }
            else if(foods_.get(center).getId().compareTo(id) < 0)
            {
                left = center + 1;
            }
            else
            {
                right = center;
            }
        }
        return left;
    }

    /**
     * Prints all the item and promotional set categories within this MenuList
     */
    public void printCategoryList()
    {
        ArrayList<String> categoryList = map.getCategoryList();
        for(int i = 0; i < categoryList.size(); i++)
        {
            System.out.println(i + " " + categoryList.get(i));
        }
    }

    /**
     * Returns the number of items within a given category
     * @param type the category type
     * @return the number of items
     */
    public int getCategorySize(String type)
    {
        String startCode = map.getKey(type);
        int count = 0;
        if(startCode != "")
        {
            for(int i = searchFoodIndex(startCode + "000"); i < foods_.size(); i++)
            {
                if(foods_.get(i).getId().substring(0, 1).compareTo(startCode) == 0)
                    if(foods_.get(i).getAvailability())
                        count++;
            }
        }
        return count;
    }

    /**
     * Returns the CategoryKeyMap of this MenuList
     * @return the CategoryKeyMap
     */
    public CategoryKeyMap getCategoryKeyMap()
    {
        return map;
    }

    /**
     * Returns a new item ID string starting that is available within this MenuList with the character associated to the inputted category. This ID can be used as
     * an index for a new item.
     * @param type the full category name that the new ID string would be generated based on
     * @return the new item ID string
     */
    public String getNewFoodId(String type)
    {
        if(foods_.size() == 0)
        {
            return map.getKey(type) + "000";
        }
        else
        {
            String lastId, nextType;
            if(map.getKey(type) == "z")
            {
                nextType = "{000";
            }
            else
            {
                nextType = StringUtil.incrementString(map.getKey(type), 1) + "000";
            }

            lastId = foods_.get(searchFoodIndex(nextType)).getId();
            // End of Array, category not found
            if(lastId.charAt(0) < map.getKey(type).charAt(0))
            {
                return map.getKey(type) + "000";
            }
            // End of Array, category found
            else if(lastId.charAt(0) == map.getKey(type).charAt(0))
            {
                return StringUtil.incrementString(lastId, 1);
            }
            // Start of Array, category not found
            else if(searchFoodIndex(nextType) == 0)
            {
                return map.getKey(type) + "000";
            }
            // Middle of Array
            else
            {
                // System.out.println(nextType);
                lastId = foods_.get(searchFoodIndex(nextType) - 1).getId();
                // Category not found
                if(lastId.charAt(0) < map.getKey(type).charAt(0))
                    return map.getKey(type) + "000";
                    // Category found
                else
                    return StringUtil.incrementString(lastId, 1);
            }
        }
    }

    /**
     * Returns the food object corresponding to the food ID string
     * @param id the food ID string
     * @return the food corresponding to the food ID string
     */
    public Food getFood(String id)
    {
        return foods_.get(searchFoodIndex(id));
    }

    /**
     * Returns an ArrayList containing all the items and promotional sets within this MenuList
     * @return the ArrayList with all the items and promotional sets
     */
    public ArrayList<Food> getAllFood()
    {
        return this.foods_;
    }

    /**
     * Insert a new food into this MenuList. A valid item ID for this MenuList must have already been set for the object
     * before insertion.
     * @param food_insert the food to be inserted
     */
    public void insertFood(Food food_insert)
    {
        int appendIndex;
        if(foods_.size() == 0)
        {
            foods_.add(food_insert);
        }
        else
        {
            appendIndex = searchFoodIndex(food_insert.getId());
            if(foods_.get(appendIndex).getId().compareTo(food_insert.getId()) < 0)
            {
                foods_.add(food_insert);
            }
            else
            {
                foods_.add(appendIndex, food_insert);
            }
        }
    }
    /**
     * Removes a food within this MenuList. This will remove the data for the food entirely and could not be used as
     * reference later on when calculating previous sales data if the food has been ordered before.
     * @param id the item ID of the item to be removed
     */
    public void removeFood(String id)
    {
        foods_.remove(searchFoodIndex(id));
    }

    /**
     * Hides the food within this MenuList from displaying in the Menu display and make it to not able to be ordered.
     * @param id the item ID of the item to be hidden
     */
    public void hideFood(String id)
    {
        Food updatedFood = foods_.get(searchFoodIndex(id));
        updatedFood.setAvailability(false);
        removeFood(id);
        insertFood(updatedFood);
    }

    private void printFood(int index, Boolean showUnavailable)
    {
        if(foods_.get(index).getClassName() == "Item")
        {
            System.out.print(foods_.get(index).getId() + " ");

            System.out.println(foods_.get(index).getName());

        }
        else
        {
            System.out.print(foods_.get(index).getId() + " ");
            if(!foods_.get(index).getAvailability()) System.out.println("(unavailable/old) ");
            System.out.println(foods_.get(index).getName());
            for(int i = 0; i < foods_.get(index).getAllItemIds().size(); i++)
            {
                System.out.println(" - " + getFood(foods_.get(index).getAllItemIds().get(i)).getName());
            }
        }
    }

    /**
     * Prints all the food items within a given category
     * @param type the category type
     * @param showUnavailable an option for whether to print unavailable food items
     */
    public void printFoods(String type, Boolean showUnavailable)
    {
        String startCode = map.getKey(type);
        if(startCode != "")
        {
            if(getCategorySize(type) == 0) return;
            System.out.println("==== " + type + " ====");
            for(int i = searchFoodIndex(startCode + "000"); i < foods_.size(); i++)
            {
                if(foods_.get(i).getId().substring(0, 1).compareTo(startCode) == 0)
                    printFood(i, showUnavailable);
            }
        }
    }
    /**
     * Prints all the food items within this MenuList
     * @param showPromoSet an option for whether to print promotional sets
     * @param showUnavailable an option for whether to print unavailable food items
     */
    public void printFoods(Boolean showPromoSet, Boolean showUnavailable)
    {
        ArrayList<String> categories = map.getCategoryList();
        for(int i = 0; i<categories.size(); i++)
        {
            if(!showPromoSet && categories.get(i).matches("Promotion Set"))
            {
                break;
            }
            else
            {
                printFoods(categories.get(i), showUnavailable);
                System.out.println();
            }
        }
    }

    /**
     * Returns whether the given ID corresponds to an item within this MenuList or not. Will also return as false if the
     * given ID cannot be found within this MenuList.
     * @param id the given ID
     * @return true if there exists an item with this given ID, false otherwise
     */
    public Boolean isItem(String id)
    {
        if(!getFood(id).getId().equals(id)) return false;
        return getFood(id).getClassName() == "Item";
    }

    /**
     * Returns whether the given ID corresponds to a promotional set within this MenuList or not. Will also return as false if the
     * given ID cannot be found within this MenuList.
     * @param id the given ID
     * @return true if there exists a promotional set with this given ID, false otherwise
     */
    public Boolean isPromoSet(String id)
    {
        if(!getFood(id).getId().equals(id)) return false;
        return getFood(id).getClassName() == "PromoSet";
    }

    /**
     * Returns whether the given ID corresponds to an item or a promotional set within this MenuList or not.
     * @param id the given ID
     * @return true if there exists an item or a promotional set with this given ID, false otherwise
     */
    public Boolean isFood(String id)
    {
        return getFood(id).getId().equals(id);
    }
}