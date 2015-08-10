package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.Map;

/**
 * Created by samuel on 09/08/15.
 */
public class ListPlayersActivity extends Activity {
    Service_ConnectToDB service_connectToDB;
    ProgressBar progressBar;
    ListView listView;
    Map<String,URL> players;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           service_connectToDB = ((Service_ConnectToDB.MyBinder)service).getMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service_connectToDB = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listplayers);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(getApplicationContext(),Service_ConnectToDB.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
    }
}
