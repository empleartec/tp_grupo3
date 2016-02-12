package com.example.nicolse.appweather;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


public class FavouriteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarFavourite);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //System.out.print("myToolbar.getTitle()");
    }
}

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_favourite_compat_action_bar, menu);
        return true;
    }
*/

//clave api bruno: AIzaSyAVzUn-MJf5tfVRm7G_2HB5eVU6wMBoJZ4
//clave api nico: AIzaSyB27CjmPkuR-YWfYuffcEmK23EcvWAYWZo
