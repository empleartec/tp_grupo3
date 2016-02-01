package com.example.nicolse.appweather;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.nicolse.appweather.AsyncTasks.GetPlacesTask;
import com.example.nicolse.appweather.Fragments.DialogFavFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;

//clave de la api de google: AIzaSyB27CjmPkuR-YWfYuffcEmK23EcvWAYWZo


public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback,GetPlacesTask.GetPlacesCallback,OnMarkerClickListener{
    //build gradle(antes): compile 'com.google.android.gms:play-services-maps:8.1.0'
    private GoogleMap mapa;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarWeather);
        setSupportActionBar(myToolbar);
        fragmentManager= getSupportFragmentManager();
        SupportMapFragment map = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
        mapa = map.getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.setMyLocationEnabled(true);
        map.getMapAsync(this);
        mapa.setOnMarkerClickListener(this);//seteamos el listener
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main_map_compat_action_bar, menu);
        MenuItem searchItem =menu.findItem(R.id.action_search_location);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFavFragment dialogFavFragment = new DialogFavFragment();
                dialogFavFragment.show(fragmentManager, "Sample Fragment");
                doSave();
                doLoad();
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
                        Toast.makeText(MainMapActivity.this, newText, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }

        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.action_favourites:
                Intent anIntent = new Intent(this,FavouriteActivity.class);
                startActivity(anIntent);//TODO : change for starActivityResults();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        LatLng unknown = new LatLng(-31.79765, -65.00312);
        mapa.addMarker(new MarkerOptions().position(unknown).title("Marker in LA CORDILLERA")).setSnippet("nono");
        mapa.moveCamera(CameraUpdateFactory.newLatLng(unknown));
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(unknown,10));
    }




    public void doSave (){
        SharedPreferences sharedPreferences=getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        /*editor.putString("Country","Argentina");
        editor.putString("Province/State","Cordoba");
        editor.putString("City/County","Nono");
        editor.putFloat("Latitude", -31.79765f);
        editor.putFloat("Longitude", -65.00312f);*/

        Set<String> set1 = new HashSet<String>();
        List<String> list1=new ArrayList<String>();
        list1.add("nombre:Nico");
        list1.add("country:Argentina");
        list1.add("Number:4321");

        Set<String> set2 = new HashSet<String>();
        List<String> list2=new ArrayList<String>();
        list2.add("nombre:Pepetonio");
        list2.add("country:España");
        list2.add("Number:4423");

        set1.addAll(list1);
        editor.putStringSet("1", set1);

        set2.addAll(list2);
        editor.putStringSet("2", set2);

        editor.apply();
    }


    public void doLoad(){
        SharedPreferences sharedPreferences=getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);

        Map<String,Set<String> > keys = (Map<String, Set<String>>) sharedPreferences.getAll();

        for(Map.Entry<String,Set<String>> entry : keys.entrySet()){
                Set<String> set=entry.getValue();
            System.out.println("Key:"+entry.getKey());
            System.out.println("Values:");
            for (String s : set) {
                System.out.println(s);
            }
            //Log.d("map values::::::", entry.getKey() + ": " + entry.getValue().toString());
        }
    }




    @Override
    public void mostrarToast(String algo) {

    }

    @Override
    public void changeFragment()         {
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
        return true;
    }
}
