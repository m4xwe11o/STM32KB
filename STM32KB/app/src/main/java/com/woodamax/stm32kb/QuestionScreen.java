package com.woodamax.stm32kb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
//TODO maybe use an ListAdapter to swipe from one question to the other...
public class QuestionScreen extends AppCompatActivity {

    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        ToolbarFragment toolbar = new ToolbarFragment();
        QuestionFragment question = new QuestionFragment();
        BottomFragment bottom = new BottomFragment();

        ft.add(R.id.article_toolbar,toolbar,"Toolbar_fragment");
        ft.add(R.id.question_text_container_bottom, bottom, "Bottom_Fragment");
        ft.add(R.id.question_text_container, question, "Question_Fragment");
        ft.commit();
        // add back arrow to toolbar
        MainActivity.fh.setTop("Toolbar_fragment");
        MainActivity.fh.setCenter("Question_Fragment");
        MainActivity.fh.setBottom("Bottom_Fragment");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.article_menue, menu);
        //menu.findItem(R.id.reading_edit).setVisible(true);
        return true;
    }
}
