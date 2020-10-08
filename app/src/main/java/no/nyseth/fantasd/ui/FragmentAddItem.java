package no.nyseth.fantasd.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.network.FantApi;
import no.nyseth.fantasd.shopnuser.Items;
import no.nyseth.fantasd.shopnuser.LoggedInUser;
import no.nyseth.fantasd.shopnuser.User;
import no.nyseth.fantasd.ui.home.HomeFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAddItem extends Fragment {
    TextView itemTitleV;
    TextView itemPriceV;
    TextView itemDescV;
    private Items items = new Items();
    private LoggedInUser user = new LoggedInUser();

    public FragmentAddItem() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public void addItem() {
        String authHeader = user.getUserToken();
        String itemTitle = itemTitleV.getText().toString();
        String itemPrice = itemPriceV.getText().toString();
        String itemDesc = itemDescV.getText().toString();

        if (itemTitle.isEmpty()) {
            itemTitleV.setError("Intet tittel fylt inn");
            itemTitleV.requestFocus();
            return;
        }
        if (itemPrice.isEmpty()) {
            itemPriceV.setError("Intet pris fylt inn");
            itemPriceV.requestFocus();
            return;
        }
        if (itemDesc.isEmpty()) {
            itemDescV.setError("Intet beskrivelse fylt inn");
            itemDescV.requestFocus();
            return;
        }
        if (authHeader == null) {
            itemDescV.setError("Ikke logget inN!");
            itemDescV.requestFocus();
            Toast.makeText(getActivity(), "Ikke logget inn!", Toast.LENGTH_LONG).show();
            System.out.println("ingen jwt");
        }

        Call<ResponseBody> call = FantApi.getSINGLETON().getApi()
                .addItem(authHeader,itemTitle,itemPrice,itemDesc);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Lagt ut!", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().toString());

                    Navigation.findNavController(getView()).popBackStack();
                }
                else {
                    Toast.makeText(getActivity(),"Noe gikk galt", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additem, container,false);

        itemTitleV = view.findViewById(R.id.additem_name);
        itemPriceV = view.findViewById(R.id.additem_price);
        itemDescV = view.findViewById(R.id.additem_desc);
        Button submitV = (Button) view.findViewById(R.id.leggtil);

        submitV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.leggtil:
                        addItem();
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }

}
