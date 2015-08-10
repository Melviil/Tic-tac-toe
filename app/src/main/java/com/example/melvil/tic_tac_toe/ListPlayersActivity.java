package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by samuel on 09/08/15.
 */
public class ListPlayersActivity extends Activity {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    Service_ConnectToDB service_connectToDB;
    ProgressBar progressBar;
    ListView listView;
    ArrayAdapter<String> adapter;
    Map<String, URI> players;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            service_connectToDB = ((Service_ConnectToDB.MyBinder) service).getMyService();
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
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        listView = (ListView) findViewById(R.id.listView);
        players =  new HashMap<>();
        adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,players.keySet().toArray(new String[players.size()]));
        listView.setAdapter(adapter);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hey", Toast.LENGTH_LONG).show();
                service_connectToDB.setProgressBar(progressBar);
                players = service_connectToDB.getNamesAndIp();
                adapter.setNotifyOnChange(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(getApplicationContext(), Service_ConnectToDB.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
    }
}
