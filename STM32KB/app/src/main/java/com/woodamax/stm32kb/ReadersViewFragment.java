package com.woodamax.stm32kb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by maxim on 30.04.2017.
 */

public class ReadersViewFragment extends Fragment {
    DatabaseHelper myDBH;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.article_readers_view_fragment,container,false);
        buildReadersView(view);
        return view;
    }

    /**
     * The helper helps to find the selected article
     * The article object can be used to edit an article
     * @param view the view which has to be build
     */
    private void buildReadersView(View view) {
        myDBH = new DatabaseHelper(getActivity());
        Cursor res = myDBH.getArticleDescription();
        while (res.moveToNext()){
            if(ArticleScreen.helper.getId() == Integer.parseInt(res.getString(0))){
                Article article = new Article(res.getString(1),res.getString(2),res.getString(3)," ");
                TextView title = (TextView) view.findViewById(R.id.article_readers_view_title);
                TextView description = (TextView) view.findViewById(R.id.article_readers_view_description);
                TextView articletext = (TextView) view.findViewById(R.id.article_readers_view_text);
                title.setText(res.getString(1));
                description.setText(res.getString(2));
                articletext.setText(res.getString(4));
            }
        }
    }
}
