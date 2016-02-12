package com.example.nicolse.appweather;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.nicolse.appweather.AsyncTasks.GetPlacesTask;
import com.example.nicolse.appweather.Fragments.DialogFavFragment;
import com.example.nicolse.appweather.Fragments.ListPlacesFragment;
import com.example.nicolse.appweather.ObjectsFromJSON.Geoname;
import com.example.nicolse.appweather.InfoAdapters.WeatherInfoAdapter;
import com.example.nicolse.appweather.entities.ForecastParcelable;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;

//clave de la api de google: AIzaSyB27CjmPkuR-YWfYuffcEmK23EcvWAYWZo


public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback, GetPlacesTask.GetPlacesCallback ,OnInfoWindowClickListener {
    //build gradle(antes): compile 'com.google.android.gms:play-services-maps:8.1.0'
    private GoogleMap mapa;
    private FragmentManager fragmentManager;
    private SupportMapFragment supportMapFragment;
    private ListPlacesFragment listPlacesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarWeather);
        setSupportActionBar(myToolbar);

        FragmentManager fragmentManagerAux;
        fragmentManagerAux = fragmentManager;

        fragmentManager = getSupportFragmentManager();
        supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
        mapa = supportMapFragment.getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.setMyLocationEnabled(true);
        supportMapFragment.getMapAsync(this);
      //  mapa.setOnMarkerClickListener(this);  //seteamos el listener VER-->onMarkerClick(Marker marker)


        listPlacesFragment = new ListPlacesFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, listPlacesFragment);
        fragmentTransaction.hide(listPlacesFragment);
        fragmentTransaction.commit();


        if (Configuration.ORIENTATION_PORTRAIT == getResources().getConfiguration().orientation) {
            System.out.println("PORTRAIT");
        }
        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            System.out.println("LANDSCAPE");
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main_map_compat_action_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search_location);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.action_search_location), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //DO SOMETHING WHEN THE SEARCHVIEW IS CLOSING
                Toast.makeText(MainMapActivity.this, "SearchView was clossed" + searchView.getQuery(), Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(listPlacesFragment);
                fragmentTransaction.show(supportMapFragment);
                ImageButton imageButton = (ImageButton) findViewById(R.id.btn_favourite);
                imageButton.setVisibility(View.VISIBLE);
                fragmentTransaction.commit();
                return true;
            }
        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DialogFavFragment dialogFavFragment = new DialogFavFragment();
                dialogFavFragment.show(fragmentManager, "Sample Fragment");
                doSave();
                doLoad();*/





            }
        });

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        new GetPlacesTask(MainMapActivity.this, MainMapActivity.this).execute();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        return false;
                    }
                }

        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_favourites:
                Intent anIntent = new Intent(this, FavouriteActivity.class);
                startActivity(anIntent);//TODO : change for starActivityResults();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//TODO:para editar https://pixlr.com/editor/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setOnInfoWindowClickListener(this);
        mapa.setInfoWindowAdapter(new WeatherInfoAdapter(this));
        LatLng unknown = new LatLng(-31.79765, -65.00312);



        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_aux);
        // Resize the bitmap to 150x100 (width x height)
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 60, 60, true);
        // Loads the resized Bitmap into an ImageView
        mapa.addMarker(new MarkerOptions().position(unknown).icon(BitmapDescriptorFactory.fromBitmap(bMapScaled)));
        //   mapa.setOn
        mapa.moveCamera(CameraUpdateFactory.newLatLng(unknown));
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(unknown, 10));
    }




    public void doSave() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /*editor.putString("Country","Argentina");
        editor.putString("Province/State","Cordoba");
        editor.putString("City/County","Nono");
        editor.putFloat("Latitude", -31.79765f);
        editor.putFloat("Longitude", -65.00312f);*/

        Set<String> set1 = new HashSet<String>();
        List<String> list1 = new ArrayList<String>();
        list1.add("nombre:Nico");
        list1.add("country:Argentina");
        list1.add("Number:4321");

        Set<String> set2 = new HashSet<String>();
        List<String> list2 = new ArrayList<String>();
        list2.add("nombre:Pepetonio");
        list2.add("country:España");
        list2.add("Number:4423");

        set1.addAll(list1);
        editor.putStringSet("1", set1);

        set2.addAll(list2);
        editor.putStringSet("2", set2);

        editor.apply();
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



/*
    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
        return true;
    }
*/

    @Override
    protected void onPause() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(listPlacesFragment);
        fragmentTransaction.commitAllowingStateLoss();
        super.onPause();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            System.out.println("??????????????????????????????????????????");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
    }

    public void resetearFragmentos(View view) {
     /*   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(supportMapFragment);
        fragmentTransaction.show(listPlacesFragment);
        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_favourite);
        imageButton.setVisibility(View.INVISIBLE);
        fragmentTransaction.commit();*/

                        DialogFavFragment dialogFavFragment = new DialogFavFragment();
                dialogFavFragment.show(fragmentManager, "Sample Fragment");
                doSave();
                doLoad();
    }

    @Override
    public void updateListPlaces(List<Geoname> listGeonames) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(supportMapFragment);
        fragmentTransaction.show(listPlacesFragment);
        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_favourite);
        imageButton.setVisibility(View.INVISIBLE);
        fragmentTransaction.commit();
        listPlacesFragment.updateListPlaces(listGeonames);
        //listPlacesFragment;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this,PlaceActivity.class);
        WeatherInfoParcelable weatherInfoParcelable =new WeatherInfoParcelable();
        weatherInfoParcelable.setIcon_id(R.drawable.icon_aux);
        weatherInfoParcelable.setDate("1992/05/01");
        weatherInfoParcelable.setCountry("Argentina");
        weatherInfoParcelable.setState("Buenos Aires");

        ForecastParcelable forecastParcelable= new ForecastParcelable();
        forecastParcelable.setCode("30");
        forecastParcelable.setDate("1992");
        forecastParcelable.setDay("mon");
        forecastParcelable.setHigh("34");
        forecastParcelable.setLow("21");
        forecastParcelable.setText("cloudy");


        ForecastParcelable forecastParcelable1=new ForecastParcelable();
        forecastParcelable1.setCode("21");
        forecastParcelable1.setDate("1995");
        forecastParcelable1.setDay("sun");
        forecastParcelable1.setHigh("21");
        forecastParcelable1.setLow("19");
        forecastParcelable1.setText("rainy");


        ArrayList <ForecastParcelable> list=new ArrayList<ForecastParcelable>();
            list.add(forecastParcelable);
           list.add(forecastParcelable1);
        weatherInfoParcelable.setForecasts(list);
        intent.putExtra("infoWeather", weatherInfoParcelable);
        startActivity(intent);
    }
}
