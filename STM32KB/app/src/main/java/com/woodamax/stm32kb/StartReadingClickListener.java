package com.woodamax.stm32kb;

import android.content.Intent;
import android.view.View;

/**
 * Created by maxim on 02.04.2017.
 */

class StartReadingClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(),ArticleScreen.class);
        view.getContext().startActivity(intent);
    }
}
