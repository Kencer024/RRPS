package Assignment;

public class Table {
    private int tableId;
    private boolean reserved;
    private int pax;
    private String Name;

    public Table(int id){
        this.tableId = id;
        this.reserved = false;
    }

    public int getTableId(){return this.tableId;}
    public boolean getreserved(){return this.reserved;}
    public int getpax(){return this.pax;}
    public String getName(){return this.Name;}

    public void setTableId(int tableId){this.tableId = tableId;}
    public void setName(String Name){this.Name = Name;}
    public void setReserved(boolean r){this.reserved = r;}
    public void setPax(int Pax){this.pax = Pax;}
}
