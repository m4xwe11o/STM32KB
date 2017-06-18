package com.woodamax.stm32kb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by maxim on 18.06.2017.
 */

public class ArticleEditFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.article_edit_article_fragment, container, false);
        MainActivity.fh.setCenter("Article_edit_Fragment");
        EditText title = (EditText) view.findViewById(R.id.article_readers_edit_title);
        EditText description = (EditText) view.findViewById(R.id.article_readers_edit_description);
        EditText articletext = (EditText) view.findViewById(R.id.article_readers_edit_text);

        title.setText(MainActivity.ah.getTitle());
        description.setText(MainActivity.ah.getDescription());
        articletext.setText(MainActivity.ah.getArticletext());
        return view;
    }
}
