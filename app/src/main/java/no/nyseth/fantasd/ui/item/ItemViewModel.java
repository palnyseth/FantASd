package no.nyseth.fantasd.ui.item;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {

    private MutableLiveData<String> textYes;

    public ItemViewModel() {
        textYes = new MutableLiveData<>();
        textYes.setValue("GHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
    }
    public LiveData<String> getText() {
        return textYes;
    }
}
