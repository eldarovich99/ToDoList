package com.to_do_list.eldarovich99.todolist.storage;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.net.URI;
import com.to_do_list.eldarovich99.todolist.records.ExtendedRecord;
import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;

import java.util.Date;
import java.util.UUID;

public class RecordCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public RecordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public SimpleRecord getRecord(){
        String id = getString(getColumnIndex(DbScheme.ToDoListTable.Columns.UUID));
        String title = getString(getColumnIndex(DbScheme.ToDoListTable.Columns.TITLE));
        String text = getString(getColumnIndex(DbScheme.ToDoListTable.Columns.TEXT));
        long date = getLong(getColumnIndex(DbScheme.ToDoListTable.Columns.DATE));
        int isSolved = getInt(getColumnIndex(DbScheme.ToDoListTable.Columns.SOLVED));
        String imageUri = getString(getColumnIndex(DbScheme.ToDoListTable.Columns.URI));
        if (imageUri!=null){
            ExtendedRecord record = new ExtendedRecord(UUID.fromString(id), title,text, URI.create(imageUri));
            record.setDate(new Date(date));
            record.setSolved(isSolved>0);
            return record;
        }
        SimpleRecord record = new SimpleRecord(UUID.fromString(id), title, text);
        record.setDate(new Date(date));
        record.setSolved(isSolved>0);
        return record;
    }
}
