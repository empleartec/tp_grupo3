package com.example.nicolse.appweather.ObjectsFromJSON;

import java.util.List;

/**
 * Created by NicolásE on 15/02/2016.
 */
public class PlacesYahoo {
    private List<PlaceYahoo> place;
    private int total;
    private int count;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PlaceYahoo> getPlace() {
        return place;
    }

    public void setPlace(List<PlaceYahoo> place) {
        this.place = place;
    }
}
