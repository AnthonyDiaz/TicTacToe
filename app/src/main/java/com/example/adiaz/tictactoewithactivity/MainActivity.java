package com.example.adiaz.tictactoewithactivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TicTacToe game; //data model
    int w;
    public Button buttons[][];

    //Controller will initialize the data model and related view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //initialize the data model
        game = new TicTacToe();

        buildGUIbyCode();

        String playerOrder = getIntent().getStringExtra("PlayerOrder");

        Toast.makeText(this, playerOrder + " goes first",
                Toast.LENGTH_LONG).show();

        //startActivity(new Intent(MainActivity.this, PlayerSelectionActivity.class));
    }

    private void buildGUIbyCode(){

        //create our own view (grid-based board view)
        //1. retrieve the size of the device
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        //2. decide the size for each cell of the tic tac toe game board
        w = size.x / TicTacToe.SIDE;

        //3. initialize buttons on the grid
        GridLayout board = new GridLayout( this);
        board.setColumnCount(TicTacToe.SIDE);
        board.setRowCount(TicTacToe.SIDE);
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        for (int row = 0; row < TicTacToe.SIDE; row++)
            for (int col = 0; col < TicTacToe.SIDE; col++){
                buttons[row][col] = new Button(this);
                buttons[row][col].setTextSize((int)(w * 0.2));
                buttons[row][col].setText("");
                //buttons[row][col].setBackgroundColor(Color.GREEN);
                //solution 1 - register for unknown-name (anonymous) listener
                /*buttons[row][col].setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        // identify which button is the event source view
                        for (int i = 0; i < TicTacToe.SIDE; i++ )
                            for (int j = 0; j< TicTacToe.SIDE; j++)
                                if (view == buttons[i][j]){
                                    updateGrid(i,j);
                                }
                    }
                });*/

                /*
                ButtonListener listener = new ButtonListener();
                buttons[row][col].setOnClickListener(listener);
                */
                TicTacToeCellButtonListener listener =
                        new TicTacToeCellButtonListener(this);
                buttons[row][col].setOnClickListener(listener);

                board.addView(buttons[row][col], w, w);
            }

        setContentView(board);
    }
    public void updateGrid(int row, int col){

        String playerFirst = getIntent().getStringExtra("PlayerOrder");
        String playerColor = getIntent().getStringExtra("PlayerColor");

        if (buttons[row][col].getText().toString().trim().equals("")) {
            // play on the board and change the data model
            int player = game.play(row, col);
            // change the button view by the player sign 'X' or 'O'
            if (player == 1)
            {
                // Set Player
                if(playerFirst.equals("X"))
                {
                    buttons[row][col].setText("X");
                }
                else
                {
                    buttons[row][col].setText("O");
                }

                // Set Player Color
                if(playerColor.equals("Red"))
                {
                    buttons[row][col].setTextColor(Color.RED);
                }else if(playerColor.equals("Blue")){
                    buttons[row][col].setTextColor(Color.BLUE);
                }
            }
            else
            {
                // Set Player
                if(playerFirst.equals("X"))
                {
                    buttons[row][col].setText("O");
                }
                else
                {
                    buttons[row][col].setText("X");
                }

                // Set Player Color
                if(playerColor.equals("Red"))
                {
                    buttons[row][col].setTextColor(Color.BLUE);
                }else if(playerColor.equals("Blue")){
                    buttons[row][col].setTextColor(Color.RED);
                }
            }


            //is game over?
        }
    }

    // solution 2 - inner class
    public class ButtonListener implements View.OnClickListener{
        public void onClick(View view){
            // identify which button is the event source view
            for (int i = 0; i < TicTacToe.SIDE; i++ )
                for (int j = 0; j< TicTacToe.SIDE; j++)
                    if (view == buttons[i][j]){
                        updateGrid(i,j);
                    }
        }
    }
}
