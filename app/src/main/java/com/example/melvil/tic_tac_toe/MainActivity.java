package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
    ImageButton b;
    LinearLayout Ll;
    Button play;
    Boolean choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (ImageButton) findViewById(R.id.drapeau);
        play = (Button) findViewById(R.id.play);
        choice = true;
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });


        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (choice) {
                    b.setImageResource(R.drawable.fra);
                    choice = false;
                } else {
                    b.setImageResource(R.drawable.eng);
                    choice = true;
                }
            }
        });
    }
}
