package com.to_do_list.eldarovich99.todolist.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.to_do_list.eldarovich99.todolist.records.ExtendedRecord;
import com.to_do_list.eldarovich99.todolist.records.ItemType;
import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ToDoListStorage {
    private static ToDoListStorage toDoListStorage;
    private Context mContext;
    private SQLiteDatabase mDatabase;

   ToDoListStorage(Context context){
       mContext = context.getApplicationContext();
       mDatabase = new DBHelper(mContext).getWritableDatabase();
   }

    public static ToDoListStorage get(Context context){
        if (toDoListStorage == null){
            toDoListStorage = new ToDoListStorage(context);
        }
        return toDoListStorage;
    }

    public static List<SimpleRecord> getRecords(){
        SimpleRecord record = new SimpleRecord("Feed the cat", "Buy whiskas");
        List<SimpleRecord> records = new ArrayList<>(Collections.nCopies(100,record));
        return records;
    }

    private static ContentValues getContentValues(SimpleRecord record){         // this is polymorphism
       ContentValues values = new ContentValues();
       values.put(DbScheme.ToDoListTable.Columns.UUID, record.getID().toString());
       values.put(DbScheme.ToDoListTable.Columns.DATE, record.getDate().getTime());
       values.put(DbScheme.ToDoListTable.Columns.SOLVED, record.getSolved());
       values.put(DbScheme.ToDoListTable.Columns.TITLE, record.getTitle());
       values.put(DbScheme.ToDoListTable.Columns.TEXT,record.getText());
       if (record.defineElement()==ItemType.EXTENDED)values.put(DbScheme.ToDoListTable.Columns.URI, ((ExtendedRecord)record).getPhoto().toString());
       return values;
    }

    private Cursor queryRecords(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(DbScheme.ToDoListTable.NAME,
                null, whereClause, whereArgs, null, null, null);
        return cursor;
    }

    public static SimpleRecord getRecord(UUID id){
       return null;
    }

    public void updateRecord(SimpleRecord record){
       String id = record.getID().toString();
       ContentValues values = getContentValues(record);
       mDatabase.update(DbScheme.ToDoListTable.NAME, values, DbScheme.ToDoListTable.Columns.UUID + " = ?", new String[]{id});
    }

    private void AddRecord(SimpleRecord record){
        mDatabase.insert(DbScheme.ToDoListTable.NAME, null, getContentValues(record));
    }
}
