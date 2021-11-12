package Assignment;

public interface DatabaseInterface {

    public Order getOrderInfo(String orderId_input);
    public void printDatabase();
    public void appendOrder(Order order_add);
    public void removeOrder(String order_id);
    public int getTotalNumelements();
    
}
