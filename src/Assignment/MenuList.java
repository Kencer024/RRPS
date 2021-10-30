import java.lang.reflect.Array;
import java.util.*;

public class MenuList
{
    private ArrayList<Item> items_ = new ArrayList<Item>();
    private ArrayList<PromoSet> sets_ = new ArrayList<PromoSet>();

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

    public ArrayList<PromoSet> getAllPromoSets()
    {
        return this.sets_;
    }

    public void appendItem(Item item_append)
    {
        this.items_.add(item_append);
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



}