package com.example.a34pasingdataconffragmentos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

public class MasterFragment extends ListFragment {

    //Metodo para poblar la lista
    public void onViewCreated(View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        String[] countries =
                new String[] {
                        "China",
                        "France",
                        "Germany",
                        "India",
                        "Russia",
                        "United Kingdom",
                        "United States"
                };

        //Es tan simple la lista que android resources ya te la proporciona
        //Evitamos crear un XML para el fragmento master
        ListAdapter countryAdapter = new ArrayAdapter<String>(
            getActivity(), android.R.layout.simple_list_item_1,
                countries);
        setListAdapter(countryAdapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnMasterSelectedListener != null) {
                    mOnMasterSelectedListener.onItemSelected( ( (TextView) view) .getText().toString());
                }
            }
        });
    }


    //Instancia de la interfaz
    private OnMasterSelectedListener mOnMasterSelectedListener = null;
    //Metodo que setea la interfaz
    public void setOnMasterSelectedListener (OnMasterSelectedListener listener) {
        mOnMasterSelectedListener = listener;
    }

    //Creamos una interfaz INNER y ya la usare en la clase con el metodo onListItemClick
    public interface OnMasterSelectedListener {

        void onItemSelected(String countryName);
    }
}
