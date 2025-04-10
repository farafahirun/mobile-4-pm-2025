package com.example.tp2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postList;
    private Context context;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.name.setText(post.name);
        holder.username.setText(post.username);
        holder.date.setText(post.date);
        holder.profileImage.setImageResource(post.profileImage);
        holder.verifiedIcon.setImageResource(post.verifiedIcon);
        holder.replies.setText(post.replies);
        holder.retweets.setText(post.retweets);
        holder.likes.setText(post.likes);
        holder.views.setText(post.views);

        setStyledText(holder.content, post.content);

        holder.profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainAccount.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage, verifiedIcon;
        TextView name, username, date, content, replies, retweets, likes, views;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            verifiedIcon = itemView.findViewById(R.id.verifiedIcon);
            name = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);
            date = itemView.findViewById(R.id.date);
            content = itemView.findViewById(R.id.content);
            replies = itemView.findViewById(R.id.replies);
            retweets = itemView.findViewById(R.id.retweets);
            likes = itemView.findViewById(R.id.likes);
            views = itemView.findViewById(R.id.views);
        }
    }

    private void setStyledText(TextView textView, String content) {
        SpannableString spannable = new SpannableString(content);

        Pattern pattern = Pattern.compile("#\\w+|(?:https?://|www\\.|[a-zA-Z0-9.-]+\\.[a-z]{2,})\\S*");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3498db")), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(spannable);
    }
}
