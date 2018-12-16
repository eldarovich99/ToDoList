package com.to_do_list.eldarovich99.todolist.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo_list.db";
    private static final int VERSION = 1;
    DBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DbScheme.ToDoListTable.NAME
                + "(" + "_id integer primary key autoincrement, "
                + DbScheme.ToDoListTable.Columns.UUID
                + ", " + DbScheme.ToDoListTable.Columns.DATE
                + ", " + DbScheme.ToDoListTable.Columns.TEXT
                + ", " + DbScheme.ToDoListTable.Columns.TITLE
                + ", " + DbScheme.ToDoListTable.Columns.URI
                + ", " + DbScheme.ToDoListTable.Columns.SOLVED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
