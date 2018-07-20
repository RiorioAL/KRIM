package com.example.rionaldo.xmppchatfirst.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rionaldo.xmppchatfirst.Adapter.ContactAdapter;

public class ContactVH extends RecyclerView.ViewHolder {
    private ContactAdapter contactAdapter;

    public ContactVH(View itemView, final ContactAdapter contactAdapter) {
        super(itemView);
        this.contactAdapter = contactAdapter;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactAdapter.getOnItemClick().onItemClick();
            }
        });
    }
}
