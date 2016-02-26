package com.example.nicolse.appweather.ListAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolse.appweather.AsyncTasks.GetWeatherTask;
import com.example.nicolse.appweather.MainMapActivity;
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
        View layout = LayoutInflater.from(context).inflate(R.layout.list_view_favourites, parent, false);
        TextView textFavCountry = (TextView) layout.findViewById(R.id.fav_country);
        TextView textFavCity = (TextView) layout.findViewById(R.id.fav_city);
        //TextView textFavFclName = (TextView) layout.findViewById(R.id.fav_fcl_name);

        final PlaceYahoo place = (PlaceYahoo) getItem(position);
        textFavCountry.setText(place.getCountry());
        textFavCity.setText(place.getName());
        //textFavFclName.setText(String.valueOf(place.getAdmin1() + ", " + place.getAdmin2()));

        layout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(context, "Searching location...", Toast.LENGTH_SHORT).show();
                                          //new GetWeatherTask(context).execute(/*"Buenos Aires,AR"*/ place.getName() + "," + place.getCountry());
                                          Intent intent = new Intent(context, MainMapActivity.class);
                                          intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                          //intent.putExtra("woeid", String.valueOf(place.getWoeid()));
                                          intent.putExtra("Name", place.getName());
                                          intent.putExtra("Country", place.getCountry());
                                          //intent.putExtra("Province1", place.getAdmin1());
                                          //intent.putExtra("Province2", place.getAdmin2());
                                          context.startActivity(intent);
                                      }
                                  }

        );

        return layout;
    }
}