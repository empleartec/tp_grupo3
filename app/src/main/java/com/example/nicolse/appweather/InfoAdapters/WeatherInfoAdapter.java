package com.example.nicolse.appweather.InfoAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.nicolse.appweather.ObjectsFromJSON.Channel;
import com.example.nicolse.appweather.ObjectsFromJSON.Condition;
import com.example.nicolse.appweather.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.Iterator;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by NicolásE on 02/02/2016.
 */
public class WeatherInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private Map< Marker,Channel> conditionXMarker;
    public WeatherInfoAdapter(Context context,Map<Marker,Channel> conditionXMarker) {
        super();
        this.context = context;
        this.conditionXMarker=conditionXMarker;
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
        Channel channel=conditionXMarker.get(marker);
        Condition condition = channel.getItem().getCondition();
        textTemp.setText("Temperature: " + condition.getTemp() + "ºC");
        textWeather.setText("Weather: " + "'" + condition.getText() + "'");
        textDesc.setText("Date: " + condition.getDate().toUpperCase());
        return v;
    }


}
