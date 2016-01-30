package com.example.nicolse.appweather.ObjectsFromJSON;

import java.util.List;

/**
 * Created by NicolásE on 29/01/2016.
 */
public class ResultGeonames {
    private String totalResultsCount;
   // private Geoname[] geonames=new Geoname[400];

    private List<Geoname> geonames;
    public String getTotalResultsCount() {
        return totalResultsCount;
    }


    /*public Geoname[] getGeonames() {
        return geonames;
    }*/
      public List<Geoname> getGeonames() {
        return geonames;
    }


}
