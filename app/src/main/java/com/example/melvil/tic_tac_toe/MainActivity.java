package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
    public EditText et;
    ImageButton b;
    Button play;
    Boolean choice;
    Button multilayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        b = (ImageButton) findViewById(R.id.drapeau);
        play = (Button) findViewById(R.id.play);
        multilayer = (Button) findViewById(R.id.multi);
        choice = true;
        /*
        Click listener for the multiplayer button, starts an Intent of ListPLayersActivity
         */
        multilayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No name was inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), ListPlayersActivity.class);
                    intent.putExtra("name",et.getText().toString());
                    startActivity(intent);
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No name was inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
<<<<<<< Updated upstream
                    intent.putExtra("name",et.getText().toString());
                    startActivity(intent);
=======
                   // intent.putExtra("et", et);
                    startActivity(intent);
                    Intent intent1 = new Intent(getApplicationContext(),ListPlayersActivity.class);
                    startActivity(intent1);

                    String str = (String) getIntent().getSerializableExtra("et");

>>>>>>> Stashed changes
                }
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
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
}


