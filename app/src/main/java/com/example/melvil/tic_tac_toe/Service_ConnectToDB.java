package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        task.execute(addLineOnGames, name);
        try {
            Toast.makeText(getApplicationContext(), task.get().toString(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void removeLineOnGame(Integer id) {
        TaskRemoveLineOnGames task = new TaskRemoveLineOnGames();
        task.execute(removeLinesOnGames, id);
        try {
            Toast.makeText(getApplicationContext(),task.get().toString(),Toast.LENGTH_LONG).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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
    URL addLinesURL;
    Boolean finish = false;
    HttpURLConnection connection;

    @Override
    protected Boolean doInBackground(Object... params) {
        String name = (String) params[1];
        String data = "name=" + name;
        addLinesURL = (URL) params[0];
        try {
            connection = (HttpURLConnection) addLinesURL.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + addLinesURL);
            System.out.println("Post parameters : " + addLinesURL);
            System.out.println("Response Code : " + responseCode);

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
class TaskRemoveLineOnGames extends AsyncTask<Object, Integer, Boolean> {
    HttpURLConnection connection;
    URL removeLineWithId;
    @Override
    protected Boolean doInBackground(Object... params) {

        removeLineWithId = (URL) params[0];
        Integer id = (Integer) params[1];
        String data = "id=" + id;
        try {
            connection = (HttpURLConnection) removeLineWithId.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + removeLineWithId);
            System.out.println("Post parameters : " + data);
            System.out.println("Response Code : " + responseCode);

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