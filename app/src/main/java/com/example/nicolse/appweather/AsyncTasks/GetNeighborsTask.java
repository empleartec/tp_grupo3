package com.example.nicolse.appweather.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.nicolse.appweather.neighbours.NeighbourWeather;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NicolásE on 26/02/2016.
 */
public class GetNeighborsTask extends AsyncTask<String, String,  List<NeighbourWeather>> {


    private Context context;
    private NeighboursTask callback;
    private Marker currentMarker;
    public GetNeighborsTask(Context context,NeighboursTask callback,Marker currentMarker){
        this.context=context;
        this.callback=callback;
        this.currentMarker=currentMarker;
    }



    @Override
    protected   List<NeighbourWeather> doInBackground(String... woeidString) {

        float lonCenter = Float.valueOf(woeidString[0]);
        float latCenter = Float.valueOf(woeidString[1]);

        float lonMinus =lonCenter-1.5f;
        float lonPlus = lonCenter+1.5f;
        float latMinus = latCenter-1.5f;
        float latPlus   =  latCenter +1.5f;

        List <String> sectors=new ArrayList<String>();

        sectors.add(searchPlace(lonMinus,latMinus));
        sectors.add(searchPlace(lonMinus,latPlus));
        sectors.add(searchPlace(lonPlus,latMinus));
        sectors.add(searchPlace(lonPlus,latPlus));




        Gson gson = new Gson();
        List<NeighbourWeather> listNeighbourWeather=new ArrayList<NeighbourWeather>();
        if (sectors != null) {
            for (String json: sectors) {
                NeighbourWeather neighbourWeather =gson.fromJson(json, NeighbourWeather.class);
                listNeighbourWeather.add(neighbourWeather);
            }
        }




        return listNeighbourWeather;
    }


    @Override
    protected void onPostExecute(  List<NeighbourWeather> listNeighbourWeather) {
        callback.showNeighbours(listNeighbourWeather,this.currentMarker);
    }


    private String searchPlace( float lon,float lat){
        String endpoint = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&units=metric&appid=81bfaff12e0252b677b030b6014248ad";
        try {
            URL url = new URL(endpoint);
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;

    }



    public interface NeighboursTask{
      void showNeighbours( List<NeighbourWeather> listNeighbourWeather,Marker currentMarker);
    }
}















