package com.example.tp3;

import static com.example.tp3.Data.DataPost.uploadList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.Adapter.FeedAdapter;
import com.example.tp3.Adapter.UploadAdapter;
import com.example.tp3.Data.DataFeed;
import com.example.tp3.Models.Upload;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout tab_beranda, tab_post;
    RecyclerView tampil_feed, feed;
    private UploadAdapter uploadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tampil_feed = findViewById(R.id.tampil_feed);
        uploadAdapter = new UploadAdapter(this, uploadList);

        tampil_feed.setLayoutManager(new GridLayoutManager(this, 3));
        tampil_feed.setAdapter(uploadAdapter);

        Upload upload = getIntent().getParcelableExtra("upload");
        if (upload != null) {
            uploadList.add(upload);
            uploadAdapter.notifyDataSetChanged();
        }

        feed = findViewById(R.id.feed);
        feed.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        FeedAdapter feedAdapter = new FeedAdapter(ProfileActivity.this, DataFeed.feeds);
        feed.setAdapter(feedAdapter);

        tab_beranda = findViewById(R.id.beranda);
        tab_beranda.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        tab_post = findViewById(R.id.post);
        tab_post.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, UploadActivity.class);
            startActivity(intent);
        });
    }
}
