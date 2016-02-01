package com.example.nicolse.appweather.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nicolse.appweather.ListAdapters.PlacesListAdapter;
import com.example.nicolse.appweather.ObjectsFromJSON.Geoname;
import com.example.nicolse.appweather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NicolásE on 29/01/2016.
 */
public class ListPlacesFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_places,container,false);
        List <Geoname> lista= new ArrayList<Geoname>();
        Geoname g1=new Geoname();
        g1.setName("pepe");
        g1.setCountryName("argento");
        g1.setAdminName1("koala");

        Geoname g2=new Geoname();
        g2.setName("pepe");
        g2.setCountryName("argento");
        g2.setAdminName1("koala");

        Geoname g3=new Geoname();
        g3.setName("pepe");
        g3.setCountryName("argento");
        g3.setAdminName1("koala");


        Geoname g4=new Geoname();
        g4.setName("pepe");
        g4.setCountryName("argento");
        g4.setAdminName1("koala");


        Geoname g5=new Geoname();
        g5.setName("pepe");
        g5.setCountryName("argento");
        g5.setAdminName1("koala");

        lista.add(g1);
        lista.add(g2);
        lista.add(g3);
        lista.add(g4);
        lista.add(g5);

        PlacesListAdapter placesListAdapter = new PlacesListAdapter(getContext(),lista);
        ListView miLista = (ListView) view.findViewById(R.id.list_view_places);
        miLista.setAdapter(placesListAdapter);
        return view;
    }
}
