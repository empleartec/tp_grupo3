package com.example.nicolse.appweather.InfoAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.nicolse.appweather.ObjectsFromJSON.Condition;
import com.example.nicolse.appweather.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.zip.Inflater;

/**
 * Created by NicolásE on 02/02/2016.
 */
public class WeatherInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private Condition condition;

    public WeatherInfoAdapter(Context context,Condition condition) {
        super();
        this.context = context;
        this.condition = condition;
    }

    // Use default InfoWindow frame
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


    // Defines the contents of the InfoWindow
    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.weather_info, null);
        TextView textTemp = (TextView) v.findViewById(R.id.txt_infoWeather_temp);
        TextView textWeather = (TextView) v.findViewById(R.id.txt_infoWeather_weather);
        TextView textDesc = (TextView) v.findViewById(R.id.txt_infoWeather_desc);
        textTemp.setText(condition.getTemp());
        textWeather.setText(condition.getText());
        textDesc.setText(condition.getDate());
        return v;
    }


}
