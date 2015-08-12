package com.example.melvil.tic_tac_toe;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samuel on 12/08/15.
 */
public class TaskGetLineOfTheWeb extends AsyncTask<Object, Void, Object> {

    @Override
    protected Object doInBackground(Object... params) {
        Object[] result = new Object[2];
        HttpURLConnection connection;
        URL UrlGetLineOfGame = (URL) params[0];
        Integer idPlayer1 = (Integer) params[1];
        String data = "idPlayer1=" + idPlayer1;
        Boolean finish = false;
        while (!finish) {
            try {
                connection = (HttpURLConnection) UrlGetLineOfGame.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");

                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(data);
                outputStream.flush();
                outputStream.close();

                Integer responseCode = connection.getResponseCode();
                Log.d("Reponse code", responseCode.toString());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                JSONArray jsonArray = new JSONArray(response.toString());
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                if (jsonObject.getString("idPlayer2") != null) {
                    finish = true;
                    result[0] = true;
                    result[1] = jsonObject;
                }
                in.close();
                connection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
