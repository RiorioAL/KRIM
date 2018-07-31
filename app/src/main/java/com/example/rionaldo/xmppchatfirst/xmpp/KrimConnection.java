package com.example.rionaldo.xmppchatfirst.xmpp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.rionaldo.xmppchatfirst.DefaultConstant;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

public class KrimConnection implements ConnectionListener {

    private static final String TAG = KrimConnection.class.getSimpleName();

    private final Context mAppContext;
    private String mUsername;
    private String mPassword;
    private String mServiceName;
    private XMPPTCPConnection connection;

    public KrimConnection(Context mAppContext) {
        Log.e(TAG, "KrimConnection Constructor Called" );
        this.mAppContext = mAppContext;
    }

    private void gatherCredentials(){
        String jid = PreferenceManager.getDefaultSharedPreferences(mAppContext)
                .getString("xmpp_jid",null);
        mPassword = PreferenceManager.getDefaultSharedPreferences(mAppContext)
                .getString("xmpp_password",null);

        if (null != jid){
            String[] tempJID = jid.split("@");
            mUsername = tempJID[0];
            mServiceName = tempJID[1];
        }else {
            mUsername = "";
            mServiceName = "";
        }
    }

    public void connect() throws IOException, XMPPException, SmackException{
        gatherCredentials();

        XMPPTCPConnectionConfiguration conf = XMPPTCPConnectionConfiguration.builder()
                .setXmppDomain(mServiceName)
                .setHost(mServiceName)
                .setResource("KRIM")

                //untuk SSL Problem
                .setKeystoreType(null)
                //untuk ngirim presence pas online
                .setSendPresence(true)
                //untuk buka log
                .setDebuggerEnabled(true)
                //kita cman pengen pake Koneksi yang aman
                .setSecurityMode(ConnectionConfiguration.SecurityMode.required)
                .setCompressionEnabled(true)
                .build();

        //ini partneran sama yang setDebuggerEnabled
        SmackConfiguration.DEBUG = true;
        //untuk aktifin Stream Management
        XMPPTCPConnection.setUseStreamManagementDefault(true);

        connection = new XMPPTCPConnection(conf);
        connection.setUseStreamManagement(true);
        connection.setUseStreamManagementResumption(true);
        //ini bakal resume connection setiap 5 detik
        connection.setPreferredResumptionTime(60);
        //untuk masang connection listener
        connection.addConnectionListener(this);

        try {
            Log.e(TAG, "Calling Connect()");
            connection.connect();
            connection.login(mUsername,mPassword);
            Log.e(TAG, "login Called" );
        }catch (InterruptedException e){
            Log.e(TAG, "connect: ",e );
        }
    }

    public void disconnect(){
        Log.e(TAG, "Disconnecting from server "+mServiceName );
        //ini cman simpen di local aja
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mAppContext);
        prefs.edit().putBoolean("xmpp_logged_in",false).apply();

        if (null != connection){
            //ga di destroy karena nnti mau login ulang lagi
            connection.disconnect();
        }
    }

    @Override
    public void connected(XMPPConnection connection) {
        Log.e(TAG, "connected: " );
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
        //ini udah authenticated
        Log.e(TAG, "authenticated: " );
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mAppContext);
        prefs.edit()
                .putBoolean("xmpp_logged_in",true)
                .apply();

        Intent intent = new Intent(DefaultConstant.BroadcastMessage.UI_AUTHENTICATED);
        mAppContext.sendBroadcast(intent);
    }

    @Override
    public void connectionClosed() {
        Log.e(TAG, "connectionClosed: " );
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        Log.e(TAG, "connectionClosedOnError: ",e );
    }

    @Override
    public void reconnectionSuccessful() {
        Log.e(TAG, "reconnectionSuccessful: " );
    }

    @Override
    public void reconnectingIn(int seconds) {
        Log.e(TAG, "reconnectingIn: "+seconds+" seconds" );
    }

    @Override
    public void reconnectionFailed(Exception e) {
        Log.e(TAG, "reconnectionFailed: ",e );
    }
}
