package no.nyseth.fantasd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import no.nyseth.fantasd.shopnuser.ItemList;
import no.nyseth.fantasd.shopnuser.UserStatus;
import no.nyseth.fantasd.ui.FragmentAddItem;
import no.nyseth.fantasd.ui.FragmentLogin;
import no.nyseth.fantasd.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Logg inn!!!", Snackbar.LENGTH_LONG)
                //.setAction("Action", null)
                //        .show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
               R.id.nav_home, R.id.nav_login, R.id.nav_create)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ItemList.getListInstance().checkList();

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int menuId = destination.getId();
                switch(menuId) {
                    case R.id.nav_login:
                        Toast.makeText(MainActivity.this, "Logg inn", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_create:
                        Toast.makeText(MainActivity.this, "Registrering", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_additem:
                        Toast.makeText(MainActivity.this, "Legge ut", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    case R.id.nav_buyitem:
                        Toast.makeText(MainActivity.this, "Kjøp", Toast.LENGTH_LONG).show();
                        fab.hide();
                        break;
                    default:
                        fab.show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}