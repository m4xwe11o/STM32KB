package com.woodamax.stm32kb;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import static com.woodamax.stm32kb.DatabaseHelper.DATABASE_NAME;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDBH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        LoginClickListener clickListener = new LoginClickListener();
        RegisterClickListener clickListener1 = new RegisterClickListener();
        StartReadingClickListener clickListener2 = new StartReadingClickListener();
        Button login = (Button) findViewById(R.id.my_login_button);
        Button register = (Button) findViewById(R.id.my_sign_up_button);
        Button reading = (Button) findViewById(R.id.my_start_reading_button);
        login.setOnClickListener(clickListener);
        register.setOnClickListener(clickListener1);
        reading.setOnClickListener(clickListener2);

        checkForNewArticles();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_info){
            //Better deleting the Database at the beginning... Than working on one of the creepy methods....
            deleteDatabase(DATABASE_NAME);
            //Toast.makeText(MainActivity.this,"Fetching articles description",Toast.LENGTH_SHORT).show();
            fetchArticlesDescription();
            //Toast.makeText(MainActivity.this,"Fetching articles text",Toast.LENGTH_SHORT).show();
            fetchArticleText();
        }else{
            Toast.makeText(MainActivity.this,"Debuging database",Toast.LENGTH_SHORT).show();
            debugDatabse();
        }
        return true;
    }

    /**
     * Method to display the local DB
     */
    private void debugDatabse() {
        myDBH = new DatabaseHelper(this);
        Cursor res = myDBH.getArticleDescription();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Title :"+ res.getString(1)+"\n");
            buffer.append("Description :"+ res.getString(2)+"\n");
            buffer.append("Text :"+ res.getString(4)+"\n");
            //Toast.makeText(this,"DEBUG Message",Toast.LENGTH_SHORT).show();
        }

        // Show all data
        showMessage("Local Database",buffer.toString());
    }

    /**
     * Build the alert screen to show the local DB
     * @param title alert dialog title
     * @param Message DB query result from the buffer
     */
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    /**
     * This method calls the background worker to get the articles description and title
     */
    private void fetchArticlesDescription() {
        String type = "FetchArticleDescription";
        String article = "article";
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.execute(type,article);
    }

    /**
     * This method calls the background worker to get the articlestext
     */
    private void fetchArticleText() {
        String type = "FetchArticleText";
        String articletext = "articletext";
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.execute(type,articletext);
    }

    /**
     * This method calls the background worker to get the number of articles
     */
    private void checkForNewArticles() {
        String type = "FetchNewArticle";
        String article = "article";
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.execute(type,article);
    }
}
