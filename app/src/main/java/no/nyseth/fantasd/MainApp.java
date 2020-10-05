package no.nyseth.fantasd;

import android.app.Application;
import android.content.Context;

public class MainApp extends Application {
    private static MainApp FantAppInstance;
    private static Context FantAppContext;

    public void onCreate() {
        super.onCreate();
        FantAppInstance = this;

    }

    public static MainApp getFantAppExists() {
        return FantAppInstance;
    }

    public static void setFantAppExists(MainApp newExists) {
        FantAppInstance = newExists;
    }

    public static Context getFantAppContext() {
        return FantAppContext;
    }

    public static void setFantAppContext(Context newContext) {
        FantAppContext = newContext;
    }
}
