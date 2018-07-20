package com.example.rionaldo.xmppchatfirst.Entity;

public class ChatListItem {
    String bareJid;
    String lastMessage;

    public ChatListItem(String bareJid, String lastMessage) {
        this.bareJid = bareJid;
        this.lastMessage = lastMessage;
    }

    public String getBareJid() {
        return bareJid;
    }

    public void setBareJid(String bareJid) {
        this.bareJid = bareJid;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
