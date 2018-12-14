package com.to_do_list.eldarovich99.todolist.records;

public class ExtendedRecord extends SimpleRecord {
    String mPhoto;

    public ExtendedRecord(String title, String text, String date, String photo) {
        super(title, text, date);
        mPhoto = photo;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    @Override
    public ItemType defineElement() {
        return ItemType.EXTENDED;
    }
}
