package no.nyseth.fantasd.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.shopnuser.ItemList;
import no.nyseth.fantasd.ui.ItemViewer;

public class HomeFragment extends Fragment {

    private ItemViewer iv;
    private RecyclerView rv;
    private RecyclerView.LayoutManager lm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FloatingActionButton fab = container.getRootView().findViewById(R.id.fab); fab.show();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        rv = root.findViewById(R.id.recycle_itemview);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(lm);
        iv = new ItemViewer();
        rv.setAdapter(iv);
        ItemList.getListInstance().checkList(
                () -> iv.notifyDataSetChanged());
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        SearchManager
                sm = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView
                sv = (SearchView) menu.findItem(R.id.bar_search).getActionView();
        sv.setSearchableInfo(sm.getSearchableInfo(getActivity().getComponentName()));
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ItemViewer itemViewer = new ItemViewer();
                itemViewer.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ItemViewer itemViewer = new ItemViewer();
                itemViewer.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}