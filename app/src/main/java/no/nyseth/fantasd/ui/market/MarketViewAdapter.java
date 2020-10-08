package no.nyseth.fantasd.ui.market;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.shopnuser.Items;

public class MarketViewAdapter extends RecyclerView.Adapter<MarketViewAdapter.ViewHolder> {

    private List<Items> listOfItems = new ArrayList<>();

    @NonNull
    @Override
    public MarketViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewAdapter.ViewHolder holder, int position) {
        Items items = listOfItems.get(position);
        holder.itemTitle.setText(items.getItemTitle());
        holder.itemPrice.setText(items.getItemPrice().toString());
        holder.itemDesc.setText(items.getItemDesc());
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    public void setListOfItems(List<Items> items) {
        this.listOfItems = listOfItems;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTitle;
        TextView itemPrice;
        TextView itemDesc;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemTitle = view.findViewById(R.id.itemlist_name);
            itemPrice = view.findViewById(R.id.itemlist_price);
            itemDesc = view.findViewById(R.id.itemlist_desc);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "clicked" + getLayoutPosition() + " - " + this.itemTitle.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
