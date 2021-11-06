package Assignment;

import java.lang.reflect.Array;
import java.util.*;

public class MenuList
{
    private ArrayList<Item> items_ = new ArrayList<Item>();
    private ArrayList<PromoSet> sets_ = new ArrayList<PromoSet>();
    private CategoryKeyMap map = new CategoryKeyMap();

    public MenuList()
    {
        map.addCategory("Dessert");
        map.addCategory("Beef");
        map.addCategory("Fish");
        map.addCategory("Salads & Wraps");
        map.addCategory("Chicken");
        map.addCategory("Drinks");
        map.addCategory("Sides");

        map.addCategory("Promotion Set");
        // map.printkey();
    }
    private int searchItemIndex(String id)
    {
        int left = 0, right = items_.size() - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            // System.out.print(id + " ");
            // System.out.println(center);
            if(items_.get(center).getId().compareTo(id) == 0)
            {
                return center;
            }
            else if(items_.get(center).getId().compareTo(id) < 0)
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

    private int searchSetIndex(String id)
    {
        int left = 0, right = sets_.size() - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            if(sets_.get(center).getId().compareTo(id) == 0)
            {
                return center;
            }
            else if(sets_.get(center).getId().compareTo(id) < 0)
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

    public String getNewItemId(String type)
    {
        if(items_.size() == 0)
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

            lastId = items_.get(searchItemIndex(nextType)).getId();
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
            else if(searchItemIndex(nextType) == 0)
            {
                return map.getKey(type) + "000";
            }
            // Middle of Array
            else
            {
                // System.out.println(nextType);
                lastId = items_.get(searchItemIndex(nextType) - 1).getId();
                // Category not found
                if(lastId.charAt(0) < map.getKey(type).charAt(0))
                    return map.getKey(type) + "000";
                // Category found
                else
                    return StringUtil.incrementString(lastId, 1);
            }
        }
    }

    public String getNewSetId(String type)
    {
        if(sets_.size() == 0)
        {
            return map.getKey("Promotion Set") + "000";
        }
        else
        {
            return StringUtil.incrementString(sets_.get(sets_.size() - 1).getId(), 1);
        }
    }

    public Item getItem(String id)
    {
        return items_.get(searchItemIndex(id));
    }

    public PromoSet getSet(String promoSetId_input)
    {
        for(int i = 0; i< this.sets_.size(); i++)
        {
            PromoSet local_set = this.sets_.get(i);
            if(local_set.getId() == promoSetId_input)
            {
                return local_set;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems(char id_start)
    {
        ArrayList<Item> items_type = new ArrayList<Item>();

        for(int i = 0; i< this.items_.size();i++)
        {
            Item local_item_type = this.items_.get(i);
            String local_item_id = local_item_type.getId();
            if(local_item_id.charAt(0) == id_start)
            {
                items_type.add(local_item_type);
            }
        }
        return items_type;    
    
    }

    public ArrayList<Item> getAllItems()
    {
        return this.items_;
    }

    public ArrayList<PromoSet> getAllPromoSets()
    {
        return this.sets_;
    }

    public void appendItem(Item item_append)
    {
        int appendIndex;
        if(items_.size() == 0)
        {
            items_.add(item_append);
        }
        else
        {
            appendIndex = searchItemIndex(item_append.getId());
            if(items_.get(appendIndex).getId().compareTo(item_append.getId()) < 0)
            {
                items_.add(item_append);
            }
            else
            {
                items_.add(appendIndex, item_append);
            }
        }
    } 

    public void removeItem(String id)
    {
        for(int i = 0; i< this.items_.size();i++)
        {
            Item local_item_type = this.items_.get(i);
            String local_item_id = local_item_type.getId();
            if(local_item_id == id)
            {   
                this.items_.remove(local_item_type);
            }
        }
    }

    public void removeSet(String id)
    {
        for(int i = 0; i< this.sets_.size();i++)
        {
            PromoSet local_set = this.sets_.get(i);
            String local_set_id = local_set.getId();
            if(local_set_id == id)
            {   
                this.sets_.remove(local_set);
            }
        }
    }

    public void updateItem(Item item_update)
    {
        for(int i = 0; i< this.items_.size();i++)
        {
            Item local_item_type = this.items_.get(i);
            String local_item_id = local_item_type.getId();
            if(local_item_id == item_update.getId())
            {   local_item_type.availability_ = 0;
                this.items_.add(i,local_item_type);
            }
        }
        this.items_.add(item_update);
    } 

    public void appendSet(PromoSet set_append)
    {
        int appendIndex;
        if(sets_.size() == 0)
        {
            sets_.add(set_append);
        }
        else
        {
            appendIndex = searchItemIndex(set_append.getId());
            if(sets_.get(appendIndex).getId().compareTo(set_append.getId()) < 0)
            {
                sets_.add(set_append);
            }
            else
            {
                sets_.add(appendIndex, set_append);
            }
        }
    } 


    public void updateSet(PromoSet set_update)
    {
        for(int i = 0; i< this.sets_.size();i++)
        {
            PromoSet local_set_type = this.sets_.get(i);
            String local_set_id = local_set_type.getId();
            if(local_set_id == set_update.getId())
            {   local_set_type.promo_availability_ = 0;
                this.sets_.add(i,local_set_type);
            }
        }
        this.sets_.add(set_update);
    } 

    public void invalidateItem(String id)
    {
        for(int i = 0; i< this.items_.size();i++)
        {
            Item local_item = this.items_.get(i);
            
            if(local_item.getId() == id)
            {
                local_item.availability_ = 0;
                this.items_.add(i,local_item);
            }
        }
    }

    public void validateItem(String id)
    {
        for(int i = 0; i< this.items_.size();i++)
        {
            Item local_item = this.items_.get(i);
            
            if(local_item.getId() == id)
            {
                local_item.availability_ = 1;
                this.items_.add(i,local_item);
            }
        }
    }

    public void invalidateSet(String setid)
    {
        for(int i = 0; i< this.sets_.size();i++)
        {
            PromoSet local_set = this.sets_.get(i);
            
            if(local_set.getId() == setid)
            {
                local_set.promo_availability_ = 0;
                this.sets_.add(i,local_set);
            }
        }
    }

    public void validateSet(String setid)
    {
        for(int i = 0; i< this.sets_.size();i++)
        {
            PromoSet local_set = this.sets_.get(i);
            
            if(local_set.getId() == setid)
            {
                local_set.promo_availability_ = 1;
                this.sets_.add(i,local_set);
            }
        }
    }

    public void printItems()
    {
        for(int i = 0; i<items_.size(); i++)
        {
            System.out.print(items_.get(i).getId() + " ");
            System.out.println(items_.get(i).getName());
        }
    }

    public void printSets()
    {
        for(int i = 0; i<sets_.size(); i++)
        {
            System.out.print(sets_.get(i).getId() + " ");
            System.out.println(sets_.get(i).getName());
            for(int j = 0; j < sets_.get(i).getallItemIds().size(); j++)
            {
                System.out.print(sets_.get(i).getallItemIds().get(j) + " ");
                System.out.println(sets_.get(i).getName());
            }
        }
    }

    public ArrayList<Item> sort_items_by_type()
    {
        ArrayList<Item> sortedMenuItems = (ArrayList<Item>)items_.clone();
        sortedMenuItems.sort(Comparator.comparing(Item::getType));

        return sortedMenuItems;
    }

    // Not necessary
    public ArrayList<Item> sort_items_by_id()
    {
        ArrayList<Item> sortedMenuItems = (ArrayList<Item>)items_.clone();
        sortedMenuItems.sort(Comparator.comparing(Item::getId));

        return sortedMenuItems;
    }

    public ArrayList<PromoSet> sort_sets_by_id()
    {
        ArrayList<PromoSet> sortedMenuPromoSets = (ArrayList<PromoSet>)sets_.clone();
        sortedMenuPromoSets.sort(Comparator.comparing(PromoSet::getId));

        return sortedMenuPromoSets;
    }
}