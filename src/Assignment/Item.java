package Assignment;

public class Item extends Food{

    // private String id_;
    // private boolean isValid_;
    // private String name_;
    // private String itemDesc_;
    // private String type_;
    // private float saleCost_;
    // private float baseCost_;
    // public int availability_;
    
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

    public Item(){
        super.setId(""); 
        super.setName(""); 
        super.setItemDesc(""); 
        super.setType("");
        // super.setSaleCost(0);
        // super.setBaseCost(0);
        super.availability_ = 1;
    }

    
}
