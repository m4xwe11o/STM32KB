package com.woodamax.stm32kb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by maxim on 05.04.2017.
 */

public class BottomFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "message";
    String messageText;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.article_bottom_fragment, container, false);
        Intent intent = getActivity().getIntent();
        messageText = intent.getStringExtra(EXTRA_MESSAGE);
        TextView hello = (TextView) view.findViewById(R.id.article_text_bottom);
        if(messageText == null){
            hello.setText("Hello Reader!");
        }else{
            hello.setText("Hello " + messageText.toString() + " !");
        }
        return view;
    }
}
