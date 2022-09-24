package com.example.test.modals;

public class Usersdata {

    String profilepic , username , userId , lastmassage ;

    public Usersdata(String profilepic, String username, String userId, String lastmassage) {
        this.profilepic = profilepic;
        this.username = username;
        this.userId = userId;
        this.lastmassage = lastmassage;
    }

    public Usersdata(){

    }

    public Usersdata( String username) {
        this.profilepic = profilepic;
        this.username = username;
        this.userId = userId;
        this.lastmassage = lastmassage;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastmassage() {
        return lastmassage;
    }

    public void setLastmassage(String lastmassage) {
        this.lastmassage = lastmassage;
    }
}
