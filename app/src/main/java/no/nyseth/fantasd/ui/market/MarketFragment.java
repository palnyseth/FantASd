package no.nyseth.fantasd.ui.market;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.shopnuser.Items;

public class MarketFragment extends Fragment {

    private MarketViewModel marketViewModel;
    private MarketViewAdapter marketViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        marketViewModel = new ViewModelProvider(this).get(MarketViewModel.class);
        marketViewAdapter = new MarketViewAdapter();

        View root = inflater.inflate(R.layout.fragment_market, container, false);
        final TextView textView = root.findViewById(R.id.text_market);
        marketViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerview_market);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(marketViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        marketViewModel.getItems();
        marketViewModel.getItemListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Items>>() {
            @Override
            public void onChanged(List<Items> items) {
                marketViewAdapter.setListOfItems(items);
            }
        });
        return root;
    }

}
