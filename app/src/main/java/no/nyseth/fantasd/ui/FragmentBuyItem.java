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
import androidx.navigation.Navigation;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.network.FantApi;
import no.nyseth.fantasd.shopnuser.LoggedInUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBuyItem extends Fragment {
    TextView itemIdV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyitem, container,false);

        itemIdV = view.findViewById(R.id.buyitem_id);
        Button submitV = (Button) view.findViewById(R.id.kjopdrit);

        submitV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.kjopdrit:
                        System.out.println("Ja knappen funker");
                        Toast.makeText(getActivity(), "Knapp funke", Toast.LENGTH_LONG).show();
                        buyItem();
                        break;
                }
            }
        });

        return view;
    }

    private void buyItem() {
        String token = "Bearer " + LoggedInUser.getInstance().getJwt();
        String itemId = itemIdV.getText().toString();

        if (itemId.isEmpty()) {
            itemIdV.setError("DU HAR ITTE VÆLGT NOE ITEM");
            itemIdV.requestFocus();
            return;
        }
        if (token == null) {
            itemIdV.setError("Logg inn for fæn");
            itemIdV.requestFocus();
            Toast.makeText(getActivity(), "IKKE LOGGA INN", Toast.LENGTH_LONG).show();
            System.out.println("ingen jwt");

            Call<ResponseBody> call = FantApi.getSINGLETON().getApi()
                    .buyItem("Bearer " + LoggedInUser.getInstance().getJwt(), itemIdV.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(getActivity(), "Kjøpt", Toast.LENGTH_LONG).show();
                    System.out.println(response.body().toString());
                    Navigation.findNavController(getView()).popBackStack();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(),"Noe gikk galt", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
