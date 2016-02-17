package com.example.nicolse.appweather;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolse.appweather.entities.ForecastParcelable;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;

import java.util.ArrayList;

public class PlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        Intent previousIntent=getIntent();
               WeatherInfoParcelable weatherInfoParcelable = (WeatherInfoParcelable) previousIntent.getExtras().getParcelable("infoWeather");
        ImageView imageView= (ImageView) findViewById(R.id.img_weather);
        TextView textDate= (TextView) findViewById(R.id.txt_forecast_date);
        TextView textCountry= (TextView) findViewById(R.id.txt_forecast_country);
        TextView textState= (TextView) findViewById(R.id.txt_forecast_state);




        imageView.setImageResource(weatherInfoParcelable.getIcon_id());
        textDate.setText(weatherInfoParcelable.getDate());
        textCountry.setText(weatherInfoParcelable.getCountry());
        textState.setText(weatherInfoParcelable.getState());


        //ForecastParcelable forecastParcelable=(ForecastParcelable) previousIntent.getExtras().getParcelable("infoForecast");


        ArrayList <ForecastParcelable> forecastParcelables;
        forecastParcelables=weatherInfoParcelable.getForecasts();

        for (ForecastParcelable f: forecastParcelables) {
            Log.v("Forecasts", f.getCode());
            Log.v("Forecasts",f.getDate());
            Log.v("Forecasts",f.getDay());
            Log.v("Forecasts",f.getHigh());
            Log.v("Forecasts",f.getLow());
            Log.v("Forecasts",f.getText());
        }



    }
}
