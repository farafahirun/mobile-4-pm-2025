package com.example.tp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tp3.AccountActivity;
import com.example.tp3.Models.Post;
import com.example.tp3.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<Post> posts;
    private Context context;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.foto_profil.setImageResource(post.getFoto_profil());

        List<Integer> postingan = post.getPostingan();
        PostinganAdapter postinganAdapter = new PostinganAdapter(context, postingan);
        holder.postingan.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.postingan.setAdapter(postinganAdapter);

        // Snap helper agar selalu berhenti tepat di gambar
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(holder.postingan);

        // Dots hanya muncul jika lebih dari 1 gambar
        if (postingan.size() <= 1) {
            holder.dots_container.setVisibility(View.GONE);
        } else {
            holder.dots_container.setVisibility(View.VISIBLE);
            holder.dots_container.removeAllViews();

            for (int i = 0; i < postingan.size(); i++) {
                ImageView dot = new ImageView(context);
                dot.setImageResource(R.drawable.dot_off);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(8, 0, 8, 0);
                dot.setLayoutParams(params);
                holder.dots_container.addView(dot);
            }

            // Update saat di-scroll
            holder.postingan.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int current = layoutManager.findFirstVisibleItemPosition();

                    for (int i = 0; i < holder.dots_container.getChildCount(); i++) {
                        ImageView dot = (ImageView) holder.dots_container.getChildAt(i);
                        dot.setImageResource(i == current ? R.drawable.dot_on : R.drawable.dot_off);
                    }
                }
            });
        }

        holder.username.setText(post.getUsername());
        holder.jumlah_suka.setText(post.getJumlah_suka());
        holder.jumlah_komen.setText(post.getJumlah_komen());
        holder.jumlah_share.setText(post.getJumlah_share());
        holder.caption.setText(post.getCaption());
        holder.caption.setMaxLines(2);
        holder.caption.setEllipsize(TextUtils.TruncateAt.END);
        holder.caption.setOnClickListener(new View.OnClickListener() {
            boolean isExpanded = false;

            @Override
            public void onClick(View v) {
                if (!isExpanded) {
                    holder.caption.setMaxLines(Integer.MAX_VALUE);
                    holder.caption.setEllipsize(null);
                    isExpanded = true;
                }
            }
        });

        holder.username.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), AccountActivity.class);
            intent.putExtra("post", post);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.foto_profil.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), AccountActivity.class);
            intent.putExtra("post", post);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView foto_profil;
        private RecyclerView postingan, highlight;
        LinearLayout dots_container;
        private TextView username, jumlah_suka, jumlah_komen, jumlah_share, caption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foto_profil = itemView.findViewById(R.id.foto_profil);
            postingan = itemView.findViewById(R.id.postingan);
            dots_container = itemView.findViewById(R.id.dots_container);
            username = itemView.findViewById(R.id.username);
            jumlah_suka = itemView.findViewById(R.id.jumlah_suka);
            jumlah_komen = itemView.findViewById(R.id.jumlah_komen);
            jumlah_share = itemView.findViewById(R.id.jumlah_share);
            caption = itemView.findViewById(R.id.caption);
            highlight = itemView.findViewById(R.id.feed_user);
        }
    }
}