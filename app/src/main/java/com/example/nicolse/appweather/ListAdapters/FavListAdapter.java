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
public class FavListAdapter extends BaseAdapter {

    private Context context;

    private List<PlaceYahoo> listFav;

    public FavListAdapter(Context context, List<PlaceYahoo> listFav){
        this.context = context;
        this.listFav = listFav;
    }

    @Override
    public int getCount() {
        return listFav.size();
    }

    @Override
    public Object getItem(int position) {
        return listFav.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listFav.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = LayoutInflater.from(context).inflate(R.layout.list_view_favorites, parent, false);
        TextView textPlaceCountry = (TextView) layout.findViewById(R.id.fav_country);
        TextView textPlaceCity = (TextView) layout.findViewById(R.id.fav_city);
        TextView textPlaceFclName = (TextView) layout.findViewById(R.id.fav_fcl_name);

        final PlaceYahoo place = (PlaceYahoo) getItem(position);
        textPlaceCountry.setText(place.getCountry());
        textPlaceCity.setText(place.getName());
        textPlaceFclName.setText(String.valueOf(place.getAdmin1() + ", " + place.getAdmin2()));

        layout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(context, "Searching location...", Toast.LENGTH_SHORT).show();
                                          new GetWeatherTask(context).execute(/*"Buenos Aires,AR"*/ place.getName() + "," + place.getCountry());
                                      }
                                  }
        );

        return layout;
    }
}