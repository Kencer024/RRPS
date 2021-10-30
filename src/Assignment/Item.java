
public class Item {

    private String id_;
    private boolean isValid_;
    private String name_;
    private String itemDesc_;
    private String type_;
    private float saleCost_;
    private float baseCost_;
    
    public Item(String name_input, float saleCost_input, float baseCost_input) {
        this();
        this.name_ = name_input;
        if(saleCost_input >0)
        {
            this.saleCost_ = saleCost_input;
        }
        else{
            System.out.println("Set a valid saleprice( > 0)");
        }
        if(baseCost_input > 0)
        {
            this.baseCost_ = baseCost_input;
        }      
        else{
            System.out.println("Set a valid baseprice( > 0)");
        }  
        this.checkValidity();
        
    }

    public Item(){
        this.name_ = "";
        this.isValid_ = false;
        this.name_ = "";
        this.itemDesc_ = "";
        this.type_ = "";
        this.saleCost_ = 0;
        this.baseCost_ = 0;
    }

    public String getId()
    {
        return this.id_;
    }

    public String getName()
    {
        return this.name_;
        
    }

    public String getItemDesc()
    {
        return this.itemDesc_;
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

    public boolean getValidity()
    {
        return this.isValid_;
    }

    public void setId(String id_input)
    {
        this.id_ = id_input;
    }

    public void setName(String name_input)
    {
        this.name_ = name_input;
        this.checkValidity();
    }

    public void setItemDesc(String item_input)
    {
        this.itemDesc_ = item_input;
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
            System.out.println("Enter a valid saleprice( > 0)");
        }
        this.checkValidity();
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

        this.checkValidity();
        
    }

    private void checkValidity()
    {
        if(this.getName()!="" && this.getBaseCost()>0 && this.getSaleCost()>0)
        {
            this.isValid_ = true;
        }
        else{
            this.isValid_ = false;
        }
    }


    
}
