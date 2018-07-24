package com.example.rionaldo.xmppchatfirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rionaldo.xmppchatfirst.xmpp.KrimConnectionService;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_jid) EditText etJid;
    @BindView(R.id.et_jid_pass) EditText etJidPass;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.pb_loading) ProgressBar pb_loading;

    private String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login :{
                pb_loading.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.GONE);
                saveCredentialsAndLogin();
//                new LoginAsnyc().execute();
                break;
            }
        }
    }

    private void saveCredentialsAndLogin(){
        Log.e(TAG, "saveCredentialsAndLogin: called()" );
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString("xmpp_jid",etJid.getText().toString())
                .putString("xmpp_password",etJidPass.getText().toString())
                .apply();

        //start the service
        Intent intentService = new Intent(this, KrimConnectionService.class);
        startService(intentService);
        //kalau kita ngejalanin start service:
        //kalau servicenya ga alive nnti dy bakal jalanin onCreate dlu baru onStartCommand
        //kalau servicenya alive lgsg ke onStartCommand
    }

//    public class LoginAsnyc extends AsyncTask<Void,Void,Void>{
//        @Override
//        protected Void doInBackground(Void... voids) {
//            XMPPTCPConnectionConfiguration conConfig = null;
//            String jid = etJid.getText().toString();
//            String[] jidParts = jid.split("@");
//            Log.e(TAG, "doInBackground: "+jidParts[0]+" "+etJidPass.getText().toString()+" "+jidParts[1] );
//            try{
//                conConfig = XMPPTCPConnectionConfiguration.builder()
//                        .setUsernameAndPassword(jidParts[0],etJidPass.getText().toString())
//                        .setXmppDomain(jidParts[1])
//                        .setKeystoreType(null)
//                        .build();
//            }catch (XmppStringprepException e){
//                Log.e(TAG, "run: ",e );
//                e.printStackTrace();
//            }
//
//            AbstractXMPPConnection connection = new XMPPTCPConnection(conConfig);
//
//            connection.addConnectionListener(new ConnectionListener() {
//                @Override
//                public void connected(XMPPConnection connection) {
//                    Log.e(TAG, "connected: ");
//                }
//
//                @Override
//                public void authenticated(XMPPConnection connection, boolean resumed) {
//                    Log.e(TAG, "authenticated: ");
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
////                            pb_loading.setVisibility(View.GONE);
////                            btnLogin.setVisibility(View.VISIBLE);
////                            Toast.makeText(LoginActivity.this,"Connected",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginActivity.this,ChatListActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                }
//
//                @Override
//                public void connectionClosed() {
//                    Log.e(TAG, "connectionClosed: ");
//                }
//
//                @Override
//                public void connectionClosedOnError(Exception e) {
//                    Log.e(TAG, "connectionClosedOnError: ");
//                }
//
//                @Override
//                public void reconnectionSuccessful() {
//                    Log.e(TAG, "reconnectionSuccessful: ");
//                }
//
//                @Override
//                public void reconnectingIn(int seconds) {
//                    Log.e(TAG, "reconnectingIn: " );
//                }
//
//                @Override
//                public void reconnectionFailed(Exception e) {
//                    Log.e(TAG, "reconnectionFailed: " );
//                }
//            });
//
//            ChatManager chatManager = ChatManager.getInstanceFor(connection);
//            chatManager.addIncomingListener(new IncomingChatMessageListener() {
//                @Override
//                public void newIncomingMessage(EntityBareJid from, final Message message, Chat chat) {
//                    Log.e(TAG, "newIncomingMessage: "+message.getBody() );
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                        }
//                    });
//                }
//            });
//
//            try {
//                connection.connect().login();
//            } catch (XMPPException | SmackException | IOException | InterruptedException e) {
//                Log.e(TAG, "run: ",e );
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
}
