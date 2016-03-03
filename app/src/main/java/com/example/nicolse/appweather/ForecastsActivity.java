package com.example.nicolse.appweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolse.appweather.Fragments.DeleteFavFragment;
import com.example.nicolse.appweather.Fragments.ForecastFragment;
import com.example.nicolse.appweather.entities.ForecastParcelable;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;
import com.example.nicolse.appweather.Fragments.DialogFavFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ForecastsActivity extends AppCompatActivity {

    private WeatherInfoParcelable weatherInfoParcelable = null;
    private List<ForecastFragment> fragmentsOfForecasts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecasts);
        Intent previousIntent = getIntent();
        fragmentsOfForecasts = new ArrayList<ForecastFragment>();
        if (savedInstanceState != null) {
            weatherInfoParcelable = savedInstanceState.getParcelable("key");
            System.out.println(weatherInfoParcelable.getDate());
            System.out.println(weatherInfoParcelable.getCountry());
            System.out.println(weatherInfoParcelable.getForecasts().size());


        } else {
            weatherInfoParcelable = previousIntent.getExtras().getParcelable("infoWeather");
        }

        ImageView imageView = (ImageView) findViewById(R.id.img_weather);
        TextView textDate = (TextView) findViewById(R.id.txt_forecast_date);
        TextView textCountry = (TextView) findViewById(R.id.txt_forecast_country);
        TextView textState = (TextView) findViewById(R.id.txt_forecast_state);


        imageView.setImageResource(weatherInfoParcelable.getIcon_id());
        textDate.setText(weatherInfoParcelable.getDate());
        textCountry.setText(weatherInfoParcelable.getCountry());
        textState.setText(weatherInfoParcelable.getState());
        ArrayList<ForecastParcelable> forecastParcelables;
        forecastParcelables = weatherInfoParcelable.getForecasts();


        for (ForecastParcelable forecast : forecastParcelables) {
            //TODO hacer algo con cada forecast

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ForecastFragment forecastFragment = new ForecastFragment();
            fragmentsOfForecasts.add(forecastFragment);
            forecastFragment.setForecast(forecast);
            ft.add(R.id.layout_inside_scroll, forecastFragment);
            ft.commit();

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("key", weatherInfoParcelable);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onPause() {
        super.onPause();
        for (ForecastFragment fragment : fragmentsOfForecasts) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }
    }

    public void doSave() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set favos = sharedPreferences.getStringSet("FAVORITOS", new HashSet());
        WeatherInfoParcelable weatherInfoParcelable = (WeatherInfoParcelable) getIntent().getExtras().getParcelable("infoWeather");
        //Set favos = new HashSet();
        favos.add(weatherInfoParcelable.getCountry().toString() + ", " + weatherInfoParcelable.getState().toString());
        editor.putStringSet("FAVORITOS", favos);
        editor.apply();

        }

    public void doDeleteFav() {
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE_INFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set favos = sharedPreferences.getStringSet("FAVORITOS", new HashSet());
        WeatherInfoParcelable weatherInfoParcelable = (WeatherInfoParcelable) getIntent().getExtras().getParcelable("infoWeather");
        String nameAndCountry = weatherInfoParcelable.getCountry().toString() + ", " + weatherInfoParcelable.getState().toString();
        favos.remove(nameAndCountry);
        editor.putStringSet("FAVORITOS", favos);
        editor.apply();
    }

    public void toDeleteFav(View view) {
        DeleteFavFragment deleteFavFragment = new DeleteFavFragment();
        deleteFavFragment.show(getSupportFragmentManager(), "Sample fragment");
    }

    public void toDialogFav(View view) {
        DialogFavFragment dialogFavFragment = new DialogFavFragment();
        dialogFavFragment.show(getSupportFragmentManager(), "Sample fragment");
    }
}