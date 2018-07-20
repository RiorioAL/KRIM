package com.example.rionaldo.xmppchatfirst.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rionaldo.xmppchatfirst.Entity.MessageItem;
import com.example.rionaldo.xmppchatfirst.R;
import com.example.rionaldo.xmppchatfirst.ViewHolder.MessageVH;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageVH> {

    List<MessageItem> messageItems;

    public MessageAdapter(List<MessageItem> messageItems) {
        this.messageItems = messageItems;
    }

    @NonNull
    @Override
    public MessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_receive,parent,false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_sent,parent,false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_receive,parent,false);
                break;
        }
        return new MessageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageVH holder, int position) {
        MessageItem messageItem = messageItems.get(position);
        if (null != messageItem.getMessage())
            holder.addMessage(messageItem.getMessage());

    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messageItems.get(position).getType();
    }

    public void addItems(MessageItem item){
        messageItems.add(0,item);
        notifyItemInserted(0);
    }
}
