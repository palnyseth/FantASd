package no.nyseth.fantasd.ui.market;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import no.nyseth.fantasd.shopnuser.ItemList;
import no.nyseth.fantasd.shopnuser.Items;

public class MarketViewModel extends AndroidViewModel {
    private MutableLiveData<String> text;
    private LiveData<List<Items>> itemsResponse;
    private ItemList itemList;


    public MarketViewModel(@NonNull Application application) {
        super(application);
        text = new MutableLiveData<String>();
        text.setValue("Shit fuuck");

        if (itemsResponse == null) {
            itemList = ItemList.getInstance();
            itemsResponse = itemList.getItemResponse();
        }
    }

    public void getItems() {
        itemList.getItems();
    }

    public LiveData<List<Items>> getItemListLiveData() {
        return itemsResponse;
    }

    public LiveData<String> getText() {
        return text;
    }
}
