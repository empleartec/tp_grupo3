package com.example.nicolse.appweather.InfoAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.nicolse.appweather.ObjectsFromJSON.Channel;
import com.example.nicolse.appweather.ObjectsFromJSON.Condition;
import com.example.nicolse.appweather.R;
import com.example.nicolse.appweather.neighbours.MainWeatherNeighbour;
import com.example.nicolse.appweather.neighbours.NeighbourWeather;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by NicolásE on 02/02/2016.
 */
public class WeatherInfoAdapter implements GoogleMap.InfoWindowAdapter {



    private Context context;
    private Map< Marker,Channel> conditionXMarker;
    private Map<Marker,NeighbourWeather> markerNeighboursXNeighbourWeather;

    public WeatherInfoAdapter(Context context,Map<Marker,Channel> conditionXMarker,Map<Marker,NeighbourWeather> markerNeighboursXNeighbourWeather) {
        super();
        this.context = context;
        this.conditionXMarker=conditionXMarker;
        this.markerNeighboursXNeighbourWeather=markerNeighboursXNeighbourWeather;
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



        if (conditionXMarker.containsKey(marker)){
            Channel channel=conditionXMarker.get(marker);
            Condition condition = channel.getItem().getCondition();
            textTemp.setText("Temperature: " + condition.getTemp() + "ºC");
            textWeather.setText("Weather: " + "'" + condition.getText() + "'");
            textDesc.setText("Date: " + condition.getDate().toUpperCase());
        }else{
            if(markerNeighboursXNeighbourWeather.containsKey(marker)){
               NeighbourWeather neighbourWeather= markerNeighboursXNeighbourWeather.get(marker);
               MainWeatherNeighbour main= neighbourWeather.getMain();
                textTemp.setText("Temperature: " +main.getTemp() + "ºC");
                textWeather.setText("Humidity: " + "'" +main.getHumidity()+ "'");
                textDesc.setText("Pressure:"+main.getPressure());
            }

        }

        return v;
    }


}
