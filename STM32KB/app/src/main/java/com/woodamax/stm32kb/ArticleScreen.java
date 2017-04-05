package com.woodamax.stm32kb;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ArticleScreen extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_screen);

        //When the activity is called the fragment is used as the view
        ToolbarFragment toolbar = new ToolbarFragment();
        ArticleFragment article = new ArticleFragment();
        BottomFragment bottom = new BottomFragment();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.article_toolbar,toolbar,"Toolbar fragment");
        ft.add(R.id.article_text_container, article, "Article selection Fragment");
        ft.add(R.id.article_text_container_bottom, bottom, "Bootm Text");
        ft.commit();
        // add back arrow to toolbar

    }
    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.article_menue, menu);
        //menu.findItem(R.id.reading_edit).setVisible(true);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.reading_feedback){
            Toast.makeText(getApplicationContext(),"FEEDBACK",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.reading_file){
            Toast.makeText(getApplicationContext(),"FILE",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.reading_edit){
            Toast.makeText(getApplicationContext(),"EDIT",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()== R.id.reading_create){
            Toast.makeText(getApplicationContext(),"CREATE",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
