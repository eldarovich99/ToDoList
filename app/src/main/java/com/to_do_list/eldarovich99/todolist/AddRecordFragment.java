package com.to_do_list.eldarovich99.todolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;
import com.to_do_list.eldarovich99.todolist.storage.ToDoListStorage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddRecordFragment extends Fragment{
    @BindView(R.id.cancel_button) ImageButton mCancelButton;
    @BindView(R.id.add_photo_button) ImageButton mAddPhotoButton;
    @BindView(R.id.apply_button) ImageButton mApplyButton;
    @BindView(R.id.imageview) ImageView mImageView;
    static final int GALLERY_REQUEST = 1;
    static final int PHOTO_REQUEST = 2;
    SimpleRecord mRecord;
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
        ButterKnife.bind(this, view);
        setOnClickListeners();
        return view;
    }
    private void setOnClickListeners(){
        mCancelButton.setOnClickListener(v->{
        });
        mAddPhotoButton.setOnClickListener(v->{
            Intent galleryPickerIntent = new Intent(Intent.ACTION_PICK);
            galleryPickerIntent.setType("image/*");
            startActivityForResult(galleryPickerIntent, GALLERY_REQUEST);
        });
        mApplyButton.setOnClickListener(v->{
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case GALLERY_REQUEST:
                Uri imageUri = data.getData();
                mImageView.setImageURI(imageUri);
                break;
            case PHOTO_REQUEST:
                Uri photoUri = data.getData();
                mImageView.setImageURI(photoUri);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        ToDoListStorage.get(getContext()).updateRecord(mRecord);
    }
/*
    private File savePhoto() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                //".jpg",         /* suffix */
                //storageDir      /* directory */
        //);

        // Save a file: path for use with ACTION_VIEW intents
/*
    Intent photoPickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (photoPickerIntent.resolveActivity(getActivity().getPackageManager())!=null)
    startActivityForResult(photoPickerIntent,PHOTO_REQUEST);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }*/
}
