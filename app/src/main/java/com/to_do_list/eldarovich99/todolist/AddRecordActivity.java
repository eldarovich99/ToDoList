package com.to_do_list.eldarovich99.todolist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.to_do_list.eldarovich99.todolist.records.ExtendedRecord;
import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;
import com.to_do_list.eldarovich99.todolist.storage.ToDoListStorage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddRecordActivity extends AppCompatActivity {
    @BindView(R.id.cancel_button) ImageButton mCancelButton;
    @BindView(R.id.add_photo_button) ImageButton mAddPhotoButton;
    @BindView(R.id.apply_button) ImageButton mApplyButton;
    @BindView(R.id.title_edit_text) EditText mTitleEditText;
    @BindView(R.id.note_edit_text) EditText mNoteEditText;
    @BindView(R.id.imageview) ImageView mImageView;
    private Uri mImageUri;
    static final int GALLERY_REQUEST = 1;
    static final int PHOTO_REQUEST = 2;
    private SimpleRecord mRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        ButterKnife.bind(this);
        setOnClickListeners();
    }
    private void setOnClickListeners() {
        mCancelButton.setOnClickListener(v -> {
            Intent intent = getIntent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });
        mAddPhotoButton.setOnClickListener(v -> {
            Intent galleryPickerIntent = new Intent(Intent.ACTION_PICK);
            galleryPickerIntent.setType("image/*");
            startActivityForResult(galleryPickerIntent, GALLERY_REQUEST);
        });
        mApplyButton.setOnClickListener(v -> {
            if (mImageUri == null) {
                mRecord = new SimpleRecord(mTitleEditText.getText().toString(),
                        mNoteEditText.getText().toString());
                ToDoListStorage.get(getApplicationContext()).addRecord(mRecord);
            } else {
                mRecord = new ExtendedRecord(mTitleEditText.getText().toString(), mNoteEditText.getText().toString(), mImageUri);
                ToDoListStorage.get(getApplicationContext()).addRecord(mRecord);
            }

            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case GALLERY_REQUEST:
                mImageUri = data.getData();
                mImageView.setImageURI(mImageUri);
                break;
            case PHOTO_REQUEST:
                Uri photoUri = data.getData();
                mImageView.setImageURI(photoUri);
                break;
        }
    }
}
