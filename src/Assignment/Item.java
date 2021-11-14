package Assignment;

/**Represents a class that is used to have Food items in the Menu.
 * It inherits from the abstract class Food as Food serves as a basic template for the
 * components to be listed on the Menu
 */
public class Item extends Food{
    
    /** Overriding the default constructor 
     *  to initialise the Item easily 
     * @param name_input of type String takes in the name of the Item
     * @param saleCost_input of type float takes in the selling price of the Item
     * @param baseCost_input of type float takes in the cost price of the Item
     */
    public Item(String name_input, float saleCost_input, float baseCost_input) {
        this();
        super.setName(name_input); 
        if(saleCost_input >0)
        {
            super.setSaleCost(saleCost_input);
        }
        else{
            System.out.println("Set a valid saleprice( > 0). You entered: " + saleCost_input);
        }
        if(baseCost_input > 0)
        {
            super.setBaseCost(baseCost_input);
        }      
        else{
            System.out.println("Set a valid baseprice( > 0). You entered: " + baseCost_input);
        }          
    }

	/** Overriding the default constructor to initialise all the private variables 
     * and the availability to 1
     */
    public Item(){
        super.setId(""); 
        super.setName("-");
        super.setFoodDesc("-");
        super.setType("-");
        super.availability_ = true;
    }

	/**(Implementation of abstract function) Gets the Class name 
    * @return A String representing the class name
    */
    public String getClassName(){

        return "Item";
    }

//    public Item clone(){
//        Item cloneItem = new Item();
//        cloneItem.setId(super.getId());
//        cloneItem.setName(super.getName());
//        cloneItem.setFoodDesc(super.getFoodDesc());
//        cloneItem.setType(super.getType());
//        cloneItem.setAvailability(super.getAvailability());
//        cloneItem.setSaleCost(super.getSaleCost());
//        cloneItem.setBaseCost(super.getBaseCost());
//        return cloneItem;
//    }
}
