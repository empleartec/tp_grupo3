package com.example.nicolse.appweather;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;

//clave de la api de google: AIzaSyB27CjmPkuR-YWfYuffcEmK23EcvWAYWZo


public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback,GetPlacesTask.GetPlacesCallback{
    //build gradle(antes): compile 'com.google.android.gms:play-services-maps:8.1.0'
    GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarWeather);
        setSupportActionBar(myToolbar);

        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapa = map.getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.setMyLocationEnabled(true);
        map.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main_map_compat_action_bar, menu);
        MenuItem searchItem =menu.findItem(R.id.action_search_location);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        new GetPlacesTask(MainMapActivity.this,MainMapActivity.this).execute();
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
            case R.id.action_setting:
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
        LatLng unknown = new LatLng(-34.76783656, -70);
        mapa.addMarker(new MarkerOptions().position(unknown).title("Marker in LA CORDILLERA"));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(unknown));
    }

    @Override
    public void mostrarToast(String algo) {
        Toast.makeText(this, algo, Toast.LENGTH_SHORT).show();
    }
}
