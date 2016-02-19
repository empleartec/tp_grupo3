package com.example.nicolse.appweather.Fragments;


import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.nicolse.appweather.ListAdapters.PlacesListAdapter;
import com.example.nicolse.appweather.ObjectsFromJSON.Geoname;
import com.example.nicolse.appweather.ObjectsFromJSON.PlaceYahoo;
import com.example.nicolse.appweather.R;

import java.util.ArrayList;
import java.util.List;
import com.example.nicolse.appweather.AsyncTasks.GetPlacesTask.GetPlacesCallback;

/**
 * Created by NicolásE on 29/01/2016.
 */
public class ListPlacesFragment extends Fragment implements GetPlacesCallback {


    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_places, container, false);
        return view;
    }

    @Override
    public void updateListPlaces(List<PlaceYahoo> listPlaces) {
        PlacesListAdapter placesListAdapter = new PlacesListAdapter(getContext(), listPlaces);

        View view = getView();

        if(view == null){
           System.out.println("EL GETVIRE() RETORNA UN NULL");
           //TODO : cuando se vuelve del otra activity al main como que se debe volver a instanciar el fragment o la lista
       }

        ListView miLista = (ListView) view.findViewById(R.id.list_view_places);
        miLista.setAdapter(placesListAdapter);
    }


}
