package Assignment;

import java.util.List;

public abstract class Food {
    private String id_;
    private String name_;
    private String foodDesc_;
    private String type_;
    private float saleCost_;
    private float baseCost_;
    public int availability_;

    public String getClassName(){
        return "Item";
    }

    public String getId()
    {
        return this.id_;
    }

    public String getName()
    {
        return this.name_;

    }

    public String getFoodDesc()
    {
        return this.foodDesc_;
    }

    public String getType()
    {
        return this.type_;
    }

    public float getSaleCost()
    {
        return this.saleCost_;
    }

    public float getBaseCost()
    {
        return this.baseCost_;
    }

    public int getAvailability()
    {
        return this.availability_;
    }

    public void setId(String id_input)
    {
        this.id_ = id_input;
    }

    public void setName(String name_input)
    {
        this.name_ = name_input;
    }

    public void setItemDesc(String item_input)
    {
        this.foodDesc_ = item_input;
    }

    public void setType(String type_input)
    {
        this.type_ = type_input;
    }

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

    public void setBaseCost(float baseCost_input)
    {
        if(baseCost_input > 0)
        {
            this.baseCost_ = baseCost_input;
        }
        else{
            System.out.println("Enter a valid baseprice( > 0)");
        }

    }

    public List<String> getallItemIds()
    {
        return null;
    }
}
