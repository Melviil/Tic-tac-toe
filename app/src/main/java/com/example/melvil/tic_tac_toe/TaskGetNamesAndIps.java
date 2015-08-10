package com.example.melvil.tic_tac_toe;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samuel on 10/08/15.
 */
public class TaskGetNamesAndIps extends AsyncTask<URL, Integer, JSONArray> {
    ProgressBar progressBar;

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        progressBar.setProgress(4);
        super.onPostExecute(jsonArray);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected JSONArray doInBackground(URL... params) {
        JSONArray jsonArray = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) params[0].openConnection();
            int responseCode = connection.getResponseCode();
            publishProgress(20);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                publishProgress(40);
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
                jsonArray = new JSONArray(responseStrBuilder.toString());
            }
            publishProgress(60);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}



