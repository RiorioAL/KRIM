package com.example.rionaldo.xmppchatfirst.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rionaldo.xmppchatfirst.Entity.ContactItem;
import com.example.rionaldo.xmppchatfirst.R;
import com.example.rionaldo.xmppchatfirst.ViewHolder.ContactVH;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactVH> {
    private List<ContactItem> items;
    private OnItemClick onItemClick;

    public interface OnItemClick{
        public void onItemClick();
    }

    public ContactAdapter(List<ContactItem> items) {
        this.items = items;
    }

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.contact_item_cell;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new ContactVH(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
