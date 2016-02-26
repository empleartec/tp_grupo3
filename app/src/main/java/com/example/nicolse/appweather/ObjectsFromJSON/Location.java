package com.example.nicolse.appweather.ObjectsFromJSON;

/**
 * Created by NicolásE on 12/01/2016.
 */
public class Location {

        String city;
        String country;
        String region;

    private transient  int woeid;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
