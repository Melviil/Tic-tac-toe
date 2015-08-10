package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by samuel on 08/08/15.
 */
public class Service_ConnectToDB extends Service {
    //TODO Finish this class
    Map<String, URL> namesAndIp;
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
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    //TODO verify this code

    /**
     * Method who returns a Map of players and connectb to the webService
     *
     * @return Map<String,URL> who contains all players able to play
     */
    public Map<String, URL> getNamesAndIp() {
        JSONObject jsonObject = null;
        try {
            TaskGetNamesAndIps task = new TaskGetNamesAndIps();
            task.setProgressBar(progressBar);
            task.execute(getNamesAndIP);
            task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //  Toast.makeText(getApplicationContext(),jsonObject.toString(),Toast.LENGTH_LONG);
        return null;
    }

    public void test(JSONObject jsonObject) {
        Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();
    }

    public class MyBinder extends Binder {
        public Service_ConnectToDB getMyService() {
            return Service_ConnectToDB.this;
        }
    }
}
