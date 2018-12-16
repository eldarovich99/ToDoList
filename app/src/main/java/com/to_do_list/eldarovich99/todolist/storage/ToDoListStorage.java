package com.to_do_list.eldarovich99.todolist.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.to_do_list.eldarovich99.todolist.records.ExtendedRecord;
import com.to_do_list.eldarovich99.todolist.records.ItemType;
import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToDoListStorage {
    private static ToDoListStorage toDoListStorage;
    private static SQLiteDatabase mDatabase;

   private ToDoListStorage(Context context){
       mDatabase = new DBHelper(context).getWritableDatabase();
   }

    public static ToDoListStorage get(Context context){
        if (toDoListStorage == null){
            toDoListStorage = new ToDoListStorage(context);
        }
        return toDoListStorage;
    }

    private static ContentValues getContentValues(SimpleRecord record){         // this is polymorphism
       ContentValues values = new ContentValues();
       values.put(DbScheme.ToDoListTable.Columns.UUID, record.getID().toString());
       values.put(DbScheme.ToDoListTable.Columns.DATE, record.getDate().getTime());
       values.put(DbScheme.ToDoListTable.Columns.SOLVED, record.getSolved());
       values.put(DbScheme.ToDoListTable.Columns.TITLE, record.getTitle());
       values.put(DbScheme.ToDoListTable.Columns.TEXT,record.getText());
       if (record.defineElement()==ItemType.EXTENDED)values.put(DbScheme.ToDoListTable.Columns.URI, ((ExtendedRecord)record).getPhotoUri().toString());
       return values;
    }

    private static RecordCursorWrapper queryRecords(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(DbScheme.ToDoListTable.NAME,
                null, whereClause, whereArgs, null, null, null);
        return new RecordCursorWrapper(cursor   );
    }

    public void updateRecord(SimpleRecord record){
       String id = record.getID().toString();
       ContentValues values = getContentValues(record);
       mDatabase.update(DbScheme.ToDoListTable.NAME, values, DbScheme.ToDoListTable.Columns.UUID + " = ?", new String[]{id});
    }

    public void addRecord(SimpleRecord record){
        mDatabase.insert(DbScheme.ToDoListTable.NAME, null, getContentValues(record));
    }

    public static List<SimpleRecord> getRecords(){
       List<SimpleRecord> records = new ArrayList<>();
       RecordCursorWrapper cursorWrapper = queryRecords(null, null);
       try{
           cursorWrapper.moveToFirst();
           while (!cursorWrapper.isAfterLast()) {
               records.add(cursorWrapper.getRecord());
               cursorWrapper.moveToNext();
           }
       }
       finally {
           cursorWrapper.close();
       }
       return records;
    }

    public static void deleteRecord(String id){
       String whereClause = DbScheme.ToDoListTable.Columns.UUID + "=?";
       mDatabase.delete(DbScheme.ToDoListTable.NAME, whereClause, new String[] { id });
    }

    public static SimpleRecord getRecord(UUID id){
        RecordCursorWrapper cursorWrapper = queryRecords(null, null);
        try{
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                SimpleRecord record = cursorWrapper.getRecord();
                UUID uuid = record.getID();

                if (record.getID().compareTo(id)==0)
                    return cursorWrapper.getRecord();
                cursorWrapper.moveToNext();
            }
        }
        finally {
            cursorWrapper.close();
        }
        return null;
    }

    public static SimpleRecord getLastRecord(){
        RecordCursorWrapper cursorWrapper = queryRecords(null, null);
        try{
            cursorWrapper.moveToLast();
            return cursorWrapper.getRecord();
        }
        finally {
            cursorWrapper.close();
        }
    }

    /*public static SimpleRecord getLastRecord(UUID id){
       return null;
    }*/
    /*public static List<SimpleRecord> getRecords(){
        SimpleRecord record = new SimpleRecord("Feed the cat", "Buy whiskas");
        List<SimpleRecord> records = new ArrayList<>(Collections.nCopies(100,record));
        return records;
    }*/
}
