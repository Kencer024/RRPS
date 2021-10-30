package Assignment;

public class Table {
    private int tableId;
    private boolean reserved;

    public Table(int id){
        this.tableId = id;
        this.reserved = false;
    }

    public int getTableId(){return this.tableId;}
    public boolean getreserved(){return this.reserved;}


    public void setTableId(int tableId){this.tableId = tableId;}

    public void setReserved(boolean r){this.reserved = r;}
}
