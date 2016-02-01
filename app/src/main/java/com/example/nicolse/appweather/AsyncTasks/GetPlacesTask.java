package com.example.nicolse.appweather.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.nicolse.appweather.ObjectsFromJSON.Geoname;
import com.example.nicolse.appweather.ObjectsFromJSON.ResultGeonames;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by NicolásE on 29/01/2016.
 */
public class GetPlacesTask extends AsyncTask<String, String, String> {


    private Context context;
    private GetPlacesCallback callback;
    private final String USERNAME="username=nikoLedesma";
    private final String MAXROWS="maxRows=100";

    public GetPlacesTask(Context context, GetPlacesCallback callback) {

        this.context = context;
        this.callback = callback;
    }


    public interface GetPlacesCallback {
        public void mostrarToast(String algo);

        public void changeFragment();
    }


    @Override
    protected String doInBackground(String... placeString) {
        //  String place = placeString[0];
        String endpoint = "http://api.geonames.org/searchJSON?name=argentina&maxRows=5&username=nikoLedesma";
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
        ////intentamos hacer una clase a partir del json
        Gson gson = new Gson();

        System.out.println(json);

        if (json != null) {

            ResultGeonames resultGeonames = gson.fromJson(json, ResultGeonames.class);
            int totalResultCounts = Integer.parseInt(resultGeonames.getTotalResultsCount());
            List<Geoname> listGeonames= resultGeonames.getGeonames();//TODO:la lista para pasarlo al list adapter
           // callback.mostrarToast(resultGeonames.getTotalResultsCount());
            callback.mostrarToast(String.valueOf(listGeonames.size()));
            // PlacesListAdapter adapter=new PlacesListAdapter( this.context,listGeonames);
           // listView.setAdapter(adapter);//esta listview viene del activity

            callback.changeFragment();

          /*  Fragment newFragment = new ListPlacesFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.mapFragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
*/

        }

    }


}
