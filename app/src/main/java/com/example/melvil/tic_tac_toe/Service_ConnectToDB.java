package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
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
    Map<Integer, String> namesAndIp;
    URL removeLinesOnGames;
    URL getNamesAndIP;
    URL addLineOnGames;


    public Service_ConnectToDB() {
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
     * @return Map<StringURL> who contains all players able to play
     */
    public Map<Integer, String> getNamesAndIp() {
        JSONArray jsonArray = null;
        TaskGetNamesAndIps taskGetNamesAndIps;
        try {
            taskGetNamesAndIps = new TaskGetNamesAndIps();
            taskGetNamesAndIps.execute(getNamesAndIP);
            jsonArray = taskGetNamesAndIps.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        int index = 0;
        JSONObject jsonObject;
        if (jsonArray != null && !jsonArray.isNull(index)) {

            while (!jsonArray.isNull(index)) {
                try {
                    jsonObject = jsonArray.getJSONObject(index);
                    namesAndIp.put(jsonObject.getInt("idPlayers"), jsonObject.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                index++;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Nobody  online", Toast.LENGTH_LONG).show();
        }
        return namesAndIp;
    }

    public void addLineOnGame(String name) {
        TaskAddLineOnGames taskAddLineOnGames = new TaskAddLineOnGames();
        taskAddLineOnGames.execute(addLineOnGames, name);
        try {
            Toast.makeText(getApplicationContext(), taskAddLineOnGames.get().toString(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void removeLineOnGame(Integer id) {
        TaskRemoveLineOnGames taskRemoveLineOnGames = new TaskRemoveLineOnGames();
        taskRemoveLineOnGames.execute(removeLinesOnGames, id);
        try {
            Toast.makeText(getApplicationContext(), taskRemoveLineOnGames.get().toString(), Toast.LENGTH_LONG).show();
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

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        ;
        super.onPostExecute(jsonArray);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected JSONArray doInBackground(URL... params) {
        JSONArray jsonArray = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) params[0].openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
                jsonArray = new JSONArray(responseStrBuilder.toString());
                connection.disconnect();
                in.close();
                streamReader.close();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
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
        addLinesURL = (URL) params[0];
        String name = (String) params[1];
        String data = "name=" + name;
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

            connection.disconnect();
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