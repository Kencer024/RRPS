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

//    private int searchSetIndex(String id)
//    {
//        int left = 0, right = sets_.size() - 1, center;
//        while(left < right)
//        {
//            center = (left + right) / 2;
//            if(sets_.get(center).getId().compareTo(id) == 0)
//            {
//                return center;
//            }
//            else if(sets_.get(center).getId().compareTo(id) < 0)
//            {
//                left = center + 1;
//            }
//            else
//            {
//                right = center;
//            }
//        }
//        return left;
//    }

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

//    /**
//     * Returns a new set ID string that is available within this MenuList. This ID can be used as an index for a new promotional set.
//     * @return the new set ID string
//     */
//    public String getNewSetId()
//    {
//        if(sets_.size() == 0)
//        {
//            return map.getKey("Promotion Set") + "000";
//        }
//        else
//        {
//            return StringUtil.incrementString(sets_.get(sets_.size() - 1).getId(), 1);
//        }
//    }

    /**
     * Returns the item object corresponding to the item ID string
     * @param id the item ID string
     * @return the item corresponding to the set ID string
     */
    // public Item getItem(String id)
    // {
    //     return items_.get(searchItemIndex(id));
    // }

    public Food getFood(String id)
    {
        return foods_.get(searchFoodIndex(id));
    }

    /**
     * Returns the promotional set object corresponding to the set ID string
     * @param id the set ID string
     * @return the promotional set corresponding to the set ID string
     */
    // public PromoSet getSet(String id)
    // {
    //     return sets_.get(searchSetIndex(id));
    // }

    /**
     * Returns an ArrayList of items with the corresponding item IDs starting with a certain pattern
     * @param id_start the pattern for filtering items within this MenuList
     * @return the ArrayList with the item IDs starting with the given pattern
     */
    public ArrayList<Food> getFood(char id_start)
    {
        ArrayList<Food> food_type = new ArrayList<Food>();

        for(int i = 0; i< this.foods_.size();i++)
        {
            Food local_item_type = this.foods_.get(i);
            String food_id = local_item_type.getId();
            if(food_id.charAt(0) == id_start)
            {
                food_type.add(local_item_type);
            }
        }
        return food_type;

    }

    public ArrayList<Food> getAllFood()
    {
        return this.foods_;
    }

    // public ArrayList<PromoSet> getAllPromoSets()
    // {
    //     return this.sets_;
    // }

    /**
     * Insert a new item into this MenuList. A valid item ID for this MenuList must have already been set for the item
     * before insertion.
     * @param item_insert the item to be inserted
     */
    public void insertFood(Food item_insert)
    {
        int appendIndex;
        if(foods_.size() == 0)
        {
            foods_.add(item_insert);
        }
        else
        {
            appendIndex = searchFoodIndex(item_insert.getId());
            if(foods_.get(appendIndex).getId().compareTo(item_insert.getId()) < 0)
            {
                foods_.add(item_insert);
            }
            else
            {
                foods_.add(appendIndex, item_insert);
            }
        }
    }
    /**
     * Removes an item within this MenuList. This will remove the data for the item entirely and could not be used as
     * reference later on when calculating previous sales data if the item has been ordered before.
     * @param id the item ID of the item to be removed
     */
    public void removeFood(String id)
    {
        for(int i = 0; i< this.foods_.size();i++)
        {
            Food local_item_type = this.foods_.get(i);
            String local_item_id = local_item_type.getId();
            if(local_item_id.equals(id))
            {   
                this.foods_.remove(local_item_type);
            }
        }
    }
//    /**
//     * Removes a promotional set within this MenuList. This will remove the data for the set entirely and could not be
//     * referenced later on when calculating previous sales data if the item has been ordered before.
//     * @param id the set ID of the promotional set to be removed
//     */
    // public void removeSet(String id)
    // {
    //     for(int i = 0; i< this.sets_.size();i++)
    //     {
    //         PromoSet local_set = this.sets_.get(i);
    //         String local_set_id = local_set.getId();
    //         if(local_set_id.equals(id))
    //         {   
    //             this.sets_.remove(local_set);
    //         }
    //     }
    // }

    public void updateFood(Food item_update)
    {
        for(int i = 0; i< this.foods_.size();i++)
        {
            Food local_item_type = this.foods_.get(i);
            String local_item_id = local_item_type.getId();
            if(local_item_id.equals(item_update.getId()))
            {   local_item_type.availability_ = 0;
                this.foods_.set(i,local_item_type);
            }
        }
        this.foods_.add(item_update);
    } 

    // public void appendSet(PromoSet set_append)
    // {
    //     int appendIndex;
    //     if(sets_.size() == 0)
    //     {
    //         sets_.add(set_append);
    //     }
    //     else
    //     {
    //         appendIndex = searchItemIndex(set_append.getId());
    //         if(sets_.get(appendIndex).getId().compareTo(set_append.getId()) < 0)
    //         {
    //             sets_.add(set_append);
    //         }
    //         else
    //         {
    //             sets_.add(appendIndex, set_append);
    //         }
    //     }
    // } 


    // public void updateSet(PromoSet set_update)
    // {
    //     for(int i = 0; i< this.sets_.size();i++)
    //     {
    //         PromoSet local_set_type = this.sets_.get(i);
    //         String local_set_id = local_set_type.getId();
    //         if(local_set_id.equals(set_update.getId()))
    //         {   local_set_type.availability_ = 0;
    //             this.sets_.set(i,local_set_type);
    //         }
    //     }
    //     this.sets_.add(set_update);
    // } 

    public void invalidateFood(String id)
    {
        for(int i = 0; i< this.foods_.size();i++)
        {
            Food local_item = this.foods_.get(i);
            if(local_item.getId().equals(id))
            {
                local_item.availability_ = 0;
                this.foods_.set(i,local_item);
                System.out.println(id + " " + local_item.getAvailability());
            }
        }
    }

    public void validateFood(String id)
    {
        for(int i = 0; i< this.foods_.size();i++)
        {
            Food local_item = this.foods_.get(i);
            
            if(local_item.getId().equals(id))
            {
                local_item.availability_ = 1;
                this.foods_.set(i,local_item);
            }
        }
    }

    // public void invalidateSet(String setid)
    // {
    //     for(int i = 0; i< this.sets_.size();i++)
    //     {
    //         PromoSet local_set = this.sets_.get(i);
            
    //         if(local_set.getId().equals(setid))
    //         {
    //             local_set.availability_ = 0;
    //             this.sets_.set(i,local_set);
    //         }
    //     }
    // }

    // public void validateSet(String setid)
    // {
    //     for(int i = 0; i< this.sets_.size();i++)
    //     {
    //         PromoSet local_set = this.sets_.get(i);
            
    //         if(local_set.getId().equals(setid))
    //         {
    //             local_set.availability_ = 1;
    //             this.sets_.set(i,local_set);
    //         }
    //     }
    // }
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
            if(foods_.get(index).getAvailability() == 0) System.out.println("(unavailable/old) ");
            System.out.println(foods_.get(index).getName());
            for(int i = 0; i < foods_.get(index).getallItemIds().size(); i++)
            {
                System.out.println(" - " + getFood(foods_.get(index).getallItemIds().get(i)).getName());
            }
        }
    }
    public void printFoods()
    {
        for(int i = 0; i<foods_.size(); i++)
        {
            printFood(i, true);
        }
    }

    public void printMenu(String type)
    {
        String startCode = map.getKey(type);
        if(startCode != "")
        {
            for(int i = searchFoodIndex(startCode + "000"); i < foods_.size(); i++)
            {
                if(foods_.get(i).getId().substring(0, 1).compareTo(startCode) == 0)
                printFood(i, false);
            }
        }
    }

    public Boolean isItem(String id)
    {
        return getFood(id).getClassName() == "Item";
    }

    public Boolean isPromoSet(String id)
    {
        return getFood(id).getClassName() == "PromoSet";
    }
    // public void printSets()
    // {
    //     for(int i = 0; i<sets_.size(); i++)
    //     {
    //         if(sets_.get(i).getAvailability() == 1)
    //         {
    //          System.out.print(sets_.get(i).getId() + " ");
    //          System.out.println(sets_.get(i).getName());
    //          for(int j = 0; j < sets_.get(i).getallItemIds().size(); j++)
    //          {
    //              System.out.println(" - " + getItem(sets_.get(i).getallItemIds().get(j)).getName());
    //          }
    //         }
    //     }
    // }

//    public ArrayList<Food> sort_food_by_type()
//    {
//        ArrayList<Food> sortedMenuItems = (ArrayList<Food>)foods_.clone();
//        sortedMenuItems.sort(Comparator.comparing(Food::getType));
//
//        return sortedMenuItems;
//    }

    // Not necessary
    // public ArrayList<Item> sort_food_by_id()
    // {
    //     ArrayList<Item> sortedMenuItems = (ArrayList<Item>)items_.clone();
    //     sortedMenuItems.sort(Comparator.comparing(Item::getId));

    //     return sortedMenuItems;
    // }

    // public ArrayList<PromoSet> sort_sets_by_id()
    // {
    //     ArrayList<PromoSet> sortedMenuPromoSets = (ArrayList<PromoSet>)sets_.clone();
    //     sortedMenuPromoSets.sort(Comparator.comparing(PromoSet::getId));

    //     return sortedMenuPromoSets;
    // }
}