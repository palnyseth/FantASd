package no.nyseth.fantasd.shopnuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import no.nyseth.fantasd.network.FantApi;
import no.nyseth.fantasd.network.FantApi2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        itemsResponse = new MutableLiveData<>();
    }

    public void getItems() {
//        Call<List<Items>> call = FantApi.getSINGLETON().getApi().getItems();
//        call.enqueue(new Callback<List<Items>>() {
//            @Override
//            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
//                if (response.isSuccessful()) {
//                    itemsResponse.postValue(response.body());
//                    System.out.println("Body: " + response.body());
//                    System.out.println("Item shit: " + itemsResponse.getValue());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Items>> call, Throwable t) {
//
//            }
//        });
    }

    public LiveData<List<Items>> getItemResponse() {
        return itemsResponse;
    }
}
