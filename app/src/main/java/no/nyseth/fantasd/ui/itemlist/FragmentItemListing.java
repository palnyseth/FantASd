package no.nyseth.fantasd.ui.itemlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.network.FantApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentItemListing extends Fragment {

    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    ArrayList<ItemListings> itemListings = new ArrayList<>();
    private ListingAdapter listingAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_item_listing, container, false);

        recyclerView = view.findViewById(R.id.itemListings);

        Call<List<ItemListings>> call = FantApi.getSINGLETON().getApi().getItems();
        call.enqueue(new Callback<List<ItemListings>>() {
            @Override
            public void onResponse(Call<List<ItemListings>> call, Response<List<ItemListings>> response) {
                assert response.body() != null;
                itemListings = new ArrayList<>(response.body());
                listingAdapter = new ListingAdapter(view.getContext(), itemListings);
                listingAdapter.setItemListings(itemListings);
                recyclerView.setAdapter(listingAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                Toast.makeText(getContext(), "Nicerdicer", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<ItemListings>> call, Throwable t) {
                Toast.makeText(getContext(), "Fuck", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}