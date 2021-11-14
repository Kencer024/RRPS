package Assignment;

import java.util.*;

/**Represents a class that is used to have Promotional combos in the Menu.
 * It inherits from the abstract class Food as Food serves as a basic template for the
 * components to be listed on the Menu
 */
public class PromoSet extends Food{

    private List<String> itemList_ = new ArrayList<String>();

    /** Overriding the default constructor 
     *  to initialise the Item easily. It uses the setId function of the abstract class
     *  Food to set the PromoSet ID
     * @param promoSetId_input of type String takes in the name of the Promoset
     */
    public PromoSet(String promoSetId_input)
    {   
        this();
        super.setId(promoSetId_input);
    }

	/** Overriding the default constructor to initialise all the private variables 
     * and the availability to 1
     */
    public PromoSet()
    {
        super.setName("-");
        super.setFoodDesc("-");
        super.setId("");
        super.availability_ = true;
    }
    
	/** Gets all the Ids of the items that are a part of the promotional set
	 * @return A ArrayList of type String
    */
    @Override
    public List<String> getAllItemIds()
    {
        return this.itemList_;
    }
    
    /**(Implementation of abstract function) Gets the Class name 
    * @return A String representing the class name
    */
    @Override
    public String getClassName(){
        return "PromoSet";
    }    

	/** Sets all the Ids of the items that are a part of the promotional set
	 * @param addallitems_input is of the type ArrayList String that has all the ids of the items
    */
    public void setallItemIds(List<String> addallitems_input, MenuList menu)

    {
        this.itemList_ = addallitems_input;
        float baseCost = 0f;
        for(int i = 0; i < itemList_.size(); i++)
        {
            baseCost += menu.getFood(itemList_.get(0)).getBaseCost();
        }
        super.setBaseCost(baseCost);
    }

	/** Removes the item from the promotional Set
	 * @param remove_id is of type String that represents the Item Id
    */
    public void remove_item(String remove_id)
    {
        this.itemList_.remove(remove_id);
        System.out.println(itemList_);
    }

	/** Adds the item to the promotional Set
	 * @param add_id is of type String that represents the Item Id
     */
    public void add_item(String add_id)
    {
        this.itemList_.add(add_id);
        System.out.println(itemList_);
    }

	/** Returns the total items in the promotional Set */
    public int size()
    {
        return itemList_.size();
    }

    @Override
    public Boolean attributesAreValid(MenuList menu){
        if(super.getSaleCost() < 0 || super.getBaseCost() < 0)return false;
        for(int i = 0; i < itemList_.size(); i++){
            if(!menu.isItem(itemList_.get(i)))return false;
        }
        return true;
    }

    
}
