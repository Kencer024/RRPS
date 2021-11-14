package Assignment;

import java.util.ArrayList;

/**
 * Object for storing a list of staff info
 */

public class StaffDatabase {
    ArrayList<Staff> staffs;

    /**
     * Constructs a new empty StaffDatabase
     */
    public StaffDatabase(){
        staffs = new ArrayList<Staff>();
    }

    private int getStaffIndex(String staffId)
    {
        int left = 0, right = staffs.size() - 1, middle;

        //Binary insertion to the sorted list
        while(left < right)
        {
            middle = (left + right)/2;
            if(staffId.compareTo(staffs.get(middle).getId()) == 0)
            {
                return middle;
            }
            else if(staffId.compareTo(staffs.get(middle).getId()) > 0)
            {
                left = middle + 1;
            }
            else
            {
                right = middle;
            }
        }
        return left;
    }

    /**
     * Generates a new staff ID within this StaffDatabase. This ID can be used to assigned to new Staff members.
     * @return the new staff ID
     */
    public String getNewStaffId() {
        if(staffs.isEmpty()) {
            return "0001";
        }
        else {
            return StringUtil.incrementString(staffs.get(staffs.size() - 1).getId(), 1);
        }
    }

    /**
     * Adds a new Staff object to this StaffDatabase. The staff ID of the Staff object must have already been set and
     * valid for this StaffDatabase before this method can be called.
     * @param newStaff the new Staff object to be added
     * @return true if the action is successful, false otherwise (due to either duplicated or invalid staff ID)
     */
    public Boolean addStaff(Staff newStaff) {
        if(staffs.isEmpty()){
            staffs.add(newStaff);
            return true;
        }
        if(staffs.get(getStaffIndex(newStaff.getId())).getId().equals(newStaff.getId())) return false;
        staffs.add(newStaff);
        return true;
    }

    /**
     * Removes a Staff object from this StaffDatabase that is corresponded to the given staff ID.
     * @param id the staff ID corresponding to the Staff object to be removed
     * @return true if a matching staff object within this StaffDatabase has been removed successfully, false otherwise
     */
    public Boolean removeStaff(String id) {
        if(!staffs.get(getStaffIndex(id)).getId().equals(id)) return false;
        staffs.remove(getStaffIndex(id));
        return true;
    }

    /**
     * Returns a Staff object from this StaffDatabase that is corresponded to the given staff ID.
     * @param id the staff ID corresponding to the Staff
     * @return the Staff object that is corresponded to the given staff ID
     */
    public Staff getStaff(String id) {
        if(!staffs.get(getStaffIndex(id)).getId().equals(id)) return null;
        return staffs.get(getStaffIndex(id));
    }

    /**
     * Attempts to login to a staff account with the given staff ID and Password
     * @param id the staff id to login to
     * @param password the password corresponding to the given staff id
     * @return true if the ID and password matches with the data within this StaffDatabase, false otherwise
     */
    public Boolean staffLogin(String id, String password) {
        if(!staffs.get(getStaffIndex(id)).getId().equals(id)) return false;
        return staffs.get(getStaffIndex(id)).passwordMatches(password);
    }

}
