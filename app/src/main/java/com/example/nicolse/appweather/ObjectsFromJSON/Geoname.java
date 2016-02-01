package com.example.nicolse.appweather.ObjectsFromJSON;

/**
 * Created by NicolásE on 29/01/2016.
 */
public class Geoname {
    public void setName(String name) {
        this.name = name;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setAdminName1(String adminName1) {
        this.adminName1 = adminName1;
    }

    private String adminCode1;

    public String getLng() {
        return lng;
    }

    public String getAdminCode1() {
        return adminCode1;
    }

    public int getGeonameID() {
        return geonameID;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getToponymName() {
        return toponymName;
    }

    public String getFcl() {
        return fcl;
    }

    public int getPopulation() {
        return population;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public String getLat() {
        return lat;
    }

    public String getFcode() {
        return fcode;
    }

    public String getFcodeName() {
        return fcodeName;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getFclName() {
        return fclName;
    }

    private String lng;
    private int geonameID;
    private String toponymName;
    private String countryId;
    private String fcl;
    private int population;
    private String countryCode;
    private String name;
    private String fclName;
    private String countryName;
    private String fcodeName;
    private String adminName1;
    private String lat;
    private String fcode;


}
