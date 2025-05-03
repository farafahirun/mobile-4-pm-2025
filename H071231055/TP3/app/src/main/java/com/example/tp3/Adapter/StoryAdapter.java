package com.example.tp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp3.HighlightActivity2;
import com.example.tp3.Models.Story;
import com.example.tp3.R;
import com.example.tp3.StoryActivity;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private ArrayList<Story> stories;
    private Context context;

    public StoryAdapter(Context context, ArrayList<Story> stories) {
        this.stories = stories;
        this.context = context;
    }

    @NonNull
    @Override
    public StoryAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new StoryAdapter.StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.highlight.setImageResource(story.getHighlight());
        holder.username_highlight.setText(story.getUsername_highlight());

        holder.highlight.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), StoryActivity.class);
            intent.putExtra("story", story);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.username_highlight.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), StoryActivity.class);
            intent.putExtra("story", story);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        private TextView username_highlight;
        private ImageView highlight;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            highlight = itemView.findViewById(R.id.highlight);
            username_highlight = itemView.findViewById(R.id.username_highlight);
        }
    }
}