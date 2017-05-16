package com.woodamax.stm32kb;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import static com.woodamax.stm32kb.DatabaseHelper.DATABASE_NAME;
//TODO Add answers and linked table questions/answers
public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDBH;
    ProgressDialog progressDoalog;
    static FragmentHelper fh = new FragmentHelper();
    static BackgroundWorkerHelper bwh = new BackgroundWorkerHelper();

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
            /**
             * This block is used to display status information while all articles are loaded
             */
            progressDoalog = new ProgressDialog(MainActivity.this);
            progressDoalog.setMax(100);
            progressDoalog.setTitle("Updating database");
            progressDoalog.setMessage("Please wait... it's loading...");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDoalog.show();
            new Thread(new Runnable() {
                Handler handle = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        progressDoalog.incrementProgressBy(1);
                    }
                };
                @Override
                public void run() {
                    try {
                        while (progressDoalog.getProgress() <= progressDoalog
                                .getMax()) {
                            Thread.sleep(150);
                            handle.sendMessage(handle.obtainMessage());
                            if (progressDoalog.getProgress() == progressDoalog
                                    .getMax()) {
                                progressDoalog.dismiss();
                            }
                        }
                        /**Better deleting the Database at the beginning... Than working on one of the creepy methods....
                         * Than the articles are fetched, and the tables inside the database are filled
                         */
                        deleteDatabase(DATABASE_NAME);
                        fetchArticlesDescription();
                        fetchArticleText();
                        writeQuestionsInDb();
                        writeAnswersInDb();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }else if(item.getItemId() == R.id.menu_question){
            Toast.makeText(MainActivity.this,"Debuging question database",Toast.LENGTH_SHORT).show();
            debugQuestionDatabse();
        }else if(item.getItemId() == R.id.menu_answer){
            Toast.makeText(MainActivity.this,"Debuging answer database",Toast.LENGTH_SHORT).show();
            debugAnswerDatabse();
        }else{
            Toast.makeText(MainActivity.this,"Debuging article database",Toast.LENGTH_SHORT).show();
            debugDatabse();
        }
        return true;
    }

    /**
     * Used due to development
     */
    private void debugQuestionDatabse() {
        myDBH = new DatabaseHelper(this);
        Cursor res = myDBH.getQuestions();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Question :"+ res.getString(1)+"\n");
        }

        // Show all data
        showMessage("Question table",buffer.toString());
    }

    private void debugAnswerDatabse() {
        myDBH = new DatabaseHelper(this);
        Cursor res = myDBH.getAnswers();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Answer :"+ res.getString(1)+"\n");
        }

        // Show all data
        showMessage("Answer table",buffer.toString());
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
        showMessage("Article table",buffer.toString());
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

    /**
     * Used to write the questions to the DB
     */
    private void writeQuestionsInDb() {
        String type = "writeQuestionsInDb";
        bwh.setCount(4);
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.doInBackground(type,
                getResources().getString(R.string.Question1),
                getResources().getString(R.string.Question2),
                getResources().getString(R.string.Question3),
                getResources().getString(R.string.Question4));
    }

    /**
     * If it works... it isn't stupid...
     */
    private void writeAnswersInDb(){
        String type = "writeAnswersInDb";
        bwh.setCount(4*4);
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.doInBackground(type,
                getResources().getString(R.string.Answer1Q1),
                getResources().getString(R.string.Answer1Q2),
                getResources().getString(R.string.Answer1Q3),
                getResources().getString(R.string.Answer1Q4),
                getResources().getString(R.string.Answer2Q1),
                getResources().getString(R.string.Answer2Q2),
                getResources().getString(R.string.Answer2Q3),
                getResources().getString(R.string.Answer2Q4),
                getResources().getString(R.string.Answer3Q1),
                getResources().getString(R.string.Answer3Q2),
                getResources().getString(R.string.Answer3Q3),
                getResources().getString(R.string.Answer3Q4),
                getResources().getString(R.string.Answer4Q1),
                getResources().getString(R.string.Answer4Q2),
                getResources().getString(R.string.Answer4Q3),
                getResources().getString(R.string.Answer4Q4));
    }
}
