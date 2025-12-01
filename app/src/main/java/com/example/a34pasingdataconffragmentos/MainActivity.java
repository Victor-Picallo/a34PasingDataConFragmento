package com.example.a34pasingdataconffragmentos;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    //Faltan las etiquetas???
    boolean mDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MasterFragment masterFragment = null;
        FrameLayout frameLayout = findViewById(R.id.frameLayout);

        //Modo portrait
        if (frameLayout != null) {
            mDualPane = false;
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager()
                    .findFragmentByTag("MASTER");
            if(masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment,"MASTER");
            }

            DetailFragment detailFragment = (DetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetails);
            if (detailFragment != null) {
                fragmentTransaction.remove(detailFragment);
            }
            fragmentTransaction.commit();
        } else {
            //Modo landscape
            mDualPane = true;
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frameLayoutMaster);
            if(masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
            }

            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frameLayoutDetails);
            if (detailFragment == null) {
                detailFragment = new DetailFragment();
                fragmentTransaction.add(R.id.frameLayoutDetails, detailFragment);
            }
            fragmentTransaction.commit();
        } //Fin del else

        //Seteamos un CLICK sobre el FRAGMENT MASTER
        masterFragment.setOnMasterSelectedListener(new MasterFragment.OnMasterSelectedListener() {
            @Override
            public void onItemSelected(String countryName) {
                //Chequear el nombre del metodo de la interfaz
                sendCountryName(countryName);
            }
        });
    }

    //OnCreate
    //Metodo para el sendCountryName
    private void sendCountryName(String countryName) {
        DetailFragment detailFragment;
        if (mDualPane) {
            //Estamos en modo LANDSCAPE
            detailFragment = (DetailFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frameLayoutDetails);
            //El metodo del detailFragment
            detailFragment.showSelectedCountry(countryName);
            //Fin de LANDSCAPE
        } else {
            //Estamos en modo PORTRAIT
            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailFragment.KEY_COUNTRY_NAME, countryName);
            //Es como la peasa el KEY y el NAME al fragmentDetail
            //Luego lo extrae con getArguments y setea el TextView (ver DetailFragment)
            detailFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, detailFragment);
            //Como solo cabe un fragmento en el PORTRAIT, hace esto para volver al master
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            //Fin de PORTRAIT
        }
    }
}