package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Service who is the intermediate between all the Async actions
 * Created by samuel on 08/08/15.
 */
public class Service_ConnectToDB extends Service {
    //TODO Finish this class
    Map<String, URI> namesAndIp;
    URL removeLinesOnGames;
    URL getNamesAndIP;
    URL addLineOnGames;
    ProgressBar progressBar;

    public Service_ConnectToDB() {
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            //URL to obtain Json object that contains names and ips
            this.getNamesAndIP = new URL("http://abbaye.noip.me/Android/getNamesandIP.php");
            this.addLineOnGames = new URL("http://abbaye.noip.me/Android/addLineOnGames.php");
            this.removeLinesOnGames = new URL("http://abbaye.noip.me/Android/removeLineOnGames.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        namesAndIp = new HashMap<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    /**
     * Method who returns a Map of players connected to the webService
     *
     * @return Map<String URL> who contains all players able to play
     */
    public Map<String, URI> getNamesAndIp() {
        JSONArray jsonArray = null;
        try {
            TaskGetNamesAndIps task = new TaskGetNamesAndIps();
            task.setProgressBar(progressBar);
            task.execute(getNamesAndIP);
            jsonArray = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        int index = 0;
        JSONObject jsonObject;
        assert jsonArray != null;
        while (!jsonArray.isNull(index)) {
            try {
                jsonObject = jsonArray.getJSONObject(index);
                namesAndIp.put(jsonObject.getString("idPlayers") + " " + jsonObject.getString("name"), new URI(jsonObject.getString("IP")));
            } catch (JSONException | URISyntaxException e) {
                e.printStackTrace();
            }
            index++;
        }
        return namesAndIp;
    }

    public void addLineOnGame(String name) {
        TaskAddLineOnGames task = new TaskAddLineOnGames();
        task.execute(addLineOnGames,name);
        try {
            Toast.makeText(getApplicationContext(),task.get().toString(),Toast.LENGTH_LONG).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void rmeoveLineOngame(int id) {

    }

    public class MyBinder extends Binder {
        public Service_ConnectToDB getMyService() {
            return Service_ConnectToDB.this;
        }
    }
}

/**
 * Task who returns the acces to de DB all the names and ip on a JSONArray.
 * Created by samuel on 10/08/15.
 */
class TaskGetNamesAndIps extends AsyncTask<URL, Integer, JSONArray> {
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
                connection.disconnect();
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

/**
 * Class who can add lines on the DB
 * Created by samuel on 10/08/15.
 */
//TODO Complete this class
class TaskAddLineOnGames extends AsyncTask<Object, Integer, Boolean> {
    URL addLinesIRL;
    String parameters;
    OutputStreamWriter request=null;
    Boolean finish =  false;
    @Override
    protected Boolean doInBackground(Object... params) {
        addLinesIRL = (URL)params[0];
        String name =  (String)params[1];
        parameters = "name="+name;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)addLinesIRL.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            request = new OutputStreamWriter(httpURLConnection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();
            httpURLConnection.disconnect();
            finish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finish;
    }
}

/**
 * Class who can remove lines on the DB
 */
//TODO Complete this class
class TaskRemoveLineOnGames extends AsyncTask<Integer, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(Integer... params) {
        return null;
    }
}