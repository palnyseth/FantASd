package no.nyseth.fantasd.network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefs {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String SHARED_PREF = "shared_preferences";

    public SharePrefs(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public String getToken() {
        return sharedPreferences.getString("token", "");
    }

    public void setToken(String token) {
        editor.putString("token", token).apply();
    }

    public String getUser() {
        return sharedPreferences.getString("user","");
    }

    public void setUser(String user) {
        editor.putString("user", user).commit();
    }

}
