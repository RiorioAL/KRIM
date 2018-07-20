package com.example.rionaldo.xmppchatfirst.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rionaldo.xmppchatfirst.R;

public class MessageVH extends RecyclerView.ViewHolder {

    private TextView tvMessage;
    private ImageView ivAvatar;


    public MessageVH(View itemView) {
        super(itemView);
        tvMessage = itemView.findViewById(R.id.tv_message);
        ivAvatar = itemView.findViewById(R.id.iv_avatar);
    }

    public void addMessage(String message){
        tvMessage.setText(message);
    }
}
