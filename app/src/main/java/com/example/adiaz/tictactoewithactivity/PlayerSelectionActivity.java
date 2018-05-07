package com.example.adiaz.tictactoewithactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PlayerSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);

        // Obtain Switch Controls
        final Switch swtPlayerFirst = findViewById(R.id.swtPlayerFirst);
        final Switch swtPlayerColor = findViewById(R.id.swtPlayerColor);

        // Player Order Listener
        swtPlayerFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    buttonView.setText("O");
                else
                    buttonView.setText("X");
            }
        });

        // Player Color Listener
        swtPlayerColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    buttonView.setText("Red");
                else
                    buttonView.setText("Blue");
            }
        });

        // Start Game Button
        Button btnStart = findViewById(R.id.btnStart);

        // Start Game Button Listener
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlayerSelectionActivity.this, MainActivity.class);
                intent.putExtra("PlayerOrder", swtPlayerFirst.getText());
                intent.putExtra("PlayerColor", swtPlayerColor.getText());
                startActivity(intent);

                //startActivity(new Intent(PlayerSelectionActivity.this, MainActivity.class));
            }
        });
    }
}
