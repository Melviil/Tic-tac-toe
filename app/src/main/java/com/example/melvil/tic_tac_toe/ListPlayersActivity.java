package com.example.melvil.tic_tac_toe;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
<<<<<<< Updated upstream
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
=======
>>>>>>> Stashed changes

/**
 * Class who list the player waiting for a challenger
 * Created by samuel on 09/08/15.
 */
public class ListPlayersActivity extends Activity {
    Service_ConnectToDB service_connectToDB;
<<<<<<< Updated upstream
    String name;
    ProgressBar progressBar;
    ListView listView;
    ArrayAdapter<String> adapter;
    Map<String, URI> players;
    Button create;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            service_connectToDB = ((Service_ConnectToDB.MyBinder) service).getMyService();
=======
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           service_connectToDB = ((Service_ConnectToDB.MyBinder)service).getMyservice();
>>>>>>> Stashed changes
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service_connectToDB = null;
        }
    };
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listplayers);
        name = getIntent().getExtras().getString("name");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        create = (Button) findViewById(R.id.create);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        progressBar.setProgress(0);
        progressBar.setMax(100);


        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service_connectToDB.setProgressBar(progressBar);
                players = service_connectToDB.getNamesAndIp();
                adapter.clear();
                adapter.addAll(players.keySet().toArray(new String[players.size()]));
                adapter.notifyDataSetInvalidated();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String line = adapter.getItem(position);
                String chars = String.valueOf(line.charAt(0));
                chars =  chars + String.valueOf(line.charAt(1));
                Integer iD = Integer.parseInt(chars);
                service_connectToDB.removeLineOnGame(iD);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service_connectToDB.addLineOnGame(name);
                Intent intent = new Intent(getApplicationContext(), WaitingForPlayerActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(getApplicationContext(), Service_ConnectToDB.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
    }

<<<<<<< Updated upstream
=======
    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(getApplicationContext(),Service_ConnectToDB.class);
        bindService(i,serviceConnection,BIND_AUTO_CREATE);
    }
>>>>>>> Stashed changes
}
