package com.example.nicolse.appweather;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolse.appweather.Fragments.ForecastFragment;
import com.example.nicolse.appweather.entities.ForecastParcelable;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;

import java.util.ArrayList;
import java.util.List;

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


}
