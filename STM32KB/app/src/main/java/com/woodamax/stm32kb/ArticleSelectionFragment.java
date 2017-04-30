package com.woodamax.stm32kb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by maxim on 05.04.2017.
 */

public class ArticleSelectionFragment extends Fragment {
    DatabaseHelper myDBH;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.article_selection_fragment, container, false);
        //Used to rewrite the text for each categorie
        //final TextView none = (TextView) view.findViewById(R.id.article_title);
        //used to fill the spinner
        //this is one method to do this
        //String [] values = getResources().getStringArray(R.array.micro_controllers);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //This uses an different design for the spinner
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.micro_controllers, R.layout.my_spinner_item);
        final Spinner microControllerSpinner = (Spinner) view.findViewById(R.id.article_micro_controller_spinner);
        microControllerSpinner.setAdapter(adapter);
        //Click Listener for the items inside the spinner
        int selectionCurrent = microControllerSpinner.getSelectedItemPosition();
        microControllerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Select Controller")){
                    //Keeps crashing on 4.2.2 with getContext()
                    //none.setText("");
                    addArticlePreview("none");
                }
                if(selectedItem.equals("STM32F1")){
                    //Keeps crashing on 4.2.2 with getContext()
                    //Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                    //none.setText(getString(R.string.controller_f1));
                    addArticlePreview("STM32F1");
                }
                if(selectedItem.equals("STM32F3")){
                    //Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                    //none.setText(getString(R.string.controller_f3));
                    addArticlePreview("STM32F3");
                }
                if(selectedItem.equals("STM32F4")){
                    //Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                    //none.setText(getString(R.string.controller_f4));
                    addArticlePreview("STM32F4");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    //TODO dynamic view for the discription via DB query
    private void addArticlePreview(String controller) {
        switch (controller){
            case "STM32F1":
                buildSelectionView(controller);
                break;
            case "STM32F3":
                buildSelectionView(controller);
                break;
            case "STM32F4":
                buildSelectionView(controller);
                break;
            default:
                buildSelectionView(controller);
                break;
        }
    }

    private void buildSelectionView(String controller) {
        //This is the parentview
        ScrollView parentView = (ScrollView) getActivity().findViewById(R.id.article_selection_preview);
        LinearLayout childview = new LinearLayout(getActivity());
        //Because we build a new view, we have to define the xml parameters
        childview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        childview.setOrientation(LinearLayout.VERTICAL);
        //finally remove everything
        parentView.removeAllViews();
        myDBH = new DatabaseHelper(getActivity());
        Cursor res = myDBH.getArticleDescription();
        while (res.moveToNext()) {
            if(res.getString(1).contains(controller)){
                //Used to build the childview
                TextView title = new TextView(getActivity());
                TextView desc = new TextView(getActivity());
                //set params for the title
                title.setText(res.getString(1));
                title.setTextSize(25);
                title.setPadding(10,0,10,0);
                title.setId(Integer.parseInt(res.getString(0)));
                //set the params for the description
                desc.setText(res.getString(2));
                desc.setTextSize(15);
                desc.setPadding(10,0,10,50);
                desc.setId(Integer.parseInt(res.getString(0)));
                desc.setClickable(true);
                desc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSelectedArticle(v);
                    }
                });
                //add the views
                childview.addView(title);
                childview.addView(desc);
            }
        }
        //add the child to their parents
        parentView.addView(childview);
    }
    private void getSelectedArticle(View v) {
        myDBH = new DatabaseHelper(getActivity());
        Cursor res = myDBH.getArticleDescription();
        while(res.moveToNext()){
            if(v.getId() == Integer.parseInt(res.getString(0))){
                Toast.makeText(getContext(),res.getString(1), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
