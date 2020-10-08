package no.nyseth.fantasd.shopnuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import no.nyseth.fantasd.network.ApiLinks;
import no.nyseth.fantasd.network.FantApi;
import no.nyseth.fantasd.network.FantApi2;
import no.nyseth.fantasd.network.RequestGen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ItemList {
    private static final String BASE_URL = "http://192.168.0.149:8080/FantProsjekt/api/";
    private static ItemList ItemList;
    private static FantApi2 fantApi2;
    private MutableLiveData<List<Items>> itemsResponse;
    private MutableLiveData<Items> viewItemsResponse;
    private boolean authed;

    public static ItemList getInstance() {
        if (ItemList == null) {
            ItemList = new ItemList();
        }
        return ItemList;
    }

    private ItemList() {
    }

    public void getItems() {
        Call<List<Items>> call = FantApi.getSINGLETON().getApi().getItems();
        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.isSuccessful()) {
                    itemsResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Items>> getItemResponse() {
        return itemsResponse;
    }
}
