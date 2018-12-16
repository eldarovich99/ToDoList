package com.to_do_list.eldarovich99.todolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.to_do_list.eldarovich99.todolist.records.ExtendedRecord;
import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SIMPLE = 1;
    private static final int TYPE_EXTENDED = 2;
    private View.OnClickListener mOnClickListener;

    private List<SimpleRecord> mRecords;
    MainAdapter(List<SimpleRecord> records, View.OnClickListener onClickListener) {
        mRecords = records;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType == TYPE_SIMPLE){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.record_simple, viewGroup, false);
            view.setOnClickListener(mOnClickListener);
            return new MainSimpleViewHolder(view);
        }
        else if(viewType == TYPE_EXTENDED){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.record_extended, viewGroup, false);
            view.setOnClickListener(mOnClickListener);
            return new MainExtendedViewHolder(view);
        }
        else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (mRecords.get(position).defineElement()){
            case SIMPLE:
                return TYPE_SIMPLE;
            case EXTENDED:
                return TYPE_EXTENDED;
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()){
            case TYPE_SIMPLE:
                initSimpleLayout((MainSimpleViewHolder) viewHolder, position);
                break;
            case TYPE_EXTENDED:
                initExpandedLayout((MainExtendedViewHolder) viewHolder, position);
                break;
            default:
                break;
        }
    }

    private void initSimpleLayout(MainSimpleViewHolder viewHolder, int position){
        String date = new SimpleDateFormat("hh:mm   dd.MM.yyyy",
                new Locale("ru", "RU")).format( mRecords.get(position).getDate());
        viewHolder.mDate.setText(date);
        viewHolder.mTitle.setText(mRecords.get(position).getTitle());
    }

    private void initExpandedLayout(MainExtendedViewHolder viewHolder, int position){
        ExtendedRecord record = (ExtendedRecord) mRecords.get(position);
        String date = new SimpleDateFormat("hh:mm   dd.MM.yyyy",
                new Locale("ru", "RU")).format( record.getDate());
        viewHolder.mDate.setText(date);
        viewHolder.mTitle.setText(record.getTitle());
        Glide.with(viewHolder.itemView).load(record.getPhotoUri()).into(viewHolder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return mRecords == null ? 0 : mRecords.size();
    }

    class MainSimpleViewHolder extends RecyclerView.ViewHolder{
        final TextView mDate;
        final TextView mTitle;
        MainSimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.date_simple_text_view);
            mTitle = itemView.findViewById(R.id.title_simple_text_view);
        }
    }
    class MainExtendedViewHolder extends RecyclerView.ViewHolder{
        TextView mDate;
        TextView mTitle;
        ImageView mPhoto;
        MainExtendedViewHolder(@NonNull View itemView) {
            super(itemView);
            mDate = itemView.findViewById(R.id.date_extended_text_view);
            mTitle = itemView.findViewById(R.id.title_extended_text_view);
            mPhoto = itemView.findViewById(R.id.photo_image_view);
        }
    }
}
