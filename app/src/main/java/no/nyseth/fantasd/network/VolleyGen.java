package no.nyseth.fantasd.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import no.nyseth.fantasd.MainApp;

public class VolleyGen {
    private static VolleyGen instance = null;
    private RequestQueue requestQueue;

    private VolleyGen() {
        this.requestQueue = Volley.newRequestQueue(MainApp.getFantAppContext());
    }

    public static VolleyGen instance() {
        if (instance == null) {
            instance = new VolleyGen();
        }
        return instance;
    }

    public <T> void addToRequestQue(Request<T> request) {
        requestQueue.add(request);
    }

}
