package com.woodamax.stm32kb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by maxim on 09.05.2017.
 */

public class QuestionFragment1 extends Fragment {
    DatabaseHelper myDBH;

    TextView questiontitle;
    TextView questiontext;
    CheckBox answer1;
    CheckBox answer2;
    CheckBox answer3;
    CheckBox answer4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_fragment, container, false);

        questiontitle = (TextView) view.findViewById(R.id.question_title);
        questiontext = (TextView) view.findViewById(R.id.question_text);
        answer1 = (CheckBox) view.findViewById(R.id.question_answer1);
        answer2 = (CheckBox) view.findViewById(R.id.question_answer2);
        answer3 = (CheckBox) view.findViewById(R.id.question_answer3);
        answer4 = (CheckBox) view.findViewById(R.id.question_answer4);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.isChecked()){
                    Log.e("QF Q1","Checked Answer 1");
                    MainActivity.qh.setQ1a1(true);
                }else{
                    Log.e("QF Q1","Unchecked Answer 1");
                    MainActivity.qh.setQ1a1(false);
                }
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer2.isChecked()){
                    Log.e("QF Q1","Checked Answer 2");
                    MainActivity.qh.setQ1a2(true);
                }else{
                    Log.e("QF Q1","Unchecked Answer 2");
                    MainActivity.qh.setQ1a2(false);
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer3.isChecked()){
                    Log.e("QF Q1","Checked Answer 3");
                    MainActivity.qh.setQ1a3(true);
                }else{
                    Log.e("QF Q1","Unchecked Answer 3");
                    MainActivity.qh.setQ1a3(false);
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer4.isChecked()){
                    Log.e("QF Q1","Checked Answer 4");
                    MainActivity.qh.setQ1a4(true);
                }else{
                    Log.e("QF Q1","Unchecked Answer 4");
                    MainActivity.qh.setQ1a4(false);
                }
            }
        });

        buildQuestion(questiontitle, questiontext, answer1,answer2, answer3, answer4);

        return view;
    }

    private void buildQuestion(TextView questiontitle, TextView questiontext, CheckBox answer1, CheckBox answer2, CheckBox answer3, CheckBox answer4) {
        questiontitle.setText("Question 1");
        questiontext.setText(MainActivity.qh.questions.get(0));
        answer1.setText(MainActivity.qh.answers.get(0));
        answer2.setText(MainActivity.qh.answers.get(4));
        answer3.setText(MainActivity.qh.answers.get(8));
        answer4.setText(MainActivity.qh.answers.get(12));
    }
}
