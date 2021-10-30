// import java.io.*;
import java.util.*;

public class PromoSet extends Item{

    private List<String> itemList_ = new ArrayList<String>();

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
