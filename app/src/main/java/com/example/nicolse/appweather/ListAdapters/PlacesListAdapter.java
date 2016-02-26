package com.example.nicolse.appweather.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolse.appweather.AsyncTasks.GetWeatherTask;
import com.example.nicolse.appweather.ObjectsFromJSON.Geoname;
import com.example.nicolse.appweather.ObjectsFromJSON.PlaceYahoo;
import com.example.nicolse.appweather.R;

import java.util.List;

/**
 * Created by NicolásE on 29/01/2016.
 */
public class PlacesListAdapter extends BaseAdapter {

    private Context context;

    private List<PlaceYahoo> listPlaces;

    public PlacesListAdapter(Context context, List<PlaceYahoo> listPlaces){
        this.context = context;
        this.listPlaces = listPlaces;
    }

    @Override
    public int getCount() {
        return listPlaces.size();
    }

    @Override
    public Object getItem(int position) {
        return listPlaces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPlaces.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = LayoutInflater.from(context).inflate(R.layout.list_view_places, parent, false);
        TextView textPlaceCountry = (TextView) layout.findViewById(R.id.place_country);
        TextView textPlaceCity = (TextView) layout.findViewById(R.id.place_city);
        TextView textPlaceFclName = (TextView) layout.findViewById(R.id.place_fcl_name);

        final PlaceYahoo place = (PlaceYahoo) getItem(position);
        textPlaceCountry.setText(place.getCountry());
        textPlaceCity.setText(place.getName());
        textPlaceFclName.setText(String.valueOf(place.getAdmin1() + ", " + place.getAdmin2()));

        layout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(context, "Searching location...", Toast.LENGTH_SHORT).show();
                                          System.out.println("++++++++++++++++++++++++++++++" + place.getWoeid());
                                          //new GetWeatherTask(context).execute(String.valueOf(place.getWoeid()));
                                          new GetWeatherTask(context).execute(place.getName() + "," + place.getCountry());
                                      }
                                  }
        );

        return layout;
    }
}
