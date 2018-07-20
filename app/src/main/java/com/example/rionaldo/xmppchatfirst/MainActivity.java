package com.example.rionaldo.xmppchatfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_login) Button btnLogin;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login :
            {
                Log.e(TAG, "Login Button has been Clicked");

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //Background thread should be here
                        XMPPTCPConnectionConfiguration conConfig = null;

                        try{
                            conConfig = XMPPTCPConnectionConfiguration.builder()
                                    .setUsernameAndPassword("scousers","magina12")
                                    .setXmppDomain("chinwag.im")
                                    .setKeystoreType(null)
                                    .build();
                        }catch (XmppStringprepException e){
                            Log.e(TAG, "run1: ",e );
                            e.printStackTrace();
                        }

                        AbstractXMPPConnection connection = new XMPPTCPConnection(conConfig);

                        connection.addConnectionListener(new ConnectionListener() {
                            @Override
                            public void connected(XMPPConnection connection) {
                                Log.e(TAG, "connected: ");
                            }

                            @Override
                            public void authenticated(XMPPConnection connection, boolean resumed) {
                                Log.e(TAG, "authenticated: ");
                            }

                            @Override
                            public void connectionClosed() {
                                Log.e(TAG, "connectionClosed: ");
                            }

                            @Override
                            public void connectionClosedOnError(Exception e) {
                                Log.e(TAG, "connectionClosedOnError: ");
                            }

                            @Override
                            public void reconnectionSuccessful() {
                                Log.e(TAG, "reconnectionSuccessful: ");
                            }

                            @Override
                            public void reconnectingIn(int seconds) {
                                Log.e(TAG, "reconnectingIn: " );
                            }

                            @Override
                            public void reconnectionFailed(Exception e) {
                                Log.e(TAG, "reconnectionFailed: " );
                            }
                        });

                        ChatManager chatManager = ChatManager.getInstanceFor(connection);
                        chatManager.addIncomingListener(new IncomingChatMessageListener() {
                            @Override
                            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                                Log.e(TAG, "newIncomingMessage: "+message.getBody() );
                            }
                        });

                        try {
                            connection.connect().login();
                        } catch (XMPPException | SmackException | IOException | InterruptedException e) {
                            Log.e(TAG, "run2: ",e );
                            e.printStackTrace();
                        }
                    }
                };

                Thread connectionThread = new Thread(runnable);
                connectionThread.start();
                break;
            }
        }
    }
}