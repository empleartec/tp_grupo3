package com.example.nicolse.appweather.ListAdapters;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolse.appweather.AsyncTasks.GetWeatherTask;
import com.example.nicolse.appweather.ObjectsFromJSON.Geoname;
import com.example.nicolse.appweather.R;

import java.util.List;

/**
 * Created by NicolásE on 29/01/2016.
 */
public class PlacesListAdapter extends BaseAdapter {

    private Context context;

    private List<Geoname> listGeonames;

    public PlacesListAdapter(Context context, List<Geoname> listGeonames){
        this.context=context;
        this.listGeonames=listGeonames;
    }

    @Override
    public int getCount() {
        return listGeonames.size();
    }

    @Override
    public Object getItem(int position) {
        return listGeonames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listGeonames.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = LayoutInflater.from(context).inflate(R.layout.list_view_places,parent,false);
        TextView textPlaceCountry= (TextView) layout.findViewById(R.id.place_country);
         TextView textPlaceCity= (TextView) layout.findViewById(R.id.place_city);
        TextView textPlaceFclName= (TextView) layout.findViewById(R.id.place_fcl_name);

        final Geoname geoname= (Geoname) getItem(position);
        textPlaceCountry.setText(geoname.getCountryName());
        textPlaceCity.setText(geoname.getName());
        textPlaceFclName.setText(geoname.getFclName());


        layout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(context,"hola", Toast.LENGTH_SHORT).show();
                                          new GetWeatherTask().execute("Buenos Aires,AR");
                                      }
                                  }
        );

        return layout;
    }
}
