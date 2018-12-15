package com.to_do_list.eldarovich99.todolist;

import android.content.Context;

import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ToDoListStorage {
    private static ToDoListStorage toDoListStorage;
    private Context mContext;

   ToDoListStorage(Context context){
       mContext = context;
   }

    public static ToDoListStorage get(Context context){
        if (toDoListStorage == null){
            toDoListStorage = new ToDoListStorage(context);
        }
        return toDoListStorage;
    }

    public static List<SimpleRecord> getRecords(){
        SimpleRecord record = new SimpleRecord(UUID.randomUUID(),"Feed the cat", "Buy whiskas", "14.12.2018");
        List<SimpleRecord> records = new ArrayList<>(Collections.nCopies(100,record));
        return records;
    }
    private void AddRecord(){

    }
}
