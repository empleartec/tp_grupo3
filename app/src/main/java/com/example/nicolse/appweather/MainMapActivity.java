package com.example.nicolse.appweather;

import android.content.Intent;
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
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import com.example.nicolse.appweather.AsyncTasks.GetPlacesTask;
import com.example.nicolse.appweather.AsyncTasks.GetWeatherTask;
import com.example.nicolse.appweather.Fragments.ListPlacesFragment;
import com.example.nicolse.appweather.ObjectsFromJSON.Channel;
import com.example.nicolse.appweather.ObjectsFromJSON.Condition;
import com.example.nicolse.appweather.InfoAdapters.WeatherInfoAdapter;
import com.example.nicolse.appweather.ObjectsFromJSON.Item;
import com.example.nicolse.appweather.ObjectsFromJSON.Location;
import com.example.nicolse.appweather.ObjectsFromJSON.PlaceYahoo;
import com.example.nicolse.appweather.ObjectsFromJSON.ResultWeatherInfo;
import com.example.nicolse.appweather.ObjectsFromJSON.Results;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;

//clave de la api de google: AIzaSyB27CjmPkuR-YWfYuffcEmK23EcvWAYWZo

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback, GetPlacesTask.GetPlacesCallback , OnInfoWindowClickListener, GetWeatherTask.WheaterTask, GoogleMap.OnMarkerClickListener {
    //build gradle(antes): compile 'com.google.android.gms:play-services-maps:8.1.0'
    private GoogleMap mapa;
    private FragmentManager fragmentManager;
    private SupportMapFragment supportMapFragment;
    private ListPlacesFragment listPlacesFragment;

    private Map<Marker,Channel> conditionXMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarWeather);
        setSupportActionBar(myToolbar);
        fragmentManager = getSupportFragmentManager();
        supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
        mapa = supportMapFragment.getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.setMyLocationEnabled(true);
        supportMapFragment.getMapAsync(this);
        conditionXMarker=new HashMap<Marker,Channel>();
        if (Configuration.ORIENTATION_PORTRAIT == getResources().getConfiguration().orientation) {
            System.out.println("PORTRAIT");
        }
        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            System.out.println("LANDSCAPE");
        }

        mapa.setOnMarkerClickListener(this);

     /*   FragmentManager fragmentManagerAux;
        fragmentManagerAux = fragmentManager;

        fragmentManager = getSupportFragmentManager();
        supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
        mapa = supportMapFragment.getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.setMyLocationEnabled(true);
        supportMapFragment.getMapAsync(this);
      //  mapa.setOnMarkerClickListener(this);  //seteamos el listener VER-->onMarkerClick(Marker marker)

        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");

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
     */
    }

/*
    @Override //para que el intent generado por un favorito busque la ubicacion en el mapa junto a su info de clima
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String name = intent.getStringExtra("Name");
        String country =  intent.getStringExtra("Country");
        GetWeatherTask getWeatherTask = new GetWeatherTask(this);
        getWeatherTask.execute(name + "," + country);
    }
    */

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
                Toast.makeText(MainMapActivity.this, "SearchView was clossed (" + searchView.getQuery() + ")", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(listPlacesFragment);
                fragmentTransaction.show(supportMapFragment);
                fragmentTransaction.commit();
                return true;
            }
        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try {
                            query = URLEncoder.encode(query, "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new AssertionError("UTF-8 is unknown");
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                            //Log.e("Error: ", e.getMessage());
                        }
                        new GetPlacesTask(MainMapActivity.this, MainMapActivity.this).execute(query);
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
        Condition condition = new Condition();
        condition.setText("N/A");
        condition.setCode("N/A");
        condition.setDate("N/A");
        condition.setTemp("N/A");
        //mapa.setInfoWindowAdapter(new WeatherInfoAdapter(this, condition));
        /*LatLng unknown = new LatLng(-31.79765, -65.00312);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_aux);
        //Resize the bitmap to 150x100 (width x height)
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 60, 60, true);
        //Loads the resized Bitmap into an ImageView
        mapa.addMarker(new MarkerOptions().position(unknown).icon(BitmapDescriptorFactory.fromBitmap(bMapScaled)));
        //mapa.setOn
        mapa.moveCamera(CameraUpdateFactory.newLatLng(unknown));
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(unknown, 10));*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        fragmentManager = getSupportFragmentManager();
        //mapa.setOnMarkerClickListener(this);  //seteamos el listener VER-->onMarkerClick(Marker marker)
        listPlacesFragment = new ListPlacesFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, listPlacesFragment);
        fragmentTransaction.hide(listPlacesFragment);
        fragmentTransaction.commit();

        if (getIntent().hasExtra("Name")) {
            String name = getIntent().getStringExtra("Name");
            String country = getIntent().getStringExtra("Country");
           // String woeid= getIntent().getStringExtra("woeid");
            GetWeatherTask getWeatherTask = new GetWeatherTask(this);
           // getWeatherTask.execute(woeid);
            getWeatherTask.execute(name + "," + country);

        }

    }


/*
    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
        return true;
    }}
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


    @Override
    public void updateListPlaces(List<PlaceYahoo> listPlaces) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(supportMapFragment);
        fragmentTransaction.show(listPlacesFragment);
        fragmentTransaction.commit();
        listPlacesFragment.updateListPlaces(listPlaces);
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, ForecastsActivity.class);
        WeatherInfoParcelable weatherInfoParcelable = new WeatherInfoParcelable();
        Channel channel = conditionXMarker.get(marker);
        Condition condition = channel.getItem().getCondition();
        Location location = channel.getLocation();
        int iconId = getResources().getIdentifier("drawable/icon_weather_" + condition.getCode(), null, getPackageName());
        weatherInfoParcelable.setIcon_id(iconId);
        weatherInfoParcelable.setDate(condition.getDate());
        weatherInfoParcelable.setCountry(location.getCountry());
        weatherInfoParcelable.setState(location.getCity());
        weatherInfoParcelable.setForecasts(channel.getItem().getForecast());
        intent.putExtra("infoWeather", weatherInfoParcelable);
        startActivity(intent);
    }

    @Override
    public void updateWeatherInMap(ResultWeatherInfo resultWeatherInfo) {


        if(resultWeatherInfo==null){
            System.out.println("Is null");
        }

        Results results = resultWeatherInfo.getQuery().getResults();
        Channel channel = results.getChannel();
        Item item = channel.getItem();
        Condition condition = item.getCondition();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(listPlacesFragment);
        fragmentTransaction.show(supportMapFragment);
        fragmentTransaction.commit();
        Toast.makeText(this, "Latitude:" + item.getLatitude() + " Longitude:" + item.getLongitude(), Toast.LENGTH_SHORT).show();
        LatLng unknown = new LatLng(Double.parseDouble(item.getLatitude()), Double.parseDouble(item.getLongitude()));



        int iconId = getResources().getIdentifier("drawable/icon_weather_"+ condition.getCode() , null, getPackageName());
        if(iconId==0){
            condition.setCode("na");
             iconId = getResources().getIdentifier("drawable/icon_weather_"+ condition.getCode() , null, getPackageName());
        }
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), iconId);

        //Resize the bitmap to 150x100 (width x height)
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 60, 60, true);
        //Loads the resized Bitmap into an ImageView

        Marker marker=mapa.addMarker(new MarkerOptions().position(unknown).icon(BitmapDescriptorFactory.fromBitmap(bMapScaled)));
        this.addConditionToMarker(marker,channel);
        mapa.moveCamera(CameraUpdateFactory.newLatLng(unknown));
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(unknown, 10));
        mapa.setOnInfoWindowClickListener(this);
        mapa.setInfoWindowAdapter(new WeatherInfoAdapter(this, conditionXMarker));

    }



    public void addConditionToMarker(Marker anMarker,Channel channel ){
        if(!conditionXMarker.containsKey(anMarker)){
            conditionXMarker.put(anMarker, channel);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this,"HolaCapo",Toast.LENGTH_SHORT).show();
        return false;
    }
}


