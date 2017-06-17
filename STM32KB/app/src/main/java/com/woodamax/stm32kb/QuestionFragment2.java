package com.woodamax.stm32kb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by maxim on 09.05.2017.
 */

public class QuestionFragment2 extends Fragment {
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
                    Log.e("QF Q2","Checked Answer 1");
                    MainActivity.qh.setQ2a1(true);
                }else{
                    Log.e("QF Q2","Unchecked Answer 1");
                    MainActivity.qh.setQ2a1(false);
                }
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer2.isChecked()){
                    Log.e("QF Q2","Checked Answer 2");
                    MainActivity.qh.setQ2a2(true);
                }else{
                    Log.e("QF Q2","Unchecked Answer 2");
                    MainActivity.qh.setQ2a2(false);
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer3.isChecked()){
                    Log.e("QF Q2","Checked Answer 3");
                    MainActivity.qh.setQ2a3(true);
                }else{
                    Log.e("QF Q2","Unchecked Answer 3");
                    MainActivity.qh.setQ2a3(false);
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer4.isChecked()){
                    Log.e("QF Q4","Checked Answer 4");
                    MainActivity.qh.setQ4a4(true);
                }else{
                    Log.e("QF Q4","Unchecked Answer 4");
                    MainActivity.qh.setQ4a4(false);
                }
            }
        });

        buildQuestion(questiontitle, questiontext, answer1,answer2, answer3, answer4);

        return view;
    }

    private void buildQuestion(TextView questiontitle, TextView questiontext, CheckBox answer1, CheckBox answer2, CheckBox answer3, CheckBox answer4) {
        myDBH = new DatabaseHelper(getActivity());
        Cursor questions = myDBH.getQuestions();
        questiontitle.setText("Question 2");
        questiontext.setText(MainActivity.qh.questions.get(1));
        answer1.setText(MainActivity.qh.answers.get(1));
        answer2.setText(MainActivity.qh.answers.get(5));
        answer3.setText(MainActivity.qh.answers.get(9));
        answer4.setText(MainActivity.qh.answers.get(13));
    }
}
