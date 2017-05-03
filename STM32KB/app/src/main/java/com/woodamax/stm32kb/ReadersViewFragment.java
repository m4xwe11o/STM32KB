package com.woodamax.stm32kb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
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

        //Used to display the article button when the article is shown
        Button articleButton = (Button) getActivity().findViewById(R.id.my_reading_article_button);
        Button submitButton = (Button) getActivity().findViewById(R.id.my_reading_submit_button);
        if(MainActivity.fh.getCenter().equals("Article_reading_fragment")){
            articleButton.setVisibility(view.VISIBLE);
            if(MainActivity.fh.isAuthor()){
                submitButton.setVisibility(view.VISIBLE);
            }
        }

        articleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fh.setCenter("Article_selection_Fragment");
                FragmentManager fm3 = getFragmentManager();
                FragmentTransaction ft3 = fm3.beginTransaction();
                ArticleSelectionFragment article = new ArticleSelectionFragment();
                ft3.replace(R.id.article_text_container, article, "Article_selection_Fragment");
                ft3.detach(fm3.findFragmentByTag("Article_reading_fragment"));
                ft3.commit();
            }
        });
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
                int controllerType = getControllerType(res.getString(1));
                MainActivity.fh.setControllerType(controllerType);
                TextView title = (TextView) view.findViewById(R.id.article_readers_view_title);
                TextView description = (TextView) view.findViewById(R.id.article_readers_view_description);
                TextView articletext = (TextView) view.findViewById(R.id.article_readers_view_text);
                title.setText(res.getString(1));
                description.setText(res.getString(2));
                articletext.setText(res.getString(4));
            }
        }
    }

    /**
     * This method is used to determine the controller category
     * @param string passed from the Article object
     * @return is equal to the switch/case in ArticleSelectionFragment
     */
    private int getControllerType(String string) {
        if (string.contains("F1")){
            return 1;
        }
        if(string.contains("F3")){
            return 3;
        }else{
            return 4;
        }
    }
}
