package com.example.a34pasingdataconffragmentos;

//Ojo a este importe
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
     public static String KEY_COUNTRY_NAME = "country_name";

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Curiosa linea, le pasamos una cadena? SI en un bundle al crea la vista
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(KEY_COUNTRY_NAME)) {
            showSelectedCountry(bundle.getString(KEY_COUNTRY_NAME));
        }
    }

    public void showSelectedCountry(String countryName) {
         TextView textViewCountryName = null;
         if(getView() != null) {
             textViewCountryName = getView().findViewById(R.id.textViewCountryName);
         } else {
             Toast.makeText(getActivity(), "getView() es null", Toast.LENGTH_SHORT).show();
         }

         textViewCountryName.setText(countryName);
//        TextView textViewContryName = getView().findViewById(R.id.textViewCountryName);
//        textViewContryName.setText(countryName);
    }
}
