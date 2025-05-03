package com.example.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.Adapter.FeedAdapter;
import com.example.tp3.Adapter.PostAdapter;
import com.example.tp3.Adapter.StoryAdapter;
import com.example.tp3.Data.DataFeed;
import com.example.tp3.Data.DataPost;
import com.example.tp3.Data.DataStory;

public class MainActivity extends AppCompatActivity {
    LinearLayout tab_profile, tab_post;
    RecyclerView post_recycle, story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        post_recycle = findViewById(R.id.post_recycle);
        post_recycle.setLayoutManager(new LinearLayoutManager(this));
        PostAdapter adapter = new PostAdapter(this, DataPost.posts);
        post_recycle.setAdapter(adapter);

        story = findViewById(R.id.story);
        story.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        StoryAdapter storyAdapter = new StoryAdapter(MainActivity.this, DataStory.stories);
        story.setAdapter(storyAdapter);

        tab_profile = findViewById(R.id.profile);
        tab_profile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });

        tab_post = findViewById(R.id.post);
        tab_post.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UploadActivity.class);
            startActivity(intent);
        });
    }
}