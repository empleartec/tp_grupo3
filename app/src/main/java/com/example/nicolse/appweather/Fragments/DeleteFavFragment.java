package com.example.nicolse.appweather.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolse.appweather.FavouriteActivity;
import com.example.nicolse.appweather.ListAdapters.FavListAdapter;
import com.example.nicolse.appweather.MainMapActivity;
import com.example.nicolse.appweather.ForecastsActivity;
import com.example.nicolse.appweather.R;

/**
 * Created by NicolásE on 31/01/2016.
 */
public class DeleteFavFragment extends DialogFragment {



    private FavouriteActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dialog_fav, container, false);
        getDialog().setTitle("Delete Favourite Place?");
        TextView textView = (TextView)rootView.findViewById(R.id.desc_asc);
        textView.setText("Do you want to delete it?");
        getContext();
        Button noButton = (Button) rootView.findViewById(R.id.noButton);
        noButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }
        );


        Button yesButton = (Button) rootView.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //activity.doDeleteFav(viewEx);
                        activity.doDeleteFav();
                        Toast.makeText(getActivity(), "Place selected was deleted from \"favourites\"", Toast.LENGTH_LONG).show();
                        //Intent anIntent = new Intent(getContext(), FavouriteActivity.class);
                        //startActivity(anIntent);
                        dismiss();
                    }
                }

        );

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (this.activity == null) {
            this.activity = (FavouriteActivity) activity;

        }
    }

}