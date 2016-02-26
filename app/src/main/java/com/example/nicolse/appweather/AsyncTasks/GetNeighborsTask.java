package com.example.nicolse.appweather.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.nicolse.appweather.ObjectsFromJSON.PlaceYahoo;
import com.example.nicolse.appweather.ObjectsFromJSON.PlacesYahoo;
import com.example.nicolse.appweather.ObjectsFromJSON.ResultPlacesYahoo;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by NicolásE on 26/02/2016.
 */
public class GetNeighborsTask extends AsyncTask<String, String, String> {


    private Context context;

    public GetNeighborsTask(Context context){
        this.context=context;
    }



    @Override
    protected String doInBackground(String... woeidString) {
        String woeid = woeidString[0];
        String endpoint = "http://where.yahooapis.com/v1/place/"+woeid+"/parent?select=long&format=json&appid=dj0yJmk9UzhtZG5VVHpBSnJxJmQ9WVdrOWJHdERZWEE1TnpBbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD02NQ--";
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

        System.out.println(json);
        if (json != null) {



        }

    }

}
