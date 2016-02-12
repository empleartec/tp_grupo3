package com.example.nicolse.appweather.Fragments;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nicolse.appweather.MainMapActivity;
import com.example.nicolse.appweather.R;

/**
 * Created by NicolásE on 31/01/2016.
 */
public class DialogFavFragment extends DialogFragment {


    private MainMapActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_fav, container, false);
        getDialog().setTitle("Adding Favourite Place");
        getContext();
        Button noButton = (Button) rootView.findViewById(R.id.noButton);
        noButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );


        Button yesButton = (Button) rootView.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Place selected was added to \"favourites\"", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }
        );


        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (this.activity==null) {
            this.activity =(MainMapActivity) activity;
        }
    }



}
