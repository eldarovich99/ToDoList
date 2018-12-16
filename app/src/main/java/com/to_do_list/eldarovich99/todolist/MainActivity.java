package com.to_do_list.eldarovich99.todolist;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;

import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;
import com.to_do_list.eldarovich99.todolist.storage.ToDoListStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_RECORD = 3;
    public static final String EDIT_RECORD_ID = "edit";
    public static final String EDIT_RECORD_POSITION = "position";
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

        View.OnClickListener onClickListener = v->{
            Intent intent = new Intent(this, AddRecordActivity.class);
            int position = mRecyclerView.getChildLayoutPosition(v);
            intent.putExtra(EDIT_RECORD_ID,mRecords.get(position).getID().toString());     //intent.putExtra(EDIT_RECORD_ID,mRecords.get(position).getID().toString());
            intent.putExtra(EDIT_RECORD_POSITION, position);
            startActivityForResult(intent, ADD_RECORD);
        };

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final int position = viewHolder.getAdapterPosition();
                String id = mRecords.get(position).getID().toString();
                ToDoListStorage.deleteRecord(id);
                mAdapter.notifyItemRemoved(position);
                mRecords.remove(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mAdapter = new MainAdapter(mRecords,onClickListener);
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
                    int position = data.getIntExtra(EDIT_RECORD_POSITION,-1);
                    if (position!=-1){
                        mRecords.set(position, ToDoListStorage.getRecord(mRecords.get(position).getID()));
                        mAdapter.notifyItemChanged(position);
                    }
                    else{
                        mRecords.add(ToDoListStorage.getLastRecord());
                        mAdapter.notifyItemInserted(mRecords.size()-1);
                    }
                }
        }
    }
}
