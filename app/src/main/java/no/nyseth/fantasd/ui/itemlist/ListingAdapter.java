package no.nyseth.fantasd.ui.itemlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import no.nyseth.fantasd.R;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ItemListings> itemListings = new ArrayList<>();

    public ListingAdapter(Context context, ArrayList<ItemListings> itemListings) {
        this.context = context;
        this.itemListings = itemListings;
    }

    @NonNull
    @Override
    public ListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textItemTitle.setText(itemListings.get(position).getItemTitle());
        holder.textItemPrice.setText(String.valueOf(itemListings.get(position).getItemPrice()));
        holder.textItemDesc.setText(itemListings.get(position).getItemDesc());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,itemListings.get(position).getItemTitle() + "TREYKKTKKETK", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemListings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textItemTitle;
        private TextView textItemPrice;
        private TextView textItemDesc;
        private CardView parent;

        public ViewHolder(@NonNull View view) {
            super(view);
            textItemTitle = view.findViewById(R.id.textItemTitle);
            textItemPrice = view.findViewById(R.id.textItemPrice);
            textItemDesc = view.findViewById(R.id.textItemDesc);
            parent = view.findViewById(R.id.parent);

        }
    }

    public void setItemListings(ArrayList<ItemListings> itemListings) {
        this.itemListings = itemListings;
        notifyDataSetChanged();
    }


}
