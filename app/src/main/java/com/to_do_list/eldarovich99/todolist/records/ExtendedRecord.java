package com.to_do_list.eldarovich99.todolist.records;

import android.net.Uri;

import java.util.Date;
import java.util.UUID;

public class ExtendedRecord extends SimpleRecord {
    private Uri mPhoto;

    public ExtendedRecord(String title, String text, Uri photo) {
        super(title, text);
        mPhoto = photo;
    }

    public ExtendedRecord(UUID ID, String title, String text, Date date, Boolean isSolved, Uri photo) {
        super(ID, title, text, date, isSolved);
        mPhoto = photo;
    }

    public Uri getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Uri photo) {
        mPhoto = photo;
    }

    @Override
    public ItemType defineElement() {
        return ItemType.EXTENDED;
    }
}
