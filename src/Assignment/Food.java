package Assignment;

/**Represents an abstract class that serves as a Parent class for
* any kind of food that needs to be listed on the menu.
*/

public abstract class Food {
    private String id_;
    private String name_;
    private String foodDesc_;
    private String type_;
    private float saleCost_;
    private float baseCost_;
    public int availability_;

    /** An abstract function that needs to be implemented
    * by all other classes that would be inheriting from Food 
    * to facilitate the printing of the bill.   
    */
    abstract public String getClassName();

	/** Gets the Id of Food
    * @return A String representing the Id
    */
    public String getId()
    {
        return this.id_;
    }

	/** Gets the Name of Food
    * @return A String representing the name of the food
    */
    public String getName()
    {
        return this.name_;

    }

	/** Gets the Description of the Food
    * @return A String representing the description of the Food
    */
    public String getFoodDesc()
    {
        return this.foodDesc_;
    }

	/** Gets the Type of Food
    * @return A String representing the type of the food
    * This attribute is just for more clearer understanding 
    * of the classification of Food 
    */
    public String getType()
    {
        return this.type_;
    }

	/** Gets the selling price of Food
    * @return A Float representing the selling price of the Food
    */
    public float getSaleCost()
    {
        return this.saleCost_;
    }

	/** Gets the cost price of Food
    * @return A Float representing the cost price of the Food
    */
    public float getBaseCost()
    {
        return this.baseCost_;
    }

	/** Gets the availability of the Food
    * @return An Integer representing the availability of the Food
    * Currently, 1 - available and 0 - unavailable 
    * Integer was chosen instead of boolean so that others can add in more integers 
    * that can represent the special food like Chief's special(limited availability) etc
    */
    public int getAvailability()
    {
        return this.availability_;
    }

	/** Sets the Id of Food 
     * @param id_input is of type String that represents the Id of the Food
     */
    public void setId(String id_input)
    {
        this.id_ = id_input;
    }

    /** Sets the Name of Food 
     * @param name_input is of type String that represents the name of the Food
     */
    public void setName(String name_input)
    {
        this.name_ = name_input;
    }

    /** Sets the description of Food 
     * @param item_input is of type String that represents the description of the Food
     */
    public void setFoodDesc(String item_input)
    {
        this.foodDesc_ = item_input;
    }

    /** Sets the Type of Food 
     * @param type_input is of type String that represents the type of the Food
     */
    public void setType(String type_input)
    {
        this.type_ = type_input;
    }

    /** Sets the selling price of Food 
     * @param saleCost_input is of type float that represents the selling price of the Food
     */
    public void setSaleCost(float saleCost_input)
    {
        if(saleCost_input > 0)
        {
            this.saleCost_ = saleCost_input;
        }
        else{
            System.out.println("Enter a valid saleprice( > 0). You entered: "+ saleCost_input);
        }
    }

    /** Sets the cost price of Food 
     * @param baseCost_input is of type float that represents the cost price of the Food
     */
    public void setBaseCost(float baseCost_input)
    {
        if(baseCost_input > 0)
        {
            this.baseCost_ = baseCost_input;
        }
        else{
            System.out.println("Enter a valid baseprice( > 0). You entered: " + baseCost_input);
        }

    }

}
