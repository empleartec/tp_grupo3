package com.example.nicolse.appweather;


import android.content.Context;
//import android.support.annotation.Nullable;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nicolse.appweather.AsyncTasks.*;
import com.example.nicolse.appweather.AsyncTasks.GetPlacesTask;
import com.example.nicolse.appweather.ListAdapters.PlacesListAdapter;
import com.example.nicolse.appweather.ObjectsFromJSON.PlaceYahoo;
import com.example.nicolse.appweather.AsyncTasks.GetPlacesTask.GetPlacesCallback;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class FavouriteActivity extends AppCompatActivity {

    //private ForecastsActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarFavourite);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //lisView.doLoad();




        //System.out.print("myToolbar.getTitle()");
    }

    public void doLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);

        Map<String, Set<String>> keys = (Map<String, Set<String>>) sharedPreferences.getAll();

        for (Map.Entry<String, Set<String>> entry : keys.entrySet()) {
            Set<String> set = entry.getValue();
            System.out.println("Key:" + entry.getKey());
            System.out.println("Values:");
            for (String s : set) {
                System.out.println(s);
            }
            //Log.d("map values::::::", entry.getKey() + ": " + entry.getValue().toString());
        }
    }
}



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_favourite_compat_action_bar, menu);
        return true;
    }
*/

//clave api bruno pc: AIzaSyAVzUn-MJf5tfVRm7G_2HB5eVU6wMBoJZ4
//clave api bruno noteb: AIzaSyDVt1sawxkq4mcxJeuRkATaCVNJJc-zZtE
//clave api nico: AIzaSyB27CjmPkuR-YWfYuffcEmK23EcvWAYWZo
