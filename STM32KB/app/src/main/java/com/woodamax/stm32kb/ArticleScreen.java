package com.woodamax.stm32kb;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//TODO calling a new Fragment for the Article Reading via the Article Selection Fragment
/**
 * This new Fragment should be called when one of the previewed articles is selected via the ArticleSelectionFragemnt
 */
public class ArticleScreen extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    static ArticleHelper helper = new ArticleHelper();
    DatabaseHelper myDBH;
    Context context;
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
        final Button submitButton = (Button) this.findViewById(R.id.my_reading_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment cf = fm.findFragmentByTag("Article_reading_Fragment");
                Log.d("AS", "Clicked on submit");
                View parent = v.getRootView();
                if(MainActivity.fh.getCenter()== "Article_edit_Fragment"){
                    EditText title = (EditText) parent.findViewById(R.id.article_readers_edit_title);
                    EditText description = (EditText) parent.findViewById(R.id.article_readers_edit_description);
                    EditText articletext = (EditText) parent.findViewById(R.id.article_readers_edit_text);
                    if(updateArtilceInDb(title, description, articletext)){
                        Log.d("EA","Updated article");
                    }else{
                        Log.e("EA","ERROR updated article");
                    }
                }else{
                    EditText title = (EditText) parent.findViewById(R.id.article_readers_create_title);
                    EditText description = (EditText) parent.findViewById(R.id.article_readers_create_description);
                    EditText articletext = (EditText) parent.findViewById(R.id.article_readers_create_text);
                    if(writeArtilceToDb(title, description, articletext)){

                        int SDK_INT = android.os.Build.VERSION.SDK_INT;
                        if (SDK_INT > 8)
                        {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                    .permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            //your codes here
                            String type = "NewArticle";
                            BackgroundWorker backgroundWorker = new BackgroundWorker(parent.getContext());
                            backgroundWorker.onPreExecute();
                            backgroundWorker.doInBackground(type, title.getText().toString(), description.getText().toString(), articletext.getText().toString());
                            Log.d("EA","Aricle written to DB");

                        }

                    }else{
                        Log.e("EA","ERROR writing article to DB");
                    }
                }
                Log.d("AS",MainActivity.fh.getCenter());
                Log.e("AS","Change to RF");
                MainActivity.fh.setCenter("Article_reading_Fragment");
                android.support.v4.app.FragmentManager fm7 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft7 = fm7.beginTransaction();
                ReadersViewFragment readarticle = new ReadersViewFragment();
                ft7.addToBackStack(null);
                ft7.hide(cf);
                ft7.add(R.id.article_text_container,readarticle,"Article_reading_Fragment");
                ft7.commit();
            }
        });
        if(submitButton == null){
            Log.e("AS", "Error");
        }
        if (item.getItemId() == R.id.reading_feedback) {
            Toast.makeText(getApplicationContext(), "FEEDBACK", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.reading_file) {
            if(MainActivity.fh.getCenter().equals("Article_reading_Fragment")|| MainActivity.fh.getCenter().equals("Article_create_Fragment") || MainActivity.fh.getCenter().equals("Article_edit_Fragment")){
                Log.d("AS",MainActivity.fh.getCenter());
                Log.e("AS","Change to SF");
                MainActivity.fh.setCenter("Article_reading_Fragment");
                android.support.v4.app.FragmentManager fm6 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft6 = fm6.beginTransaction();
                ReadersViewFragment readarticle = new ReadersViewFragment();
                ft6.addToBackStack(null);
                ft6.hide(cf);
                ft6.add(R.id.article_text_container,readarticle,"Article_reading_Fragment");
                ft6.commit();
                submitButton.setTextColor(Color.GRAY);
            }Log.e("AS",MainActivity.fh.getCenter());
        }
        if (item.getItemId() == R.id.reading_edit) {
            if(MainActivity.fh.getCenter().equals("Article_reading_Fragment")){
                Log.d("AS",MainActivity.fh.getCenter());
                Log.e("AS","Change to EF");
                MainActivity.fh.setCenter("Article_reading_Fragment");
                android.support.v4.app.FragmentManager fm4 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft4 = fm4.beginTransaction();
                ArticleEditFragment editarticle = new ArticleEditFragment();
                ft4.addToBackStack(null);
                ft4.hide(cf);
                ft4.add(R.id.article_text_container,editarticle,"Article_reading_Fragment");
                ft4.commit();
                submitButton.setVisibility(View.VISIBLE);
                submitButton.setTextColor(Color.BLACK);
            }Log.e("AS",MainActivity.fh.getCenter());
        }
        if (item.getItemId() == R.id.reading_create) {
            if(MainActivity.fh.getCenter().equals("Article_reading_Fragment")){
                Log.d("AS",MainActivity.fh.getCenter());
                Log.e("AS","Change to CF");
                MainActivity.fh.setCenter("Article_reading_Fragment");
                android.support.v4.app.FragmentManager fm5 = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft5 = fm5.beginTransaction();
                ArticleCreateFragment createarticle = new ArticleCreateFragment();
                ft5.addToBackStack(null);
                ft5.hide(cf);
                ft5.add(R.id.article_text_container,createarticle,"Article_reading_Fragment");
                ft5.commit();
                submitButton.setVisibility(View.VISIBLE);
                submitButton.setTextColor(Color.BLACK);
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

    private boolean writeArtilceToDb(EditText title, EditText description, EditText articletext) {
        myDBH = new DatabaseHelper(this);
        if(myDBH.insertArticleData(title.getText().toString(),description.getText().toString()," ",articletext.getText().toString())){
            return true;
        }
        return false;
    }

    private boolean updateArtilceInDb(EditText title, EditText description, EditText articletext) {
        myDBH = new DatabaseHelper(this);
        return false;
    }
}
