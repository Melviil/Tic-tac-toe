package com.example.melvil.tic_tac_toe;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samuel on 10/08/15.
 */
public class TaskGetNamesAndIps extends AsyncTask<URL, Integer, JSONObject> {
    ProgressBar progressBar;
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        progressBar.setProgress(4);
        super.onPostExecute(jsonObject);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected JSONObject doInBackground(URL... params) {

        JSONObject jsonObject = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) params[0].openConnection();
            int responseCode = connection.getResponseCode();
            publishProgress(1);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                publishProgress(2);
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
                jsonObject = new JSONObject(responseStrBuilder.toString());
            }
            publishProgress(3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public void setProgressBar(ProgressBar progressBar){
        this.progressBar = progressBar;
    }
}



