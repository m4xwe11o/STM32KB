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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by maxim on 02.04.2017.
 */

public class ArticleSelectionFragment extends Fragment {

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
        // add back arrow to toolbar
        if (activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Used to rewrite the text for each categorie
        final TextView none = (TextView) view.findViewById(R.id.article_text);
        //used to fill the spinner
        //this is one method to do this
        //String [] values = getResources().getStringArray(R.array.micro_controllers);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //This uses an different design for the spinner
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.micro_controllers, R.layout.my_spinner_item);
        final Spinner microControllerSpinner = (Spinner) view.findViewById(R.id.article_micro_controller_spinner);
        microControllerSpinner.setAdapter(adapter);
        //Click Listener for the items inside the spinner
        int selectionCurrent = microControllerSpinner.getSelectedItemPosition();
        microControllerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Select Controller")){
                    //Keeps crashing on 4.2.2 with getContext()
                    Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                    none.setText("");
                }
                if(selectedItem.equals("STM32F1")){
                    //Keeps crashing on 4.2.2 with getContext()
                    Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                    none.setText(getString(R.string.controller_f1));
                }
                if(selectedItem.equals("STM32F3")){
                    Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                    none.setText(getString(R.string.controller_f3));
                }
                if(selectedItem.equals("STM32F4")){
                    Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                    none.setText(getString(R.string.controller_f4));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }




}
