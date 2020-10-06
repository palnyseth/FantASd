package no.nyseth.fantasd.shopnuser;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import no.nyseth.fantasd.MainApp;
import no.nyseth.fantasd.network.ApiLinks;
import no.nyseth.fantasd.network.RequestGen;
import no.nyseth.fantasd.network.VolleyGen;

public class ItemList {
    //Class for handling the list of items in the database.
    private static ItemList listInstance = null;

    //Checks if there currently exists an instance of the itemlist.
    public static ItemList getListInstance(){
        if (listInstance == null) {
            listInstance = new ItemList();
        }
        return listInstance;
    }

    private ArrayList<Items> items = new ArrayList<>(); //List for all items

    public void checkList(Runnable cb) {
        RequestGen requestGen = new RequestGen(ApiLinks.URL_ITEM_GET, Request.Method.GET, Items[].class, new HashMap<String, String>(), new Response.Listener<Items[]>() {
            @Override
            public void onResponse(Items[] response) {
                items.clear();
                items.addAll(Arrays.asList(response));
                items = items.stream().filter(Items -> Items.getItemBuyer() == null).sorted((item1, item2) -> item1.getItemTitle().compareTo(item2.getItemTitle())).collect(Collectors.toCollection(ArrayList::new));
                if (cb != null) {
                    cb.run();
                }
            }}, ApiLinks.newEL
        );
        //System.out.println("-------------------");
        //System.out.println(ApiLinks.URL_ITEM_GET);
        System.out.println("-------------------");
        //VolleyGen.instance().addToRequestQue(requestGen);
        System.out.println(requestGen);
    }

    public void checkList() {
        checkList(null);
    }

    private ItemList() {
        checkList();
    }

    public int getItemListSize() {
        return items.size();
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public Items getItemById(int id) {
        return items.get(id);
    }

}
