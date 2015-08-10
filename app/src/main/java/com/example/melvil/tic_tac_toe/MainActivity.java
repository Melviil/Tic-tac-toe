package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;


public class MainActivity extends Activity {
   public EditText et;
    ImageButton b;
    Button play;
    Boolean choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        b = (ImageButton) findViewById(R.id.drapeau);
        play = (Button) findViewById(R.id.play);
        choice = true;
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (et.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Aucun nom n'a été rentré", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
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
    }}


