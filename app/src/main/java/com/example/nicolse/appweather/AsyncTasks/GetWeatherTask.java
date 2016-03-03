package com.example.nicolse.appweather.AsyncTasks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolse.appweather.ObjectsFromJSON.ResultWeatherInfo;
import com.example.nicolse.appweather.entities.WeatherInfoParcelable;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by NicolásE on 01/02/2016.
 */
public class GetWeatherTask extends AsyncTask<String, String, String> {


    private WheaterTask context;

    private WeatherInfoParcelable weatherInfo;

  /*  public GetWeatherTask(WeatherInfoParcelable weatherInfo){
        super();
        this.weatherInfo = weatherInfo ;
    }*/


    public GetWeatherTask(Context context) {
        super();

        if(context instanceof WheaterTask){
            this.context = (WheaterTask) context;
        }


    }


    public interface WheaterTask{
         void updateWeatherInMap(ResultWeatherInfo resultWeatherInfo);
    }

    @Override
    protected String doInBackground(String... locations) {

        String location = locations[0];
        String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")and u='c'", location);
        //String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
        //String YQL = String.format( "select * from weather.forecast where woeid =%s and u='c'", location);
        String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
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





    @Override
    protected void onPostExecute(String json) {
        Gson gson = new Gson();
        ResultWeatherInfo resultWeatherInfo = gson.fromJson(json, ResultWeatherInfo.class);
//        System.out.println("<TITULO>::::" + resultWeatherInfo.getQuery().getResults().getChannel().getItem().getTitle());
        context.updateWeatherInMap(resultWeatherInfo);//aca muestro el mapa seteando, se debe fijar si existe el clima para cierta ciudad
//        System.out.println("<TITULO>::::" + resultWeatherInfo.getQuery().getResults().getChannel().getItem().getTitle());

    }
}










