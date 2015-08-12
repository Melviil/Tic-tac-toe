package com.example.melvil.tic_tac_toe;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samuel on 12/08/15.
 */
public class TaskJoinAGame extends AsyncTask<Object,Void,Void> {
    @Override
    protected Void doInBackground(Object... params) {
        HttpURLConnection connection;
        URL urlJoinAGame = (URL) params[0];
        Integer idPlayer1 = (Integer) params[1];
        String namePlayer2 = (String) params[2];
        String data = "idPlayer"+idPlayer1+"&namePlayer2"+namePlayer2;
        try {
            connection = (HttpURLConnection) urlJoinAGame.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
