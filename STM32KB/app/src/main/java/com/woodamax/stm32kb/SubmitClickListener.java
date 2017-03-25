package com.woodamax.stm32kb;

import android.view.View;
import android.widget.Toast;

/**
 * Created by maxim on 25.03.2017.
 */

public class SubmitClickListener implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),"Good, you accepted the AGB",Toast.LENGTH_SHORT).show();
    }
}
