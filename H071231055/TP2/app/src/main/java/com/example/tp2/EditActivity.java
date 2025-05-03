package com.example.tp2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    private static final int PICK_PROFILE_IMAGE = 1;
    private static final int PICK_COVER_IMAGE = 2;

    private EditText editName, editBio, editLocation, editWebsite, editBirthDate;
    private ImageView editFoto, editSampul;
    private Button btnSave;
    private Uri profileImageUri, coverImageUri;
    private Calendar calendar;

    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });

        editFoto = findViewById(R.id.edit_foto);
        editSampul = findViewById(R.id.edit_sampul);
        editName = findViewById(R.id.edit_name);
        editBio = findViewById(R.id.edit_bio);
        editLocation = findViewById(R.id.edit_location);
        editWebsite = findViewById(R.id.edit_website);
        editBirthDate = findViewById(R.id.edit_birth_date);
        btnSave = findViewById(R.id.btn_save);

        calendar = Calendar.getInstance();

        // Ambil data jika pernah diedit sebelumnya
        Intent dataIntent = getIntent();
        editName.setText(dataIntent.getStringExtra("name"));
        editBio.setText(dataIntent.getStringExtra("bio"));
        editLocation.setText(dataIntent.getStringExtra("location"));
        editWebsite.setText(dataIntent.getStringExtra("website"));
        editBirthDate.setText(dataIntent.getStringExtra("birthDate"));

        String profilePath = dataIntent.getStringExtra("profileImagePath");
        String coverPath = dataIntent.getStringExtra("coverImagePath");

        if (profilePath != null && new File(profilePath).exists()) {
            profileImageUri = Uri.fromFile(new File(profilePath));
            editFoto.setImageURI(profileImageUri);
        } else {
            editFoto.setImageResource(R.drawable.fotoprofil);
        }

        if (coverPath != null && new File(coverPath).exists()) {
            coverImageUri = Uri.fromFile(new File(coverPath));
            editSampul.setImageURI(coverImageUri);
        } else {
            editSampul.setImageResource(R.drawable.sampul);
        }

        editFoto.setOnClickListener(v -> pickImage(PICK_PROFILE_IMAGE));
        editSampul.setOnClickListener(v -> pickImage(PICK_COVER_IMAGE));
        editBirthDate.setOnClickListener(v -> showDatePicker());

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            if (name.isEmpty()) {
                editName.setError("Name is required");
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("bio", editBio.getText().toString().trim());
            intent.putExtra("location", editLocation.getText().toString().trim());
            intent.putExtra("website", editWebsite.getText().toString().trim());
            intent.putExtra("birthDate", editBirthDate.getText().toString().trim());

            if (profileImageUri != null) {
                intent.putExtra("profileImagePath", profileImageUri.getPath());
            }
            if (coverImageUri != null) {
                intent.putExtra("coverImagePath", coverImageUri.getPath());
            }

            setResult(RESULT_OK, intent);
            finish();
        });

        editName.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.toString().trim().isEmpty());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });
    }

    private void pickImage(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    private void showDatePicker() {
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            editBirthDate.setText(sdf.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            String imagePath = saveImageToInternalStorage(selectedImageUri);

            if (requestCode == PICK_PROFILE_IMAGE) {
                profileImageUri = Uri.fromFile(new File(imagePath));
                editFoto.setImageURI(profileImageUri);
            } else if (requestCode == PICK_COVER_IMAGE) {
                coverImageUri = Uri.fromFile(new File(imagePath));
                editSampul.setImageURI(coverImageUri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String saveImageToInternalStorage(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            File file = new File(getFilesDir(), System.currentTimeMillis() + ".jpg");
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
