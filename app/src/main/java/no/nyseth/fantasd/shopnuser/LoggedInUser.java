package no.nyseth.fantasd.shopnuser;

import com.android.volley.AuthFailureError;
import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class LoggedInUser {

    private static LoggedInUser instance = null;
    private boolean isLoggedIn = false;
    private User user;
    private String jwt;

    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return  instance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void updateUser() {}

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + jwt);
        return headers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
