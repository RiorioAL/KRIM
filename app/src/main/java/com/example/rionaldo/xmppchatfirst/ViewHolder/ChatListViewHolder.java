package com.example.rionaldo.xmppchatfirst.ViewHolder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rionaldo.xmppchatfirst.ChatListActivity;
import com.example.rionaldo.xmppchatfirst.ChatViewActivity;
import com.example.rionaldo.xmppchatfirst.R;

public class ChatListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvJid;
    private TextView tvLastMessage;
    private LinearLayout llCell;

    public ChatListViewHolder(final View itemView) {
        super(itemView);
        tvJid = itemView.findViewById(R.id.tv_jid);
        tvLastMessage = itemView.findViewById(R.id.tv_last_message);
        llCell = itemView.findViewById(R.id.ll_cell);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), ChatViewActivity.class);
                (itemView.getContext()).startActivity(intent);
            }
        });
    }

    public void setData(String jid, String lastMessage){
        tvJid.setText(jid);
        tvLastMessage.setText(lastMessage);
    }
}
