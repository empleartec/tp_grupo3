package com.example.nicolse.appweather.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.nicolse.appweather.AsyncTasks.GetPlacesTask;
import com.example.nicolse.appweather.FavouriteActivity;
import com.example.nicolse.appweather.ListAdapters.FavListAdapter;
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
public class ListFavFragment extends Fragment {

    private FavouriteActivity context;

    public interface FavouritesPersistance{
        List<PlaceYahoo> loadFavourite();
         List<PlaceYahoo> deleteFavourite();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (FavouriteActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_fav, container, false);
        List<PlaceYahoo> listFav=context.loadFavourite();
        FavListAdapter favListAdapter = new FavListAdapter(getActivity(), listFav);
        ListView miLista = (ListView) view.findViewById(R.id.list_view_favourites);
        miLista.setAdapter(favListAdapter);
        return view;
    }

    public void updateList(){
        List<PlaceYahoo> listFav=context.loadFavourite();
        FavListAdapter favListAdapter = new FavListAdapter(getActivity(), listFav);
        ListView miLista = (ListView) getView().findViewById(R.id.list_view_favourites);
        miLista.setAdapter(favListAdapter);
    }
    
}