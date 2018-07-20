package com.example.rionaldo.xmppchatfirst.Entity;

import android.text.format.DateFormat;

import java.util.concurrent.TimeUnit;

public class MessageItem {

    private String message;
    private long timestamp;
    private int type;
    private String contactJID;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContactJID() {
        return contactJID;
    }

    public void setContactJID(String contactJID) {
        this.contactJID = contactJID;
    }

    public String getFormattedString(){
        long oneDayInMillis = TimeUnit.DAYS.toMillis(1);

        long timeDifference = System.currentTimeMillis() - timestamp;

        return timeDifference < oneDayInMillis
                ? DateFormat.format("hh:mm a",timestamp).toString()
                : DateFormat.format("dd MMM - hh:mm a",timestamp).toString();
    }
}
