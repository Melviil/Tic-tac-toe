package com.example.melvil.tic_tac_toe;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by samuel on 08/08/15.
 */
public class Service_ConnectToDB extends Service {
    //TODO Finish this class
    public Service_ConnectToDB() {
    }
    public class MyBinder extends Binder{
        public Service_ConnectToDB getMyservice(){
            return new Service_ConnectToDB();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}
