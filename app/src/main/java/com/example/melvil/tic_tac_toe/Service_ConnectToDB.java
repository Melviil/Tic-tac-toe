package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * Created by samuel on 08/08/15.
 */
public class Service_ConnectToDB extends Service {
    //TODO Finish this class
    Map<String, URL> namesAndIp;
    URL removeLinesOnGames;
    URL getNamesAndIP;
    URL addLineOnGames;

    public Service_ConnectToDB() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
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
    public Map<String, URL> getNamesAndIp() {
        //TODO Need to receive Database inforamtion for the listview -> Coreect this méthod
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                HttpResponse response = null;
                try {
                    request.setURI(getNamesAndIP.toURI());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                try {
                    response = client.execute(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response != null) {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

        };

        return null;
    }

    public class MyBinder extends Binder {
        public Service_ConnectToDB getMyService() {
            return Service_ConnectToDB.this;
        }
    }
}
