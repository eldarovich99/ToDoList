package com.to_do_list.eldarovich99.todolist.records;

import java.util.Date;
import java.util.UUID;

public  class SimpleRecord {
    private UUID mID;
    private String mTitle;
    private String mText;
    private Date mDate;
    private Boolean mIsSolved = false;

    public UUID getID() {
        return mID;
    }


    public SimpleRecord(String title, String text) {        //constructor for generating records while you add a record
        mID = UUID.randomUUID();
        mDate = new Date();
        mTitle = title;
        mText = text;
    }

    public SimpleRecord(UUID ID, String title, String text, Date date, Boolean isSolved) {  //constructor for getting records from datebase
        mID = ID;
        mTitle = title;
        mText = text;
        mDate = date;
        mIsSolved = isSolved;
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
