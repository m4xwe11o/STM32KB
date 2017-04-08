package com.woodamax.stm32kb;

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

public class ArticleFragment extends Fragment {
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
                    Toast.makeText(view.getContext(),selectedItem,Toast.LENGTH_SHORT).show();
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

    private void addArticlePreview(String controller) {
        LinearLayout rootView = (LinearLayout) getActivity().findViewById(R.id.article_selection_preview);
        TextView tile = new TextView(getActivity());
        TextView desc = new TextView(getActivity());
        rootView.removeAllViews();
        switch (controller){
            case "STM32F1":
                tile.setText(controller + " - ADC");
                tile.setTextSize(25);
                rootView.addView(tile);
                desc.setText("This is an description for the " + controller + " ADC configuration...");
                desc.setTextSize(15);
                rootView.addView(desc);
                break;
            case "STM32F3":
                tile.setText(controller + " - ADC");
                tile.setTextSize(25);
                rootView.addView(tile);
                desc.setText("This is an description for the "+ controller + "ADC configuration...");
                desc.setTextSize(15);
                rootView.addView(desc);
                break;
            case "STM32F4":
                tile.setText(controller + " - ADC");
                tile.setTextSize(25);
                rootView.addView(tile);
                desc.setText("This is an description for the "+ controller + "ADC configuration...");
                desc.setTextSize(15);
                rootView.addView(desc);
                break;
            default:
                break;
        }
    }
}
