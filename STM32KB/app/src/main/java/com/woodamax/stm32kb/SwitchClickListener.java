package com.woodamax.stm32kb;

import android.view.View;
import android.widget.Toast;

/**
 * Created by maxim on 24.03.2017.
 */

public class SwitchClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),"Clicked on switch",Toast.LENGTH_SHORT).show();
    }
}
