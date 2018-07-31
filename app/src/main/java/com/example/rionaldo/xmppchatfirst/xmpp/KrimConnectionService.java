package com.example.rionaldo.xmppchatfirst.xmpp;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.rionaldo.xmppchatfirst.DefaultConstant;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;

public class KrimConnectionService extends Service {

    private static final String TAG = KrimConnectionService.class.getSimpleName();

    private boolean mActive; //nyimpen threadnya aktif atau nggak
    private Thread mThread;
    private Handler mTHandler; //digunain buat post message ke background thread
    private static KrimConnection mConnection;

    public KrimConnectionService() {
    }

    private void initConnection(){
        Log.e(TAG, "initConnection: " );

        if (null == mConnection)
            mConnection = new KrimConnection(this);

        try{
            mConnection.connect();
        }catch (IOException | SmackException | XMPPException e){

            Intent intent = new Intent(DefaultConstant.BroadcastMessage.UI_CONNECTION_ERROR);
            getApplicationContext().sendBroadcast(intent);

            Log.e(TAG, "initConnection: ",e );

            boolean loggedInState = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                    .getBoolean("xmpp_logged_in",false);

            stopSelf();
        }
    }

    public void start(){
        Log.e(TAG, "Service Start() function called, mActive: "+mActive );
        if (!mActive){
            mActive = true;
            if (null == mThread || !mThread.isAlive()){
                mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        mTHandler = new Handler();
                        initConnection();
                        //The Code here is run in a background thread
                        Looper.loop();
                    }
                });

                mThread.start();
            }
        }
    }

    public void stop(){
        Log.e(TAG, "stop: " );
        if (null != mTHandler) {
            mTHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (null != mConnection) {
                        mConnection.disconnect();
                    }
                }
            });
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Do Your Task Here
        start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }
}
