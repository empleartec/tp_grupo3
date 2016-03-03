package com.example.nicolse.appweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.nicolse.appweather.Fragments.DeleteFavFragment;
import com.example.nicolse.appweather.Fragments.ListFavFragment;
import com.example.nicolse.appweather.ObjectsFromJSON.PlaceYahoo;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FavouriteActivity extends AppCompatActivity implements ListFavFragment.FavouritesPersistance{

    private FragmentManager fragmentManager;
    private ListFavFragment listFavFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarFavourite);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fragmentManager = getSupportFragmentManager();
         listFavFragment=(ListFavFragment)fragmentManager.findFragmentById(R.id.favouriteFragment);
        /*listFavFragment = new ListFavFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, listFavFragment);
        fragmentTransaction.show(listFavFragment);
        fragmentTransaction.commit();*/
       // doLoad();
    }

    public List<PlaceYahoo>  loadFavourite() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);
        Set favos = new HashSet();
        favos = sharedPreferences.getStringSet("FAVORITOS", favos);
        List<PlaceYahoo> listFav = new ArrayList<PlaceYahoo>();

        if (favos != null) {
            for (Object favo : favos) {
                String[] f = favo.toString().split(",");
                PlaceYahoo py = new PlaceYahoo();
                py.setCountry(f[0]);
                py.setName(f[1]);
                listFav.add(py);
            }
        }
        return listFav;
    }


    public List<PlaceYahoo>  deleteFavourite() {
        List<PlaceYahoo> listFav = new ArrayList<PlaceYahoo>();
        return  listFav;
    }



//todo:aca se tiene que sacar el lugar de la lista de favoritos
    public void doDelete() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set favos = sharedPreferences.getStringSet("FAVORITOS", new HashSet());
       // WeatherInfoParcelable weatherInfoParcelable = (WeatherInfoParcelable) getIntent().getExtras().getParcelable("infoWeather");
        if(placeToDelete!=null) {
            favos.remove(placeToDelete);
        }
        System.out.println(loadFavourite().size());
        editor.putStringSet("FAVORITOS", favos);
        editor.apply();
        listFavFragment.updateList();
    }


    private String placeToDelete;
    //esto hace cuando se aprieta la crucesita en cualquier item de la lista
    public void toDeleteFav(View v) {
        placeToDelete= (String)v.getTag();
        Toast.makeText(this,placeToDelete,Toast.LENGTH_SHORT).show();
        System.out.println(placeToDelete);
        DeleteFavFragment deleteFavFragment = new DeleteFavFragment();
        deleteFavFragment.show(getSupportFragmentManager(), "Sample fragment");
    }

}

        //System.out.print("myToolbar.getTitle()");

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


//clave api bruno pc: AIzaSyAVzUn-MJf5tfVRm7G_2HB5eVU6wMBoJZ4
//clave api bruno noteb: AIzaSyDVt1sawxkq4mcxJeuRkATaCVNJJc-zZtE
//clave api nico: AIzaSyB27CjmPkuR-YWfYuffcEmK23EcvWAYWZo
