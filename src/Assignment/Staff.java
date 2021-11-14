package Assignment;

import java.util.Base64;

/**
 * Object for storing data of a restaurant's staff which includes password data that has been encrypted for usages in
 * authentication functions
 */
public class Staff {
    private String id;
    private String name;
    private char gender;
    private String jobTitle;
    private String password;

    /**
     * Constructs a Staff object with initialized attributes. The password inputted will be encrypted.
     * @param id this Staff object's ID--can be generated from using getNewStaffId() method from a StaffDatabase object
     * @param name the staff's name
     * @param gender the staff's gender
     * @param jobTitle the job title of the staff
     * @param password unencrypted password for logging into this account
     */
    public Staff(String id, String name, char gender, String jobTitle, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.password = Base64.getEncoder().encodeToString(password.getBytes());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Checks whether a given password String matches with this Staff's password
     * @param password the unencrypted password to be checked
     * @return true if the password matches, false otherwise
     */
    public Boolean passwordMatches(String password) {
        return  this.password.equals(Base64.getEncoder().encodeToString(password.getBytes()));
    }
}
