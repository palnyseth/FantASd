package no.nyseth.fantasd.shopnuser;

import com.google.gson.annotations.Expose;

public class LoggedInUser {

    @Expose
    private String uid;
    private String userToken;

    public LoggedInUser(String uid) {
        this.uid = uid;
    }

    public LoggedInUser() {
    }

    public void setUserToken(String token) {
        this.userToken = token;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


}
