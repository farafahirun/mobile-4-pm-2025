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

import com.example.tp3.HighlightActivity;
import com.example.tp3.Models.Feed;
import com.example.tp3.R;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private ArrayList<Feed> feeds;
    private Context context;

    public FeedAdapter(Context context, ArrayList<Feed> feeds) {
        this.feeds = feeds;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedAdapter.FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.FeedViewHolder holder, int position) {
        Feed feed = feeds.get(position);
        holder.highlight.setImageResource(feed.getHighlight());
        holder.username_highlight.setText(feed.getUsername_highlight());

        holder.highlight.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), HighlightActivity.class);
            intent.putExtra("feed", feed);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.username_highlight.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), HighlightActivity.class);
            intent.putExtra("feed", feed);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        private TextView username_highlight;
        private ImageView highlight;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            highlight = itemView.findViewById(R.id.highlight);
            username_highlight = itemView.findViewById(R.id.username_highlight);
        }
    }
}