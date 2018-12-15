package com.to_do_list.eldarovich99.todolist.records;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public class ExtendedRecord extends SimpleRecord {
    private URI mPhoto;

    public ExtendedRecord(String title, String text, URI photo) {
        super(title, text);
        mPhoto = photo;
    }

    public ExtendedRecord(UUID ID, String title, String text, Date date, Boolean isSolved, URI photo) {
        super(ID, title, text, date, isSolved);
        mPhoto = photo;
    }

    public URI getPhoto() {
        return mPhoto;
    }

    public void setPhoto(URI photo) {
        mPhoto = photo;
    }

    @Override
    public ItemType defineElement() {
        return ItemType.EXTENDED;
    }
}
