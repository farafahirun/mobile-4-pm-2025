package com.example.tp3.Adapter;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp3.Models.Upload;
import com.example.tp3.R;

public class DetailUploadActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView captionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_upload);

        imageView = findViewById(R.id.upload_detail);
        captionTextView = findViewById(R.id.caption_detail);

        Upload upload = getIntent().getParcelableExtra("upload");

        if (upload != null) {
            imageView.setImageURI(upload.getImageUri());
            captionTextView.setText(upload.getCaption());
        }
    }
}
