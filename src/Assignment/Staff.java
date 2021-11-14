package Assignment;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Staff {
    private String id;
    private String name;
    private char gender;
    private String jobTitle;
    private String password;

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

    public Boolean passwordMatches(String password) {
        return  this.password.equals(Base64.getEncoder().encodeToString(password.getBytes()));
    }
}
