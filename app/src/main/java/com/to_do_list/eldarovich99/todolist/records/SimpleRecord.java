package com.to_do_list.eldarovich99.todolist.records;

import java.util.UUID;

public  class SimpleRecord {
    UUID mUUID;
    String mTitle;
    String mText;
    String mDate;
    Boolean mIsSolved = false;

    public SimpleRecord(UUID UUID, String title, String text, String date) {
        mUUID = UUID;
        mTitle = title;
        mText = text;
        mDate = date;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
    public ItemType DefineElement(){
        return ItemType.SIMPLE;
    }
}
