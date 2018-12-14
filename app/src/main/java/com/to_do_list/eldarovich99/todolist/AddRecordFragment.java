package com.to_do_list.eldarovich99.todolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.BindView;

public class AddRecordFragment extends Fragment{
    public static AddRecordFragment newInstance() {

        //Bundle args = new Bundle();

        AddRecordFragment fragment = new AddRecordFragment();
        //fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_record_layout, container, false);
        return view;
    }

}
