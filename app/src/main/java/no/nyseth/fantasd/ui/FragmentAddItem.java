package no.nyseth.fantasd.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import no.nyseth.fantasd.network.FantApi;
import no.nyseth.fantasd.shopnuser.Items;
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
    private User user = new User();

    public FragmentAddItem() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public void addItem() {
        String itemTitle = itemTitleV.getText().toString();
        String itemPrice = itemPriceV.getText().toString();
        String itemDesc = itemDescV.getText().toString();
        String authHeader = user.getJwt();

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

        Call<ResponseBody> call = FantApi.getSINGLETON().getApi()
                .addItem(authHeader,itemTitle,itemPrice,itemDesc);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Fragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    Toast.makeText(getActivity(), "Ok", Toast.LENGTH_LONG).show();
                    System.out.print(response.body().toString());
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
        return super.onCreateView(inflater, container, savedInstanceState);
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
