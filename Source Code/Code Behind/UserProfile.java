package sample;

import java.util.List;

public class UserProfile {

    private String name;
    private String password;
    private String contact;
    private String location;
    private List<String> interests ;
    //private projectHistory

    public String getName()
    {
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getInterests() {
        return interests;
    }
    public void setName(String name){
        this.name=name;
    }

    public UserProfile(String name, String password, String contact, String address, List<String> interests)
    {
        this.name = name;
        this.password=password;
        this.contact = contact;
        this.location = address;
        this.interests = interests;
    }


}
