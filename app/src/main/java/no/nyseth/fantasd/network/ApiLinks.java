package no.nyseth.fantasd.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Arrays;

public class ApiLinks {
    //Retired atm.

    //Class contains the different URLs the calls are meant to go to.

    //Base URL
    public static final String URL = "http://192.168.0.149:8080/FantProsjekt/";

    //User & auth key
    public static final String URL_USER_CREATE = URL + "api/auth/create";
    public static final String URL_USER_LOGIN = URL + "api/auth/login";
    public static final String URL_PUBLICKEY = URL + "api/publickey.pem";
    public static final String URL_USER_CURRENT = URL + "api/auth/currentuser";
    public static final String URL_USER_CHANGEPW = URL + "api/auth/changepassword";
    public static final String URL_USER_UPDATE = URL + "api/auth/updateuser";

    //Item
    public static final String URL_ITEM_GET = URL + "api/shop/getitems";
    public static final String URL_ITEM_ADD = URL + "api/shop/additem";
    public static final String URL_ITEM_REMOVE = URL + "api/shop/removeitem";
    public static final String URL_ITEM_BUY = URL + "api/shop/buyitem";

    public static final String PREF_KEY_BEARER_TOKEN = "bearerToken";

    public static final Response.ErrorListener newEL = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    };

}
