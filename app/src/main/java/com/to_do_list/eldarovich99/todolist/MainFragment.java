package com.to_do_list.eldarovich99.todolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;
import com.to_do_list.eldarovich99.todolist.storage.ToDoListStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment {
    @BindView(R.id.recycler) RecyclerView mRecyclerView;
    @BindView(R.id.add_image_button) ImageButton mAddButton;

    public static MainFragment newInstance() {
        //Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //SimpleRecord record = new SimpleRecord(UUID.randomUUID(),"Feed the cat", "Buy whiskas", "14.12.2018");
        List<SimpleRecord> records = ToDoListStorage.getRecords();

        mRecyclerView.setAdapter(new MainAdapter(records));
        mAddButton.setOnClickListener(v->{
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, AddRecordFragment.newInstance())
                    .addToBackStack(null)
                    .commit();});
    }

}
