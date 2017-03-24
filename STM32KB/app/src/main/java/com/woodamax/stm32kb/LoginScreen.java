package com.woodamax.stm32kb;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_login_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        //Rewrite the title
        toolbar.setTitle("");
        //chenge the background color
        toolbar.setBackgroundColor(Color.parseColor("#F44336"));
        setSupportActionBar(toolbar);
        
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        LoginBackClickListener clickListener = new LoginBackClickListener();
        Button backtostart = (Button) findViewById(R.id.my_login_back_button);
        backtostart.setOnClickListener(clickListener);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_menue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //To change the color in the action bar we need to define the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_login_toolbar);
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        if (item.getItemId() == R.id.login_menu_list){
            Toast.makeText(LoginScreen.this,"Clicked on info",Toast.LENGTH_SHORT).show();
        }

        //Just something for customazation
        int id = item.getItemId();
        switch (id){
            case R.id.menu_blue:
                if(item.isChecked()){item.setChecked(false);}else{item.setChecked(true);}
                toolbar.setBackgroundColor(Color.parseColor("#2196F3"));
                return true;
            case R.id.menu_red:
                if(item.isChecked()){item.setChecked(false);}else{item.setChecked(true);}
                toolbar.setBackgroundColor(Color.parseColor("#F44336"));
                return true;
            case R.id.menu_green:
                if(item.isChecked()){item.setChecked(false);}else{item.setChecked(true);}
                toolbar.setBackgroundColor(Color.parseColor("#4CAF50"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
