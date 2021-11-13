package Assignment;

//javadocs

/**Represents each table slot
 *
 */
public class Table {
    private int tableId;
    private boolean reserved;
    private int pax;
    private String name;

    /** Initialise all table reserved status to false and id as id
     *
     * @param id The identity number of the table, also doubles as index
     */
    public Table(int id){
        this.tableId = id;
        this.reserved = false;
    }

    /** Gets the ID of the table
     * @return An integer representing the table identity
     */
    public int getTableId(){return this.tableId;}

    /** Gets the reservation status of the table
     * @return A boolean representing the reservation status of
     *          the table. True means it is reserved, false means it isn't
     */
    public boolean getReserved(){return this.reserved;}

    /** Gets the number people that can be seated at the table
     * @return An integer representing the pax allowed at the table
     */
    public int getPax(){return this.pax;}

    /** Gets the name used for reservation
     * @return A string representing the name used for reservation
     */
    public String getName(){return this.name;}


    /** Sets the table identity
     * @param tableId An integer representing the identity of the table
     */
    public void setTableId(int tableId){this.tableId = tableId;}

    /** Sets the name of the person reserving the table
     * @param name A string representing name reserving the table
     */
    public void setName(String name){this.name = name;}

    /** Sets reservation status of the table
     * @param r A boolean where true means it is reserved, false means it is not
     */
    public void setReserved(boolean r){this.reserved = r;}

    /** Sets the maximum number of people per table
     * @param pax A boolean where true means it is reserved, false means it is not
     */
    public void setPax(int pax){this.pax = pax;}


}
