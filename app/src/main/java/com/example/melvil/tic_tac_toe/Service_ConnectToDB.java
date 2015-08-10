package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
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

    //TODO verify this code

    /**
     * Method who returns a Map of players connected to the webService
     *
     * @return Map<String,URL> who contains all players able to play
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
                namesAndIp.put(jsonObject.getString("name"), new URI(jsonObject.getString("IP")));
            } catch (JSONException | URISyntaxException e) {
                e.printStackTrace();
            }
            index++;
        }
        return namesAndIp;
    }

    public class MyBinder extends Binder {
        public Service_ConnectToDB getMyService() {
            return Service_ConnectToDB.this;
        }
    }
}
