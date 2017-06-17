package com.woodamax.stm32kb;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

//TODO maybe use an ListAdapter to swipe from one question to the other...
public class QuestionScreen extends AppCompatActivity {

    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

    DatabaseHelper myDBH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        final Button submit = (Button) findViewById(R.id.question_submit_button);
        final Button finish = (Button) findViewById(R.id.question_finish_button);
        MainActivity.bwh.setCode(1);
        writeQuestionsInDb();
        MainActivity.bwh.setCode(1);
        writeAnswersInDb();
        MainActivity.bwh.setCode(1);
        writeQuestionAnswerInDb();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Q1"));
        tabLayout.addTab(tabLayout.newTab().setText("Q2"));
        tabLayout.addTab(tabLayout.newTab().setText("Q3"));
        tabLayout.addTab(tabLayout.newTab().setText("Q4"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ToolbarFragment toolbar = new ToolbarFragment();
        //BottomFragment bottom = new BottomFragment();

        ft.add(R.id.article_toolbar,toolbar,"Toolbar_fragment");
        //ft.add(R.id.question_text_container_bottom, bottom, "Bottom_Fragment");
        //ft.add(R.id.question_text_container, question, "Question_Fragment");
        ft.commit();
        // add back arrow to toolbar
        MainActivity.fh.setTop("Toolbar_fragment");
        MainActivity.fh.setCenter("Question_Fragment");
        MainActivity.fh.setBottom("Bottom_Fragment");

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new QuestionPageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        Log.e("QA","Tab 1");
                        submit.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        Log.e("QA","Tab 2");
                        submit.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        Log.e("QA","Tab 3");
                        submit.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        Log.e("QA","Tab 4");
                        submit.setVisibility(View.VISIBLE);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String messageText = buildMessage();
                                Log.e("QA","Clicked on Submit");
                                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                emailIntent.setType("message/rfc822");
                                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "maximilian.pessl@stud.fh-campuswien.ac.at", "erika.wood@stud.fh-campuswien.ac.at" });
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Registration for STM32KB");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, MainActivity.qh.getQuestion().get(0));
                                emailIntent.putExtra(Intent.EXTRA_TEXT, messageText);
                                v.getContext().startActivity(emailIntent);
                                submit.setVisibility(View.INVISIBLE);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        finish.setVisibility(View.VISIBLE);
                                        finish.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(v.getContext(),MainActivity.class);
                                                v.getContext().startActivity(intent);
                                            }
                                        });
                                    }
                                }, 5000);

                            }
                        });
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private String buildMessage() {
        StringBuilder newMessage = new StringBuilder();
        newMessage.append("\n"+"Resgistration answers:");
        newMessage.append("\n\nQuestion: "+MainActivity.qh.getQuestion().get(0));
        if(MainActivity.qh.isQ1a1()){
            Log.e("QA",MainActivity.qh.getAnswers().get(0));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(0));
        }
        if(MainActivity.qh.isQ1a2()){
            Log.e("QA",MainActivity.qh.getAnswers().get(4));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(4));
        }
        if(MainActivity.qh.isQ1a3()){
            Log.e("QA",MainActivity.qh.getAnswers().get(8));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(8));
        }
        if(MainActivity.qh.isQ1a4()){
            Log.e("QA",MainActivity.qh.getAnswers().get(12));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(12));
        }

        newMessage.append("\n\nQuestion: "+MainActivity.qh.getQuestion().get(1));

        if(MainActivity.qh.isQ2a1()){
            Log.e("QA",MainActivity.qh.getAnswers().get(1));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(1));
        }
        if(MainActivity.qh.isQ2a2()){
            Log.e("QA",MainActivity.qh.getAnswers().get(5));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(5));
        }
        if(MainActivity.qh.isQ2a3()){
            Log.e("QA",MainActivity.qh.getAnswers().get(9));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(9));
        }
        if(MainActivity.qh.isQ2a4()){
            Log.e("QA",MainActivity.qh.getAnswers().get(13));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(13));
        }

        newMessage.append("\n\nQuestion: "+MainActivity.qh.getQuestion().get(2));

        if(MainActivity.qh.isQ3a1()){
            Log.e("QA",MainActivity.qh.getAnswers().get(2));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(2));
        }
        if(MainActivity.qh.isQ3a2()){
            Log.e("QA",MainActivity.qh.getAnswers().get(6));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(6));
        }
        if(MainActivity.qh.isQ3a3()){
            Log.e("QA",MainActivity.qh.getAnswers().get(10));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(10));
        }
        if(MainActivity.qh.isQ3a4()){
            Log.e("QA",MainActivity.qh.getAnswers().get(14));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(14));
        }

        newMessage.append("\n\nQuestion: "+MainActivity.qh.getQuestion().get(3));

        if(MainActivity.qh.isQ2a1()){
            Log.e("QA",MainActivity.qh.getAnswers().get(3));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(3));
        }
        if(MainActivity.qh.isQ2a2()){
            Log.e("QA",MainActivity.qh.getAnswers().get(7));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(7));
        }
        if(MainActivity.qh.isQ2a3()){
            Log.e("QA",MainActivity.qh.getAnswers().get(11));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(11));
        }
        if(MainActivity.qh.isQ2a4()){
            Log.e("QA",MainActivity.qh.getAnswers().get(15));
            newMessage.append("\nAnswer: "+MainActivity.qh.getAnswers().get(15));
        }

        String message = newMessage.toString();
        return message;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.article_menue, menu);
        //menu.findItem(R.id.reading_edit).setVisible(true);
        return true;
    }

    /**
     * Used to write the questions to the DB
     */
    private void writeQuestionsInDb() {
        String type = "writeQuestionsInDb";
        MainActivity.bwh.setCount(4);
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
        MainActivity.bwh.setCount(4*4);
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

    private void writeQuestionAnswerInDb() {
        String type ="writeQuestionAnswerInDb";
        MainActivity.bwh.setCount(4);
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.doInBackground(type);
    }
}
