package com.example.rionaldo.xmppchatfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rionaldo.xmppchatfirst.Adapter.ChatListAdapter;
import com.example.rionaldo.xmppchatfirst.Entity.ChatListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListActivity extends AppCompatActivity {
    @BindView(R.id.recyclerChatList) RecyclerView recyclerChatList;

    ChatListAdapter adapter;
    List<ChatListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        ButterKnife.bind(this);

        ChatListItem item = new ChatListItem("YourName@Server.com","This is where your last message is shown");
        items.add(item);
        items.add(item);
        items.add(item);
        items.add(item);
        items.add(item);

        adapter = new ChatListAdapter(items);
        recyclerChatList.setAdapter(adapter);
        recyclerChatList.setHasFixedSize(true);
        recyclerChatList.setLayoutManager(new LinearLayoutManager(this));
    }
}
