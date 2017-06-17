package com.woodamax.stm32kb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
//TODO calling a new Fragment for the Article Reading via the Article Selection Fragment
/**
 * This new Fragment should be called when one of the previewed articles is selected via the ArticleSelectionFragemnt
 */
public class ArticleScreen extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    static ArticleHelper helper = new ArticleHelper();
    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_screen);

        //When the activity is called the fragment is used as the view
        ToolbarFragment toolbar = new ToolbarFragment();
        ArticleSelectionFragment article = new ArticleSelectionFragment();
        BottomFragment bottom = new BottomFragment();

        ft.add(R.id.article_toolbar,toolbar,"Toolbar_fragment");
        ft.add(R.id.article_text_container_bottom, bottom, "Bottom_Fragment");
        ft.add(R.id.article_text_container, article, "Article_selection_Fragment");
        ft.commit();
        // add back arrow to toolbar
        MainActivity.fh.setTop("Toolbar_fragment");
        MainActivity.fh.setCenter("Article_selection_Fragment");
        MainActivity.fh.setBottom("Bottom_Fragment");
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
        //Used to find the Fragments
        Fragment cf = fm.findFragmentByTag("Article_reading_Fragment");
        Fragment cf2 = fm.findFragmentByTag("Article_selection_Fragment");
        if (item.getItemId() == R.id.reading_feedback) {
            Toast.makeText(getApplicationContext(), "FEEDBACK", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.reading_file) {
            if(MainActivity.fh.getCenter().equals("Article_reading_Fragment")){
                Log.e("AS","Change to SF");
                MainActivity.fh.setCenter("Article_reading_Fragment");
                android.support.v4.app.FragmentManager fm6 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft6 = fm6.beginTransaction();
                ReadersViewFragment readarticle = new ReadersViewFragment();
                ft6.addToBackStack(null);
                ft6.hide(cf);
                ft6.add(R.id.article_text_container,readarticle,"Article_reading_Fragment");
                ft6.commit();
            }Log.e("AS",MainActivity.fh.getCenter());
        }
        if (item.getItemId() == R.id.reading_edit) {
            if(MainActivity.fh.getCenter().equals("Article_reading_Fragment")){
                Log.e("AS","Change to EF");
                MainActivity.fh.setCenter("Article_reading_Fragment");
                android.support.v4.app.FragmentManager fm4 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft4 = fm4.beginTransaction();
                ArticleEditFragment editarticle = new ArticleEditFragment();
                ft4.addToBackStack(null);
                ft4.hide(cf);
                ft4.add(R.id.article_text_container,editarticle,"Article_reading_Fragment");
                ft4.commit();
            }Log.e("AS",MainActivity.fh.getCenter());
        }
        if (item.getItemId() == R.id.reading_create) {
            if(MainActivity.fh.getCenter().equals("Article_reading_Fragment")){
                Log.e("AS","Change to CF");
                MainActivity.fh.setCenter("Article_reading_Fragment");
                android.support.v4.app.FragmentManager fm5 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft5 = fm5.beginTransaction();
                ArticleCreateFragment createarticle = new ArticleCreateFragment();
                ft5.addToBackStack(null);
                ft5.hide(cf);
                ft5.add(R.id.article_text_container,createarticle,"Article_reading_Fragment");
                ft5.commit();
            }Log.e("AS",MainActivity.fh.getCenter());
        }
        if (item.getItemId() == android.R.id.home) {
            //This manages the step back to change the Fragments and be able to go to the Main Activity
            if(cf != null){
                if(cf2.isVisible()){
                    finish();
                }
                //Important to push the user experience
                MainActivity.fh.setCenter("Article_selection_Fragment");
                MainActivity.fh.setControllerType(0);
                android.support.v4.app.FragmentManager fm3 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft3 = fm3.beginTransaction();
                ArticleSelectionFragment article = new ArticleSelectionFragment();
                ft3.hide(cf);
                ft3.replace(R.id.article_text_container, article, "Article_selection_Fragment");
                ft3.detach(fm.findFragmentByTag("Article_reading_Fragment"));
                ft3.commit();
            }else{
                finish(); // close this activity and return to preview activity (if there is any)
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
