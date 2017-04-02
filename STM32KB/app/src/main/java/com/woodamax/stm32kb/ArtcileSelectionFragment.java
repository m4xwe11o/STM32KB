package com.woodamax.stm32kb;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by maxim on 02.04.2017.
 */

public class ArtcileSelectionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.article_selection, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.my_arcticle_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        //Rewrite the title
        toolbar.setTitle("");
        //change the background color
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //used to fill the spinner
        String [] values = getResources().getStringArray(R.array.micro_controllers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        Spinner microControllerSpinner = (Spinner) view.findViewById(R.id.article_micro_controller_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        microControllerSpinner.setAdapter(adapter);

        return view;
    }

}
