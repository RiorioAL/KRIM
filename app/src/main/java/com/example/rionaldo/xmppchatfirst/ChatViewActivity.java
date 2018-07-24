package com.example.rionaldo.xmppchatfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.rionaldo.xmppchatfirst.Adapter.MessageAdapter;
import com.example.rionaldo.xmppchatfirst.Entity.MessageItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatViewActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.recyclerChatView) RecyclerView recyclerChatView;
    @BindView(R.id.et_input_chat) EditText etInputChat;
    @BindView(R.id.ib_send) ImageButton ibSend;
    @BindView(R.id.ib_income) ImageButton ibIncome;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private List<MessageItem> items = new ArrayList<>();
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_pink);

        MessageItem receive = new MessageItem();
        receive.setType(0);

        MessageItem sent = new MessageItem();
        receive.setType(1);

        items.add(receive);
        items.add(sent);
        items.add(receive);
        items.add(sent);
        items.add(receive);
        items.add(sent);
        items.add(receive);
        items.add(sent);
        items.add(receive);
        items.add(sent);

        adapter = new MessageAdapter(items);
        recyclerChatView.setAdapter(adapter);
        recyclerChatView.setLayoutManager(new LinearLayoutManager(ChatViewActivity.this,LinearLayoutManager.VERTICAL,true));
        recyclerChatView.setHasFixedSize(true);

        ibIncome.setOnClickListener(this);
        ibSend.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_send:
                MessageItem send = new MessageItem();
                send.setType(1);
                send.setMessage("Send");
                adapter.addItems(send);
                recyclerChatView.scrollToPosition(0);
                break;
            case R.id.ib_income:
                MessageItem receive = new MessageItem();
                receive.setType(0);
                receive.setMessage("Receive");
                adapter.addItems(receive);
                recyclerChatView.scrollToPosition(0);
                break;
        }
    }
}
