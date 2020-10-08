package no.nyseth.fantasd.shopnuser;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;

import no.nyseth.fantasd.network.ApiLinks;
import no.nyseth.fantasd.network.RequestGen;

public class UserStatus {

    //Status of whether or not there's an instance of a user
    private static UserStatus userInstance = null;

    //Checks for instance, if none -> create.
    public static UserStatus getUserInstance() {
        if (userInstance == null) {
            userInstance = new UserStatus();
        }
        return userInstance;
    }

    //Checks if user is currently logged inn
    private boolean userActive = false;

    public boolean userActive() {
        return userActive();
    }

    public void setUserActive(boolean userActive1) {
        userActive = userActive1;
    }

    //Get JWT Token from server
    private String jwt;

    public HashMap<String, String> getJWTToken() {
        HashMap<String, String> jwtToken = new HashMap<>();
        jwtToken.put("Authorization", "Bearer " + jwt);
        return jwtToken;
    }

    private User user;
    public void getCurrentUser() {
        RequestGen<User> requestGen = new RequestGen<>(
                ApiLinks.URL_USER_CURRENT, Request.Method.GET,
                User.class, getJWTToken(),
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        user = response;

                    }
                },
                ApiLinks.newEL
        );
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
