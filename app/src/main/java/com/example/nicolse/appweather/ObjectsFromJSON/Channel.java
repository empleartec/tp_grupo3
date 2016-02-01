package com.example.nicolse.appweather.ObjectsFromJSON;

/**
 * Created by NicolásE on 12/01/2016.
 */
public class Channel{
    Location location;
    Units units;
    Item item;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
