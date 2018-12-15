package com.to_do_list.eldarovich99.todolist.records;

import java.net.URI;

public class ExtendedRecord extends SimpleRecord {
    URI mPhoto;

    public ExtendedRecord(String title, String text, String date, URI photo) {
        super(title, text, date);
        mPhoto = photo;
    }

    public URI getPhoto() {
        return mPhoto;
    }

    public void setPhoto(URI photo) {
        mPhoto = photo;
    }

    @Override
    public ItemType DefineElement() {
        return ItemType.EXTENDED;
    }
}
