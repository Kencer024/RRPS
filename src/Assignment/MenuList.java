import java.lang.reflect.Array;
import java.util.*;

public class MenuList
{
    private ArrayList<Item> items_ = new ArrayList<Item>();
    private ArrayList<PromoSet> sets_ = new ArrayList<PromoSet>();
    private String itemEndId = "0000";
    private String setEndId = "0000";

    private int searchItemTypeStartIndex(String type)
    {
        int left = 0, right = items_.size() - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            if(items_.get(center).getType().compareTo(type) < 0)
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

    private int searchItemTypeEndIndex(String type)
    {
        int left = 0, right = items_.size() - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            if(items_.get(center).getType().compareTo(type) <= 0)
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

    private int searchItemIndex(String type, int id)
    {
        int left = searchItemTypeStartIndex(type), right = searchItemTypeEndIndex(type) - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            if(items_.get(center).getType().compareTo(type) <= 0)
            {
                left = center + 1;
            }
            else
            {
                right = center;
            }
        }
    }

    private int searchSetTypeStartIndex(String type)
    {
        int left = 0, right = sets_.size() - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            if(sets_.get(center).getType().compareTo(type) < 0)
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

    private int searchSetTypeEndIndex(String type)
    {
        int left = 0, right = sets_.size() - 1, center;
        while(left < right)
        {
            center = (left + right) / 2;
            if(sets_.get(center).getType().compareTo(type) <= 0)
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

    public Item getItem(String id)
    {
        for(int i = 0; i< this.items_.size(); i++)
        {
            Item local_item = this.items_.get(i);
            if(local_item.getId() == id)
            {
                return local_item;
            }
        }
        return null;
    }

    public PromoSet getSet(String promoSetId_input)
    {
        for(int i = 0; i< this.sets_.size(); i++)
        {
            PromoSet local_set = this.sets_.get(i);
            if(local_set.getPromoSetId() == promoSetId_input)
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
        if(this.items_.size() == 0)
        {
            this.items_.add(item_append);
        }
        else
        {

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
            String local_set_id = local_set.getPromoSetId();
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
        this.sets_.add(set_append);
    } 


    public void updateSet(PromoSet set_update)
    {
        for(int i = 0; i< this.sets_.size();i++)
        {
            PromoSet local_set_type = this.sets_.get(i);
            String local_set_id = local_set_type.getPromoSetId();
            if(local_set_id == set_update.getPromoSetId())
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
            
            if(local_set.getPromoSetId() == setid)
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
            
            if(local_set.getPromoSetId() == setid)
            {
                local_set.promo_availability_ = 1;
                this.sets_.add(i,local_set);
            }
        }
    }

    public void printItems()
    {
        for(int i = 0; i<this.items_.size(); i++)
        {
            System.out.println(this.items_.get(i).getName());
        }
    }

    public ArrayList<Item> sort_items_by_type()
    {
        ArrayList<Item> sortedMenuItems = (ArrayList<Item>)items_.clone();
        sortedMenuItems.sort(Comparator.comparing(Item::getType));

        return sortedMenuItems;
    }

    public ArrayList<Item> sort_items_by_id()
    {
        ArrayList<Item> sortedMenuItems = (ArrayList<Item>)items_.clone();
        sortedMenuItems.sort(Comparator.comparing(Item::getId));

        return sortedMenuItems;
    }

    public ArrayList<PromoSet> sort_sets_by_id()
    {
        ArrayList<PromoSet> sortedMenuPromoSets = (ArrayList<PromoSet>)sets_.clone();
        sortedMenuPromoSets.sort(Comparator.comparing(PromoSet::getPromoSetId));

        return sortedMenuPromoSets;
    }



}