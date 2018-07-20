package com.example.rionaldo.xmppchatfirst.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rionaldo.xmppchatfirst.Entity.ChatListItem;
import com.example.rionaldo.xmppchatfirst.R;
import com.example.rionaldo.xmppchatfirst.ViewHolder.ChatListViewHolder;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListViewHolder> {

    List<ChatListItem> items;

    public ChatListAdapter(List<ChatListItem> items) {
        this.items = items;
    }

    @Override
    public ChatListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.chat_list_cell;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new ChatListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatListViewHolder holder, int position) {
        ChatListItem item = items.get(position);
        holder.setData(item.getBareJid(),item.getLastMessage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
