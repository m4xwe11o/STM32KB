package com.woodamax.stm32kb;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by maxim on 18.06.2017.
 */

public class ArticleCreateFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.article_create_article_fragments, container, false);
        MainActivity.fh.setCenter("Article_create_Fragment");
        EditText title = (EditText) view.findViewById(R.id.article_readers_create_title);
        EditText description = (EditText) view.findViewById(R.id.article_readers_create_description);
        EditText articletext = (EditText) view.findViewById(R.id.article_readers_create_text);

        title.setText(MainActivity.ah.getTitle().substring(0,7)+" - ");
        return view;
    }
}
