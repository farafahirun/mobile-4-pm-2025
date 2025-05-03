package com.example.tp3;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp3.Adapter.StoryanAdapter;
import com.example.tp3.Models.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryActivity extends AppCompatActivity {
    TextView username_highlight;
    ImageView highlight;
    RecyclerView recycle_highlight;
    List<ProgressBar> progressBars = new ArrayList<>();
    Handler handler = new Handler();
    Runnable storyRunnable;
    int currentStory = 0;
    private LinearLayout progressContainer;
    private ObjectAnimator currentAnimator;
    private List<Integer> gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recycle_highlight = findViewById(R.id.recycle_highlight);
        username_highlight = findViewById(R.id.username_highlight2);
        highlight = findViewById(R.id.highlight2);
        progressContainer = findViewById(R.id.progressContainer);

        Story story = getIntent().getParcelableExtra("story");
        username_highlight.setText(story.getUsername_highlight());
        highlight.setImageResource(story.getHighlight());
        gambar = story.getStoryan();

        StoryanAdapter storyanAdapter = new StoryanAdapter(gambar, this);
        recycle_highlight.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycle_highlight.setAdapter(storyanAdapter);

        for (int i = 0; i < gambar.size(); i++) {
            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
            params.setMarginEnd(8);
            progressBar.setLayoutParams(params);
            progressBar.setMax(100);
            progressBar.setProgress(0);
            progressContainer.addView(progressBar);
            progressBars.add(progressBar);
        }

        findViewById(R.id.tapRight).setOnClickListener(v -> {
            if (currentStory < gambar.size() - 1) {
                moveToStory(currentStory + 1);
            } else {
                finish();
            }
        });

        findViewById(R.id.tapLeft).setOnClickListener(v -> {
            if (currentStory > 0) {
                moveToStory(currentStory - 1);
            }
        });

        storyRunnable = () -> {
            if (currentStory < gambar.size()) {
                moveToStory(currentStory + 1);
            } else {
                finish();
            }
        };

        moveToStory(0);
    }

    private void moveToStory(int index) {
        cancelRunnableAndAnimation();

        if (index > currentStory) {
            markProgressComplete(currentStory);
        }

        if (index < currentStory) {
            for (int i = index + 1; i <= currentStory; i++) {
                resetProgress(i);
            }
        }

        currentStory = index;
        recycle_highlight.smoothScrollToPosition(currentStory);
        resetProgress(currentStory); // selalu reset story aktif
        animateProgress(progressBars.get(currentStory));
        handler.postDelayed(storyRunnable, 5000);
    }

    private void animateProgress(ProgressBar progressBar) {
        currentAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        currentAnimator.setDuration(5000);
        currentAnimator.setInterpolator(new LinearInterpolator());
        currentAnimator.start();
    }

    private void cancelRunnableAndAnimation() {
        handler.removeCallbacks(storyRunnable);
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }
    }

    private void markProgressComplete(int index) {
        if (index >= 0 && index < progressBars.size()) {
            progressBars.get(index).setProgress(100);
        }
    }

    private void resetProgress(int index) {
        if (index >= 0 && index < progressBars.size()) {
            progressBars.get(index).setProgress(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelRunnableAndAnimation();
    }
}