package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

import org.apache.http.client.HttpClient;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Melvil on 07/08/15.
 */
public class GameActivity extends Activity {
    Map<Uri,String> playerIpName;
    URL url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        try {
            url =  new URL("abbaye.noip.me");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
