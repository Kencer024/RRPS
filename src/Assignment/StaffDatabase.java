package Assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffDatabase {
    ArrayList<Staff> staffs;
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

    public String getNewStaffId() {
        if(staffs.isEmpty()) {
            return "0001";
        }
        else {
            return StringUtil.incrementString(staffs.get(staffs.size() - 1).getId(), 1);
        }
    }

    public Boolean addStaff(Staff newStaff) {
        if(staffs.isEmpty()){
            staffs.add(newStaff);
            return true;
        }
        if(staffs.get(getStaffIndex(newStaff.getId())).getId().equals(newStaff.getId())) return false;
        staffs.add(newStaff);
        return true;
    }

    public Boolean removeStaff(String id) {
        if(!staffs.get(getStaffIndex(id)).getId().equals(id)) return false;
        staffs.remove(getStaffIndex(id));
        return true;
    }

    public Staff getStaff(String id) {
        if(!staffs.get(getStaffIndex(id)).getId().equals(id)) return null;
        return staffs.get(getStaffIndex(id));
    }

    public Boolean staffLogin(String id, String password) {
        if(!staffs.get(getStaffIndex(id)).getId().equals(id)) return false;
        return staffs.get(getStaffIndex(id)).passwordMatches(password);
    }

}
