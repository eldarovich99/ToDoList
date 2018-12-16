package com.to_do_list.eldarovich99.todolist;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;
import com.to_do_list.eldarovich99.todolist.storage.ToDoListStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_RECORD = 3;
    @BindView(R.id.recycler) RecyclerView mRecyclerView;
    @BindView(R.id.add_image_button) ImageButton mAddButton;
    private MainAdapter mAdapter;
    List<SimpleRecord> mRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ToDoListStorage.get(getApplicationContext());
        mRecords = ToDoListStorage.getRecords();

        mAdapter = new MainAdapter(mRecords);
        mRecyclerView.setAdapter(mAdapter);
        mAddButton.setOnClickListener(v->{
            Intent intent = new Intent(this, AddRecordActivity.class);
            startActivityForResult(intent, ADD_RECORD);});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case ADD_RECORD:
                if (resultCode==RESULT_OK){
                    mRecords.add(ToDoListStorage.getLastRecord());
                    mAdapter.notifyItemInserted(mRecords.size()-1);
                }
        }
    }
}
