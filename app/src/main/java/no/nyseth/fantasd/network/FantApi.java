package no.nyseth.fantasd.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FantApi {
   private static final String BASE_URL = "http://192.168.0.149:8080/FantProsjekt/api/";
    private static FantApi SINGLETON;
    Retrofit retrofit = null;

    public FantApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


    public static synchronized FantApi getSINGLETON() {
        if (SINGLETON == null) { SINGLETON = new FantApi(); } return SINGLETON;
    }

    public FantApi2 getApi() {
        return retrofit.create(FantApi2.class);
    }
}