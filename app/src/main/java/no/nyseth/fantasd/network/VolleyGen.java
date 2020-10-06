package no.nyseth.fantasd.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import no.nyseth.fantasd.MainApp;

public class VolleyGen {

    //Checks for existing instance, creates if none.
    private static VolleyGen volleyInstance = null;

    public static VolleyGen instance() {
        if (volleyInstance == null) {
            volleyInstance = new VolleyGen();
        }
        return volleyInstance;
    }

    private RequestQueue rq;


    private VolleyGen() {
        this.rq = Volley.newRequestQueue(MainApp.getFantAppContext());
    }

    public <T> void addRq(Request<T> tRequest) {
        rq.add(tRequest);
    }
}
