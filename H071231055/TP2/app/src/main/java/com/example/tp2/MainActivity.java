package com.example.tp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    PostAdapter adapter;
    List<Post> postList;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_menu);
        NavigationView navigationView = findViewById(R.id.nav_menu);
        View headerView = navigationView.getHeaderView(0);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);

        f2 = findViewById(R.id.f2);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

        ImageView profileImage = headerView.findViewById(R.id.fotoprofil);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        TextView profileName = headerView.findViewById(R.id.username_head);
        profileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        TextView profileNn = headerView.findViewById(R.id.head_nn);
        profileNn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList = new ArrayList<>();
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Apr 23",
                "PEACEMINUSONE x NIKE 'KIIIONDO1'COMING SOON🔥 #Kwondo1 #PEACEMINUSONE #NIKE #FreedomInFlow",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "954", "7,5K", "29,1K", "1,9M"));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Apr 23",
                "[THE FIRST-EVER BMW XM]\nG-Dragon x BMW XM\n'BREAK THE E NORM'\n@BMWMotorsport",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "454", "6,4K", "23,4K", "1,7M"));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Apr 23",
                "\uD83D\uDD52Act.III, AT3:00AM\uD83D\uDD52 seoul, South Korea \ninstagram.com/p/BYBlz-vAW2A/",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "229", "2,5K", "18K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "25 Feb 18",
                "'Untitled, 2017' -2018.2.25 Jeju Shinhwa World",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "17,4K", "7,6K", "61,2kK", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "23 Nov 17",
                "#BIGBANGJAPANDOMETOUR2017 #LASTDANCE #FUKUOKA > #OSAKA instagram.com/p/Bb0c_dgg61_/",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "296", "3,8K", "23,1K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "31 Okt 17",
                "BIGBANG 2017 CONCERT <LAST DANCE> IN SEOUL\n\n(Date & Time) : 2017.12.30 (SAT) 6PM -12.31...",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "274", "3,8K", "21,3K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "03 Okt 17",
                "#CHANELSpringSummer grand Palais - RMN (Officiel)",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "148", "2,8K", "18,7K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Mei 17",
                "#My8second instagram.com/p/BTi2xROg5NE/",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "58K", "1,7K", "9,6K", ""));

        adapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        NavigationView navigationView = findViewById(R.id.nav_menu);
        View headerView = navigationView.getHeaderView(0);
        TextView profileName = headerView.findViewById(R.id.username_head);
        ImageView profileImage = headerView.findViewById(R.id.fotoprofil);

        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "No Name");
        String profileImagePath = sharedPreferences.getString("profileImage", null);

        profileName.setText(name);

        if (profileImagePath != null) {
            profileImage.setImageURI(Uri.parse(profileImagePath));
        } else {
            profileImage.setImageResource(R.drawable.fotoprofil);
        }

        ImageView imgProfile = findViewById(R.id.fotoprofil_beranda);
        if (profileImagePath != null) {
            imgProfile.setImageURI(Uri.parse(profileImagePath));
        } else {
            imgProfile.setImageResource(R.drawable.fotoprofil);
        }
    }
}