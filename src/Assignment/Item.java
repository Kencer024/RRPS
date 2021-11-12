package Assignment;

public class Item extends Food{
    
    /** Overriding the default constructor 
     *  to initialise the Item easily 
     * @param name_input of type String takes in the name of the Item
     * @param saleCost_input of type float takes in the selling price of the Item
     * @param baseCost of type float takes in the cost price of the Item
     */
    public Item(String name_input, float saleCost_input, float baseCost_input) {
        this();
        super.setName(name_input); 
        if(saleCost_input >0)
        {
            super.setSaleCost(saleCost_input);
            System.out.println("Entered");
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

	/** Overriding the default constructor to initialise all the private variables */
    public Item(){
        super.setId(""); 
        super.setName(""); 
        super.setFoodDesc(""); 
        super.setType("");
        // super.setSaleCost(0);
        // super.setBaseCost(0);
        super.availability_ = 1;
    }

	/**(Implementation of abstract function) Gets the Class name 
    * @return A String representing the class name
    */
    public String getClassName(){

        return "Item";
    }

    
}
