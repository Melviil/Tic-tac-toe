package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Melvil on 07/08/15.
 */
public class GameActivity extends Activity {
    Button[][] buttons;
    String name;
    String var;
    int i;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        name = getIntent().getExtras().getString("name");
        buttons = new Button[3][3];
        buttons[0][0] = (Button) findViewById(R.id.button1);
        buttons[0][1] = (Button) findViewById(R.id.button2);
        buttons[0][2] = (Button) findViewById(R.id.button3);
        buttons[1][0] = (Button) findViewById(R.id.button4);
        buttons[1][1] = (Button) findViewById(R.id.button5);
        buttons[1][2] = (Button) findViewById(R.id.button6);
        buttons[2][0] = (Button) findViewById(R.id.button7);
        buttons[2][1] = (Button) findViewById(R.id.button8);
        buttons[2][2] = (Button) findViewById(R.id.button9);
        var = "o";
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Button b = (Button) v;

                        if (var == "o") {
                            var = "x";
                        } else {
                            var = "o";
                        }
                        b.setText(var);
                        if (checkForCompleted("x")) showDialog();
                        if (checkForCompleted("o")) showDialog();
                    }
                });
            }
        }
    }

    private boolean checkForCompleted(String symbol){
        if (buttons[0][0].getText() == symbol && buttons[1][1].getText() == symbol && buttons[2][2].getText() == symbol){
            Log.v("GameActivity", "checkForCompleted: completed in cross - " + symbol);
            return true;
        }
        if (buttons[0][2].getText() == symbol && buttons[1][1].getText() == symbol && buttons[2][0].getText() == symbol){
            Log.v("GameActivity","checkForCompleted: completed in cross - "+symbol);
            return true;
        }
        for (i = 0; i < 3; i++) {
            if (buttons[i][0].getText() == symbol && buttons[i][1].getText() == symbol && buttons[i][2].getText() == symbol){
                Log.v("GameActivity","checkForCompleted: completed in row - "+symbol);
                return true;
            }
            if (buttons[0][i].getText() == symbol && buttons[1][i].getText() == symbol && buttons[2][i].getText() == symbol){
                Log.v("GameActivity","checkForCompleted: completed in column - "+symbol);
                return true;
            }
        }
        return false;
    }

<<<<<<< HEAD
    private void showDialog(){
=======
    private void showDialog(String team){
        String Player = getResources().getString(R.string.Player);
        String just_won = getResources().getString(R.string.just_won);
>>>>>>> 949bf5f... Language automatic support

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_gameover);
<<<<<<< HEAD
        dialog.setTitle("You just won!");
=======
        dialog.setTitle(Player+" '"+team+"' "+just_won);
>>>>>>> 949bf5f... Language automatic support

        // set the custom dialog components - text, image and button
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.rond);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonAgain);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
