package com.example.tp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp3.Models.Upload;
import com.google.android.material.button.MaterialButton;

public class UploadActivity extends AppCompatActivity {
    ImageView kembali;
    private ImageView inputFoto;
    private EditText editBio;
    private MaterialButton buttonBagikan;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputFoto = findViewById(R.id.input_foto);
        editBio = findViewById(R.id.input_caption);
        buttonBagikan = findViewById(R.id.button_bagikan);
        buttonBagikan.setEnabled(false);
        buttonBagikan.setOnClickListener(v -> {
            String caption = editBio.getText().toString();
            Upload upload = new Upload(imageUri, caption);
            Intent intent = new Intent(UploadActivity.this, ProfileActivity.class);
            intent.putExtra("upload", upload);
            startActivity(intent);
        });

        inputFoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        });

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            inputFoto.setImageURI(imageUri);
            buttonBagikan.setEnabled(true);
        }
    }
}
