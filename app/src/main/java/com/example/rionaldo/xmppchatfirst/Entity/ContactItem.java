package com.example.rionaldo.xmppchatfirst.Entity;

public class ContactItem {
    private String avatarLink;
    private String contactJID;
    private String contactStatus;

    public ContactItem(String avatarLink, String contactJID, String contactStatus) {
        this.avatarLink = avatarLink;
        this.contactJID = contactJID;
        this.contactStatus = contactStatus;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getContactJID() {
        return contactJID;
    }

    public void setContactJID(String contactJID) {
        this.contactJID = contactJID;
    }

    public String getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }
}
