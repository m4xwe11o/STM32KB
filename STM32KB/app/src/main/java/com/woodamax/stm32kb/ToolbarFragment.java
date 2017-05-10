package com.woodamax.stm32kb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by maxim on 05.04.2017.
 */

public class ToolbarFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "message";
    String messageText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        messageText = intent.getStringExtra(EXTRA_MESSAGE);
        View view=inflater.inflate(R.layout.toolbar_fragment, container, false);
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
        //Toast.makeText(view.getContext(), messageText, Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(getContext(), "On Create", Toast.LENGTH_SHORT).show();
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        Toast.makeText(getContext(), "Fragment On Create Options Menu", Toast.LENGTH_SHORT).show();
        if(!(MainActivity.fh.isAuthor())){
            menu.findItem(R.id.reading_edit).setVisible(false);
            Toast.makeText(getContext(), "Is Not Author", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), MainActivity.fh.getCenter(), Toast.LENGTH_SHORT).show();
            if(MainActivity.fh.getCenter().equals("Question_Fragment")){
                Toast.makeText(getContext(), "Fragment QuestionFragment active", Toast.LENGTH_SHORT).show();
                menu.findItem(R.id.reading_file).setVisible(false);
                menu.findItem(R.id.reading_edit).setVisible(false);
                menu.findItem(R.id.reading_edit).setVisible(false);
                menu.findItem(R.id.reading_create).setVisible(false);
                menu.findItem(R.id.reading_feedback).setVisible(false);
                return;
            }
        }else{
            menu.findItem(R.id.reading_edit).setVisible(true);
            menu.findItem(R.id.reading_create).setVisible(true);
            menu.findItem(R.id.reading_feedback).setVisible(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
