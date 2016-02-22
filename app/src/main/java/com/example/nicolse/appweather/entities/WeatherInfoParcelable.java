package com.example.nicolse.appweather.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NicolásE on 02/02/2016.
 */
public class WeatherInfoParcelable implements Parcelable {


    private int icon_id;
    private String date;
    private String country;
    private String state;
    private ArrayList<ForecastParcelable> forecasts;

    public ArrayList<ForecastParcelable> getForecasts() {
        return forecasts;
    }

    public void setForecasts(ArrayList<ForecastParcelable> forecasts) {
        this.forecasts = forecasts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon_id);
        dest.writeString(date);
        dest.writeString(country);
        dest.writeString(state);
        dest.writeList(forecasts);
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public WeatherInfoParcelable createFromParcel(Parcel in) {
            return new WeatherInfoParcelable(in);
        }

        public WeatherInfoParcelable[] newArray(int size) {
            return new WeatherInfoParcelable[size];
        }
    };


    public WeatherInfoParcelable(Parcel in) {
        icon_id = in.readInt();
        date = in.readString();
        country = in.readString();
        state = in.readString();
        forecasts = in.readArrayList(getClass().getClassLoader());
    }

    public WeatherInfoParcelable() {
        super();
    }


}
