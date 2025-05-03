package com.example.tp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.DetailFeedActivity;
import com.example.tp3.R;

import java.util.List;

public class PostfeedAdapter extends RecyclerView.Adapter<PostfeedAdapter.ViewHolder> {

    private Context context;
    private List<Integer> postfeedList;

    public PostfeedAdapter(Context context, List<Integer> postfeedList) {
        this.context = context;
        this.postfeedList = postfeedList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.postfeed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.postfeed.setImageResource(postfeedList.get(position));

        holder.itemView.setOnClickListener(v -> {
            // Membuat Intent untuk membuka DetailFeedActivity
            Intent intent = new Intent(context, DetailFeedActivity.class);

            // Mengirim data ke DetailFeedActivity menggunakan putExtra
            intent.putExtra("postfeedList", postfeedList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postfeedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView postfeed;

        public ViewHolder(View itemView) {
            super(itemView);
            postfeed = itemView.findViewById(R.id.postfeed);
        }
    }
}