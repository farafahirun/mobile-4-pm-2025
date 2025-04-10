package com.example.tp2;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {
    private static final int PICK_PROFILE_IMAGE = 1;
    private static final int PICK_COVER_IMAGE = 2;
    private static final int REQUEST_STORAGE_PERMISSION = 100;
    private EditText editName, editBio, editLocation, editWebsite, editBirthDate;
    private Button btnSave;
    private ImageView editFoto, editSampul;
    private Uri profileImageUri, coverImageUri;

    Calendar calendar;
    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

//        Tombol kembali ke activity_profile
        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editFoto = findViewById(R.id.edit_foto);
        editSampul = findViewById(R.id.edit_sampul);
        editName = findViewById(R.id.edit_name);
        editBio = findViewById(R.id.edit_bio);
        editLocation = findViewById(R.id.edit_location);
        editWebsite = findViewById(R.id.edit_website);
        editBirthDate = findViewById(R.id.edit_birth_date);
        calendar = Calendar.getInstance();
        btnSave = findViewById(R.id.btn_save);
        editBirthDate.setOnClickListener(v -> showDatePicker());

        loadProfileData();

        editFoto.setOnClickListener(v -> checkPermissionAndPickImage(PICK_PROFILE_IMAGE));
        editSampul.setOnClickListener(v -> checkPermissionAndPickImage(PICK_COVER_IMAGE));
        btnSave.setOnClickListener(v -> saveProfileData());

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    //    Setel tanggal yang dipilih
    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    calendar.set(selectedYear, selectedMonth, selectedDay);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                    String selectedDate = sdf.format(calendar.getTime());

                    editBirthDate.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    //    Periksa izin agar bisa memilih gambar dari galeri
    private void checkPermissionAndPickImage(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_STORAGE_PERMISSION);
            } else {
                pickImage(requestCode);
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
            } else {
                pickImage(requestCode);
            }
        }
    }

    //    Membuka galeri
    private void pickImage(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    //    Proses hasil pemilihan gambar di galeri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
    }

    //    Menyimpan gambar ke penyimpanan internal
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

    //    Memuat data yang tersimpan di SharedPreferences
    private void loadProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        editName.setText(sharedPreferences.getString("name", ""));
        editBio.setText(sharedPreferences.getString("bio", ""));
        editLocation.setText(sharedPreferences.getString("location", ""));
        editWebsite.setText(sharedPreferences.getString("website", ""));
        editBirthDate.setText(sharedPreferences.getString("birthDate", ""));

        String profileImagePath = sharedPreferences.getString("profileImage", null);
        if (profileImagePath != null) {
            editFoto.setImageURI(Uri.fromFile(new File(profileImagePath)));
        } else {
            editFoto.setImageResource(R.drawable.fotoprofil);
        }

        String coverImagePath = sharedPreferences.getString("coverImage", null);
        if (coverImagePath != null) {
            editSampul.setImageURI(Uri.fromFile(new File(coverImagePath)));
        } else {
            editSampul.setImageResource(R.drawable.sampul);
        }
    }

    //    Menangani hasil permintaan izin akses penyimpanan
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Izin diberikan! Coba lagi.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Izin ditolak!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //    Menyimpan data ke SharedPreferences.
    private void saveProfileData() {
        String name = editName.getText().toString().trim();

        if (name.isEmpty()) {
            editName.setError("Name is required");
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("bio", editBio.getText().toString().trim());
        editor.putString("location", editLocation.getText().toString().trim());
        editor.putString("website", editWebsite.getText().toString().trim());
        editor.putString("birthDate", editBirthDate.getText().toString().trim());
        if (profileImageUri != null) {
            editor.putString("profileImage", profileImageUri.getPath());
        }
        if (coverImageUri != null) {
            editor.putString("coverImage", coverImageUri.getPath());
        }

        editor.apply();
        Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}