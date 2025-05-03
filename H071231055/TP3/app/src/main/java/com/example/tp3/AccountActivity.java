package com.example.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.Adapter.FeedAdapter;
import com.example.tp3.Data.DataFeed;
import com.example.tp3.Data.DataPost;
import com.example.tp3.Models.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends AppCompatActivity {
    ImageView kembali;
    TextView username, jumlah_postingan, jumlah_pengikut, jumlah_mengikuti, nickname, bio;
    RecyclerView highlight, tampilFeed, highlight_user;
    LinearLayout tab_beranda, tab_profile, tab_post;
    CircleImageView foto_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> {
            finish();
        });

        tab_beranda = findViewById(R.id.beranda);
        tab_beranda.setOnClickListener(v -> {
            Intent intent = new Intent(AccountActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        tab_profile = findViewById(R.id.profile);
        tab_profile.setOnClickListener(v -> {
            Intent intent = new Intent(AccountActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });

        tab_post = findViewById(R.id.post);
        tab_post.setOnClickListener(v -> {
            Intent intent = new Intent(AccountActivity.this, UploadActivity.class);
            startActivity(intent);
        });

        username = findViewById(R.id.username);
        foto_profil = findViewById(R.id.foto_profil);
        jumlah_postingan = findViewById(R.id.jumlah_postingan);
        jumlah_pengikut = findViewById(R.id.jumlah_pengikut);
        jumlah_mengikuti = findViewById(R.id.jumlah_mengikuti);
        nickname = findViewById(R.id.nickname);
        bio = findViewById(R.id.bio);
        highlight = findViewById(R.id.feed_user);
        Post post = getIntent().getParcelableExtra("post");
        username.setText(post.getUsername());
        foto_profil.setImageResource(post.getFoto_profil());
        jumlah_postingan.setText(post.getJumlah_postingan());
        jumlah_pengikut.setText(post.getJumlah_pengikut());
        jumlah_mengikuti.setText(post.getJumlah_mengikuti());
        nickname.setText(post.getNickname());
        bio.setText(post.getBio());

        if (post != null) {
            List<Integer> highlightList = post.getHighlightList();

            highlight_user = findViewById(R.id.highlight_user);
            highlight_user.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            com.example.tp3.Adapter.HighlightAdapter adapter = new com.example.tp3.Adapter.HighlightAdapter(this, highlightList);
            highlight_user.setAdapter(adapter);
        }

        if (post != null) {
            List<Integer> postfeedList = post.getPostfeedList();

            tampilFeed = findViewById(R.id.feed_user);
            tampilFeed.setLayoutManager(new GridLayoutManager(this, 3));
            com.example.tp3.Adapter.PostfeedAdapter adapter = new com.example.tp3.Adapter.PostfeedAdapter(this, postfeedList);
            tampilFeed.setAdapter(adapter);
        }
    }
}