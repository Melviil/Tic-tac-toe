package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static String PACKAGE_NAME;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        b = (ImageButton) findViewById(R.id.drapeau);
        play = (Button) findViewById(R.id.play);
        multilayer = (Button) findViewById(R.id.multi);
        choice = true;
        final String no_name = getResources().getString(R.string.no_name);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        prefs = getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        et.setText(prefs.getString(PACKAGE_NAME, ""));

        /*
        Click listener for the multiplayer button, starts an Intent of ListPLayersActivity
         */
        multilayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), no_name, Toast.LENGTH_SHORT).show();
                } else {
                    prefs.edit().putString(PACKAGE_NAME, et.getText().toString()).apply();
                    Intent intent = new Intent(getApplicationContext(), ListPlayersActivity.class);
                    intent.putExtra("name", et.getText().toString());
                    startActivity(intent);
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), no_name, Toast.LENGTH_SHORT).show();
                } else {
                    prefs.edit().putString(PACKAGE_NAME, et.getText().toString()).apply();
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("name", et.getText().toString());
                    startActivity(intent);
                    String str = (String) getIntent().getSerializableExtra("et");

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

}


