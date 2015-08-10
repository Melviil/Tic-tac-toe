package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by samuel on 08/08/15.
 */
public class Service_ConnectToDB extends Service {
    //TODO Finish this class
    Map<String, URL> namesAndIp;
    URL abbaye;

    public Service_ConnectToDB() {
        try {
            abbaye = new URL("abbaye.noip.me");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public Service_ConnectToDB getMyservice() {
            return new Service_ConnectToDB();
        }
        //TODO verify this code
        public Map<String, URL> getNamesAndIp() {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection)abbaye.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                int cp;
                try {
                    while ((cp = reader.read()) != -1) {
                        stringBuilder.append((char) cp);
                    }
                    String json=stringBuilder.toString();
                    Toast.makeText(getApplicationContext(),json,Toast.LENGTH_SHORT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return namesAndIp;
        }
    }
}
