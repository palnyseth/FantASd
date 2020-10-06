package no.nyseth.fantasd.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import no.nyseth.fantasd.R;
import no.nyseth.fantasd.shopnuser.UserStatus;

public class FragmentLogin extends Fragment {
    //Fields
    TextView uidV;
    TextView pwdV;
    Button submitV;

    public FragmentLogin() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_login, container,false);
        //return view;
        FloatingActionButton fab = container.getRootView().findViewById(R.id.fab); fab.hide();
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        uidV = root.findViewById(R.id.login_uid);
        pwdV = root.findViewById(R.id.login_pwd);
        submitV = root.findViewById(R.id.login_submit);
        submitV.setOnClickListener(v -> {
            loginRequest(uidV.getText().toString(),
                    pwdV.getText().toString());
        });
        return root;
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

    public void loginRequest(String uid, String pwd) {
        Response.Listener<String> listener = response -> {
            UserStatus.getUserInstance().setJwt(response);
            UserStatus.getUserInstance().setUserActive(true);
            UserStatus.getUserInstance().getCurrentUser();
        };
    }
}
