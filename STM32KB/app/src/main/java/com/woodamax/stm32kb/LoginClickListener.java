package com.woodamax.stm32kb;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by maxim on 23.03.2017.
 */

public class LoginClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        //Toast.makeText(view.getContext(), "Clicked on login", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), LoginScreen.class);
        view.getContext().startActivity(intent);
    }
}
