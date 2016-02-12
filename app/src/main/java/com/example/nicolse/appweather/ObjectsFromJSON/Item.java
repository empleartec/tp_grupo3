package com.example.nicolse.appweather.ObjectsFromJSON;

import com.example.nicolse.appweather.entities.ForecastParcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NicolásE on 12/01/2016.
 */
public class Item {
    String title;
    Condition condition;
    ArrayList<ForecastParcelable> forecast;

    public ArrayList<ForecastParcelable> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<ForecastParcelable> forecast) {
        this.forecast = forecast;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @SerializedName("lat")
    String latitude;

    @SerializedName("long")
    String longitude;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
