package Assignment;

/**Represents a Interface that serves as a template 
 * for creating multiple Databases. Currently we have OrderDatabase to 
 * store current orders and SalesDatabase which stores Orders and analyses them.
 * However, instead of a single SalesDatabase, we can have 2 seperate Databases
 * with one of them tailored to retrieve information about Orders and the other one
 * focusing on generating reports, calculating trends etc.
 * Furthermore, use of interface makes it easier to have standardised usage functions
 * for multiple databases.
 *
 */
public interface DatabaseInterface {

    public Order getOrderInfo(String orderId_input);
    public void printDatabase();
    public void appendOrder(Order order_add);
    public Order removeOrder(String order_id);
    public int getTotalNumelements();
    
}
