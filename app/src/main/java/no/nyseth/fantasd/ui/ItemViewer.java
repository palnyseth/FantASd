package no.nyseth.fantasd.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.shopnuser.ItemList;
import no.nyseth.fantasd.shopnuser.Items;

public class ItemViewer extends RecyclerView.Adapter<ItemViewer.ItemViewHolder> implements Filterable {
    public ItemViewer() {}

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout cl = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Items item = ItemList.getListInstance().getItemById(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return ItemList.getListInstance().getItemListSize();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public Items items;
        public TextView itemName;
        public TextView itemPrice;
        public TextView itemDesc;
        public ConstraintLayout cl;

        public ItemViewHolder(final ConstraintLayout cl) {
            super(cl);
            this.cl = cl;

            cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                }
            });
        }
        public void setItem(Items items) {
            this.items = items;

            //Check for item, if none exists it sets its properties
            if (items != null)
                this.itemName.setText(items.getItemTitle());
                this.itemPrice.setText(items.getItemPrice().toString()); //Typechange BigDec to String cuz exc.
                this.itemDesc.setText(items.getItemDesc());
        }
    }
}
