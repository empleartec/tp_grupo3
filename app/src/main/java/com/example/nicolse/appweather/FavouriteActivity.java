package com.example.nicolse.appweather;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolse.appweather.ListAdapters.FavListAdapter;
import com.example.nicolse.appweather.ObjectsFromJSON.PlaceYahoo;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//import android.support.annotation.Nullable;


public class FavouriteActivity extends AppCompatActivity{

    private ForecastsActivity activity;
    private WeatherInfoParcelable weatherInfoParcelable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarFavourite);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        doLoad();

        /*
        Intent previousIntent = getIntent();
        WeatherInfoParcelable weatherInfoParcelable = (WeatherInfoParcelable) previousIntent.getExtras().getParcelable("infoWeather");

        TextView textCountry = (TextView) findViewById(R.id.txt_forecast_country);
        TextView textState = (TextView) findViewById(R.id.txt_forecast_state);

        textCountry.setText(weatherInfoParcelable.getCountry());
        textState.setText(weatherInfoParcelable.getState());
        */





        //System.out.print("myToolbar.getTitle()");
    }

/*
    @Override
    public void updateListPlaces(List<PlaceYahoo> listPlaces) {
        FavListAdapter favListAdapter = new FavListAdapter(getApplicationContext(), listPlaces);

        View view = findViewById(R.id.list_view_favourites);

        if (view == null) {
            System.out.println("EL GETVIRE() RETORNA UN NULL");
            //TODO : cuando se vuelve del otra activity al main como que se debe volver a instanciar el fragment o la lista
        }
        ListView miLista = (ListView) view.findViewById(R.id.list_view_favourites);
        miLista.setAdapter(favListAdapter);
    }
    */



    public void doLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);


        Set favos = new HashSet();
        favos = sharedPreferences.getStringSet("FAVORITOS", null);
        //String temp = sharedPreferences.getString("FAVORITOS", String.valueOf(favos));
        TextView textInfo = (TextView) findViewById(R.id.fav_info);


        if(favos != null)
        {
            for(Object favo:favos)
            {
                Toast.makeText(getApplicationContext(),
                        favo.toString(),
                        Toast.LENGTH_LONG).show();
                //textInfo.setText(favo.toString());


            }

        }
        //textInfo.setText(weatherInfoParcelable.getCountry());


        /*
        Map<String, Set<String>> keys = (Map<String, Set<String>>) sharedPreferences.getAll();

        for (Map.Entry<String, Set<String>> entry : keys.entrySet()) {
            Set<String> set = entry.getValue();
            System.out.println("Key:" + entry.getKey());
            System.out.println("Values:");
            //System.out.println("Country:" + entry.getCountry());
            for (String s : set) {
                System.out.println(s);
            }
            //Log.d("map values::::::", entry.getKey() + ": " + entry.getValue().toString());
        }
        //System.out.println(weatherInfoParcelable.getDate());
        //System.out.println(weatherInfoParcelable.getCountry());
        */
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
