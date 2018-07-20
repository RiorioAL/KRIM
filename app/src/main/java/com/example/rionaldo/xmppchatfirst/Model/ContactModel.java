package com.example.rionaldo.xmppchatfirst.Model;

import android.content.Context;

import com.example.rionaldo.xmppchatfirst.Entity.ContactItem;

import java.util.ArrayList;
import java.util.List;

public class ContactModel {
    private static String LOG = ContactModel.class.getSimpleName();
    private static ContactModel contactModel;
    private Context mContext;
    private List<ContactItem> itemList;

    public static ContactModel getInstance(Context context){
        if (null != contactModel)
            contactModel = null;

        contactModel = new ContactModel(context);
        return contactModel;
    }

    public ContactModel(Context mContext) {
        this.mContext = mContext;
        itemList = new ArrayList<>();
        ContactItem item = new ContactItem(null,null,null);
        itemList.add(item);
        itemList.add(item);
        itemList.add(item);
        itemList.add(item);
        itemList.add(item);
        itemList.add(item);
        itemList.add(item);
    }

    public void setData(List<ContactItem> items){
        itemList = items;
    }

    public List<ContactItem> getData(){
        return itemList;
    }
}
