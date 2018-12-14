package com.to_do_list.eldarovich99.todolist.records;

public  class SimpleRecord {
    String mTitle;
    String mText;
    String mDate;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public SimpleRecord(String title, String text, String date) {

        mTitle = title;
        mText = text;
        mDate = date;
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

    public ItemType defineElement(){
        return ItemType.SIMPLE;
    }
}
