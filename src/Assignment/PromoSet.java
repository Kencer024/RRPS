// import java.io.*;
package Assignment;

import java.util.*;

public class PromoSet{

    private String name_;
    private String setDesc_;
    private List<String> itemList_ = new ArrayList<String>();
    private float saleCost_;
    private String promoSetId_;
    public int promo_availability_;
    
    public PromoSet(String promoSetId_input)
    {   
        this.promoSetId_ = promoSetId_input;
    }

    public PromoSet()
    {
        this.name_ = "";
        this.setDesc_ = "";
        this.promoSetId_ = "";
        this.promo_availability_ = 1;
    }

    public void setName(String nameInput) { this.name_ = nameInput; }

    public String getName() { return this.name_; }

    public void setDesc(String descInput) { this.setDesc_ = descInput; }

    public String getDesc() { return this.setDesc_; }

    public void setId(String idInput)
    {
        this.promoSetId_ = idInput;
    }

    public String getId()
    {
        return this.promoSetId_;
    }

    public void setSaleCost(float saleInput) {this.saleCost_ = saleInput; }

    public float getSaleCost() { return this.saleCost_; }

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
