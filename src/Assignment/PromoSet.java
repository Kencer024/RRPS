// import java.io.*;
package Assignment;

import java.util.*;

public class PromoSet{

    private List<String> itemList_ = new ArrayList<String>();
    private String promoSetId_;
    public int promo_availability_;
    
    public PromoSet(String promoSetId_input)
    {   
        this.promoSetId_ = promoSetId_input;
    }

    public PromoSet()
    {
        this.promoSetId_ = "";
        this.promo_availability_ = 1;
    }

    public void setPromoSetId(String id_input)
    {
        this.promoSetId_ = id_input;
    }

    public String getPromoSetId()
    {
        return this.promoSetId_;
    }

    public List<String> getallItemIds()
    {
        return this.itemList_;
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




    
}
