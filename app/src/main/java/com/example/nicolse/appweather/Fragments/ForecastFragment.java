package com.example.nicolse.appweather.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolse.appweather.R;
import com.example.nicolse.appweather.entities.ForecastParcelable;

/**
 * Created by NicolásE on 07/02/2016.
 */
public class ForecastFragment extends Fragment {

    private final String DATE="";
    private final String DAY="Day: ";
    private final String HIGH="High temperature: ";
    private final String LOW="Low temperature: ";
    private final String TEXT="Weather: ";

    private  ForecastParcelable forecastParcelable;

   public void setForecast(ForecastParcelable forecastParcelable){
       this.forecastParcelable = forecastParcelable;
   }

    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast,container, false);
        TextView textDate= (TextView)view.findViewById(R.id.txt_forecast_date_frag);
        TextView textText= (TextView)view.findViewById(R.id.txt_forecast_text_frag);
        TextView textHigh= (TextView)view.findViewById(R.id.txt_forecast_high_frag);
        TextView textLow= (TextView)view.findViewById(R.id.txt_forecast_low_frag);

        ImageView imageWeather = (ImageView) view.findViewById(R.id.image_weather_forecast);

        int resourceId = getResources().getIdentifier("drawable/icon_weather_" + forecastParcelable.getCode(), null, getActivity().getPackageName());
        Drawable weatherImageDrawable = getResources().getDrawable(resourceId);
        imageWeather.setImageDrawable(weatherImageDrawable);


        textDate.setText(DATE+forecastParcelable.getDate()+", "+forecastParcelable.getDay());
        textText.setText(TEXT+forecastParcelable.getText());
        textHigh.setText(HIGH+forecastParcelable.getHigh()+"°C");
        textLow.setText(LOW+forecastParcelable.getLow()+"°C");
        return view;
    }
}
