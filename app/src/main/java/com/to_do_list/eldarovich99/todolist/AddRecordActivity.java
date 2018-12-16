package com.to_do_list.eldarovich99.todolist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.to_do_list.eldarovich99.todolist.records.ExtendedRecord;
import com.to_do_list.eldarovich99.todolist.records.ItemType;
import com.to_do_list.eldarovich99.todolist.records.SimpleRecord;
import com.to_do_list.eldarovich99.todolist.storage.ToDoListStorage;

import java.util.UUID;

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
        Intent intent = getIntent();
        String id = intent.getStringExtra(MainActivity.EDIT_RECORD);
        ButterKnife.bind(this);
        if (id!=null){
            mRecord = ToDoListStorage.getRecord(UUID.fromString(id));
            mTitleEditText.setText(mRecord.getTitle());
            mNoteEditText.setText(mRecord.getText());
            if (mRecord.defineElement()==ItemType.EXTENDED){
                mImageUri = ((ExtendedRecord)mRecord).getPhotoUri();
                mImageView.setImageURI(mImageUri);
            }
        }
        setTitle(R.string.add_record);
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
            if (mTitleEditText.getText().toString().compareTo("")!=0) {
                if (mImageUri == null) {
                    if (mRecord==null) {
                        mRecord = new SimpleRecord(mTitleEditText.getText().toString(),
                                mNoteEditText.getText().toString());
                        ToDoListStorage.get(getApplicationContext()).addRecord(mRecord);
                    }
                    else{
                        ToDoListStorage.get(getApplicationContext()).updateRecord(mRecord);
                    }
                }
                else {
                    if (mRecord==null) {
                        mRecord = new ExtendedRecord(mTitleEditText.getText().toString(), mNoteEditText.getText().toString(), mImageUri);
                        ToDoListStorage.get(getApplicationContext()).addRecord(mRecord);
                    }
                    else{
                        ToDoListStorage.get(getApplicationContext()).updateRecord(mRecord);
                    }
                }

                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
            else{
                Toast.makeText(this, getResources().getString(R.string.no_title), Toast.LENGTH_SHORT).show();
                mTitleEditText.requestFocus();
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.showSoftInput(mTitleEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case GALLERY_REQUEST:
                if (resultCode==RESULT_OK) {
                    mImageUri = data.getData();
                    mImageView.setImageURI(mImageUri);
                    break;
                }
            case PHOTO_REQUEST:
                if (resultCode==RESULT_OK) {
                    Uri photoUri = data.getData();
                    mImageView.setImageURI(photoUri);
                    break;
                }
        }
    }
}
