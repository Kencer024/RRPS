// import java.io.*;
package Assignment;

import java.util.*;

public class PromoSet extends Food{

    // private String name_;
    // private String setDesc_;
    private List<String> itemList_ = new ArrayList<String>();
    // private float saleCost_;
    // private String promoSetId_;
    // public int promo_availability_;
    
    public PromoSet(String promoSetId_input)
    {   
        super.setId(promoSetId_input);
    }

    public PromoSet()
    {
        super.setName("");
        super.setItemDesc(""); 
        super.setId("");
        super.availability_ = 1;
    }

   
    public List<String> getallItemIds()
    {
        return this.itemList_;
    }
    
    @Override
    public String getClassName(){
        return "PromoSet";
    }    
    public void setallItemIds(List<String> addallitems_input)

    {
        this.itemList_ = addallitems_input;
    }

    public void remove_item(String remove_id)
    {
        this.itemList_.remove(remove_id);
        System.out.println(itemList_);
    }

    public void add_item(String add_id)
    {
        this.itemList_.add(add_id);
        System.out.println(itemList_);
    }

    public int size()
    {
        return itemList_.size();
    }

    
}
