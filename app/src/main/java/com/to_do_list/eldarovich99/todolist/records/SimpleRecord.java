package com.to_do_list.eldarovich99.todolist.records;

import java.util.Date;
import java.util.UUID;

public  class SimpleRecord {
    private UUID mID;
    private String mTitle;
    private String mText;
    private Date mDate;

    public UUID getID() {
        return mID;
    }

    private Boolean mIsSolved = false;

    public SimpleRecord(String title, String text) {
        mID = UUID.randomUUID();
        mDate = new Date();
        mTitle = title;
        mText = text;
    }

    public ItemType defineElement(){
        return ItemType.SIMPLE;
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Boolean getSolved() {
        return mIsSolved;
    }

    public void setSolved(Boolean solved) {
        mIsSolved = solved;
    }

}
