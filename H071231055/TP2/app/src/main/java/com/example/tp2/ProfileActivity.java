package com.example.tp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT_PROFILE = 100;

    private TextView tvUsername, tvBio, tvLocation, tvWebsite, tvBirthDate, tvJoining;
    private ImageView imgProfile, imgCover;
    private MaterialButton btn_edit;
    private RecyclerView recyclerView;
    private ProfilAdapter adapter;
    private List<Profil> profileList;
    ImageView kembali;

    private String name = "", bio = "", location = "", website = "", birthDate = "", profileImagePath = null, coverImagePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvLocation = findViewById(R.id.tvLocation);
        tvWebsite = findViewById(R.id.tvWebsite);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvJoining = findViewById(R.id.tvJoining);
        imgProfile = findViewById(R.id.fotoprofil);
        imgCover = findViewById(R.id.sampul);
        btn_edit = findViewById(R.id.btn_edit);

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });

        btn_edit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("bio", bio);
            intent.putExtra("location", location);
            intent.putExtra("website", website);
            intent.putExtra("birthDate", birthDate);
            intent.putExtra("profileImagePath", profileImagePath);
            intent.putExtra("coverImagePath", coverImagePath);
            startActivityForResult(intent, REQUEST_EDIT_PROFILE);
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        profileList = new ArrayList<>();
        profileList.add(new Profil("TAEYANG", "@Realtaeyang", "TAEYANG Official twitter Channel", R.drawable.taeyang, R.drawable.sampul_taeyang));
        profileList.add(new Profil("Bill Gates", "@BillGates", "Sharing things I'm learning through my foundation work and other interests.", R.drawable.billgates, R.drawable.sampul_billgates));
        profileList.add(new Profil("MrBeast", "@MrBeast", "X Super Official CEO", R.drawable.mrbeast, R.drawable.sampul_mrbeast));
        profileList.add(new Profil("BIGBANG GLOBAL VIP", "@YG_GlobalVIP", "YG ENTERTAINMENT BIGBANG OFFICIAL V.I.P", R.drawable.bigbang, R.drawable.sampul_bigbang));
        adapter = new ProfilAdapter(profileList);
        recyclerView.setAdapter(adapter);

        tvJoining.setText("Joined April 2023");
        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {
            name = data.getStringExtra("name");
            bio = data.getStringExtra("bio");
            location = data.getStringExtra("location");
            website = data.getStringExtra("website");
            birthDate = data.getStringExtra("birthDate");
            profileImagePath = data.getStringExtra("profileImagePath");
            coverImagePath = data.getStringExtra("coverImagePath");

            updateUI();
        }
    }

    private void updateUI() {
        tvUsername.setText(name.isEmpty() ? "No Name" : name);
        tvBio.setText(bio);
        tvBio.setVisibility(bio.isEmpty() ? View.GONE : View.VISIBLE);
        tvLocation.setText(location);
        tvWebsite.setText(website);
        tvBirthDate.setText(birthDate);

        LinearLayout layoutLocation = findViewById(R.id.layoutLocation);
        LinearLayout layoutWebsite = findViewById(R.id.layoutWebsite);
        LinearLayout layoutBirthDate = findViewById(R.id.layoutBirthDate);
        layoutLocation.setVisibility(location.isEmpty() ? View.GONE : View.VISIBLE);
        layoutWebsite.setVisibility(website.isEmpty() ? View.GONE : View.VISIBLE);
        layoutBirthDate.setVisibility(birthDate.isEmpty() ? View.GONE : View.VISIBLE);

        if (profileImagePath != null && new File(profileImagePath).exists()) {
            imgProfile.setImageURI(Uri.fromFile(new File(profileImagePath)));
        } else {
            imgProfile.setImageResource(R.drawable.fotoprofil);
        }

        if (coverImagePath != null && new File(coverImagePath).exists()) {
            imgCover.setImageURI(Uri.fromFile(new File(coverImagePath)));
        } else {
            imgCover.setImageResource(R.drawable.sampul);
        }
    }
}