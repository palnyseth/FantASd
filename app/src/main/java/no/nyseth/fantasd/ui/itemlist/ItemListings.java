package no.nyseth.fantasd.ui.itemlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemListings {
    @SerializedName("itemId")
    @Expose
    private long itemId;

    @SerializedName("itemTitle")
    @Expose
    private String itemTitle;

    @SerializedName("itemPrice")
    @Expose
    private String itemPrice;

    @SerializedName("itemDesc")
    @Expose
    private String itemDesc;

    public ItemListings(String itemTitle, String itemPrice, String itemDesc) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemDesc = itemDesc;
    }

    @Override
    public String toString() {
        return "Listing{" + "itemId=" + itemId + ", itemTitle=" + itemTitle + ", itemPrice=" + itemPrice + ", itemDesc=" + itemDesc + "}";
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
