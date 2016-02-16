package com.example.nicolse.appweather.ObjectsFromJSON;

/**
 * Created by NicolásE on 15/02/2016.
 */
public class PlaceYahoo {
    private int woeid;
    private String name;
    private String country;
    private String admin1;
    private String admin2;
    private PlaceUbicationYahoo centroid;

    public PlaceUbicationYahoo getCentroid() {
        return centroid;
    }

    public void setCentroid(PlaceUbicationYahoo centroid) {
        this.centroid = centroid;
    }

    public String getAdmin1() {

        return admin1;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public int getWoeid() {

        return woeid;
    }

    public void setWoeid(int woeid) {
        this.woeid = woeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
