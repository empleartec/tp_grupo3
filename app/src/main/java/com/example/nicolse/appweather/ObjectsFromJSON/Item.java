package com.example.nicolse.appweather.ObjectsFromJSON;

/**
 * Created by NicolásE on 12/01/2016.
 */
public class Item {
    String title;
    Condition condition;

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
