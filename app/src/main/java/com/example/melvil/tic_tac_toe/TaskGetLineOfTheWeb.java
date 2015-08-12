package com.example.melvil.tic_tac_toe;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samuel on 12/08/15.
 */
public class TaskGetLineOfTheWeb extends AsyncTask<Object,Void,Object> {

    @Override
    protected Object doInBackground(Object... params) {
        HttpURLConnection connection;
        URL UrlGetLineOfGame = (URL) params[0];
        Integer idPlayer = (Integer) params[1];
        String data = "id=" + id;
        try {
            connection = (HttpURLConnection) UrlGetLineOfGame.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + UrlGetLineOfGame);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
