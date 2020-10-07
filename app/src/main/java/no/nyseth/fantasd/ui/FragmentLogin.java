package no.nyseth.fantasd.ui;

import android.os.Bundle;
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
import no.nyseth.fantasd.shopnuser.User;
import no.nyseth.fantasd.ui.home.HomeFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLogin extends Fragment {
    //Fields
    TextView uidV;
    TextView pwdV;

    private User user = new User();

    public FragmentLogin() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);

        uidV = view.findViewById(R.id.login_uid);
        pwdV = view.findViewById(R.id.login_pwd);
        Button submitV = (Button) view.findViewById(R.id.login_submit);

        submitV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.login_submit:
                        loginUser();
                        break;
                }
            }
        });
        return view;
    }

    public void loginUser() {
        String uid = uidV.getText().toString();
        String pwd = pwdV.getText().toString();

        if (uid.isEmpty()) {
            uidV.setError("Intet brukernavn fylt inn");
            uidV.requestFocus();
            return;
        }
        if (pwd.isEmpty()) {
            uidV.setError("Intet passord fylt inn");
            uidV.requestFocus();
            return;
        }

        Call<ResponseBody> call = FantApi.getSINGLETON().getApi().userLogin(uid,pwd);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    user.setJwt(response.body().toString());
                    Toast.makeText(getActivity(), "Ya logged inn!", Toast.LENGTH_LONG).show();
                    System.out.println(response.body().toString());

                    Navigation.findNavController(getView()).popBackStack();
                }
                else {
                    Toast.makeText(getActivity(), "Noe gikk galt", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
