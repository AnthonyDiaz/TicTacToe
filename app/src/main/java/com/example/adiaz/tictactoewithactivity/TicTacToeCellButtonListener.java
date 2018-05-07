package com.example.adiaz.tictactoewithactivity;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by 8314 on 3/13/2018.
 */

public class TicTacToeCellButtonListener implements OnClickListener {
    MainActivity mainActivity;
    public TicTacToeCellButtonListener(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void onClick(View view){
        // identify which button is the event source view
        for (int i = 0; i < TicTacToe.SIDE; i++ )
            for (int j = 0; j< TicTacToe.SIDE; j++)
                if (view == this.mainActivity.buttons[i][j]){
                    this.mainActivity.updateGrid(i,j);
                }
    }
}
