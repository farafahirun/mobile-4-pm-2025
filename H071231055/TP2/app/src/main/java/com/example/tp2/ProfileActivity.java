package com.example.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProfilAdapter adapter;
    private List<Profil> profileList;
    private TextView tvUsername, tvBio, tvLocation, tvWebsite, tvBirthDate, tvJoining;
    private ImageView imgProfile, imgCover;
    private MaterialButton btn_edit;
    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Tombol kembali ke activity_profile
        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvLocation = findViewById(R.id.tvLocation);
        tvWebsite = findViewById(R.id.tvWebsite);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        imgProfile = findViewById(R.id.fotoprofil);
        imgCover = findViewById(R.id.sampul);
        tvJoining = findViewById(R.id.tvJoining);
        btn_edit = findViewById(R.id.btn_edit);

        loadProfileData();

        btn_edit.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditActivity.class);
            startActivity(intent);
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
    }

    private void loadProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "");
        String bio = sharedPreferences.getString("bio", "");
        String location = sharedPreferences.getString("location", "");
        String website = sharedPreferences.getString("website", "");
        String birthDate = sharedPreferences.getString("birthDate", "");

        tvUsername.setText(name.isEmpty() ? "No Name" : name);
        tvBio.setText(bio);
        tvBio.setVisibility(bio.isEmpty() ? View.GONE : View.VISIBLE);
        tvLocation.setText(location);
        tvWebsite.setText(website);
        tvBirthDate.setText(birthDate);
        tvJoining.setText("Joined April 2023");

        LinearLayout layoutLocation = findViewById(R.id.layoutLocation);
        LinearLayout layoutWebsite = findViewById(R.id.layoutWebsite);
        LinearLayout layoutBirthDate = findViewById(R.id.layoutBirthDate);

        layoutLocation.setVisibility(location.isEmpty() ? View.GONE : View.VISIBLE);
        layoutWebsite.setVisibility(website.isEmpty() ? View.GONE : View.VISIBLE);
        layoutBirthDate.setVisibility(birthDate.isEmpty() ? View.GONE : View.VISIBLE);

        String profileImagePath = sharedPreferences.getString("profileImage", null);
        if (profileImagePath != null && new File(profileImagePath).exists()) {
            imgProfile.setImageURI(Uri.fromFile(new File(profileImagePath)));
        } else {
            imgProfile.setImageResource(R.drawable.fotoprofil);
        }

        String coverImagePath = sharedPreferences.getString("coverImage", null);
        if (coverImagePath != null && new File(coverImagePath).exists()) {
            imgCover.setImageURI(Uri.fromFile(new File(coverImagePath)));
        } else {
            imgCover.setImageResource(R.drawable.sampul);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfileData();
    }
}