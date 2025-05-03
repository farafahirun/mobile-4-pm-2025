package com.example.tp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.R;

import java.util.List;

public class PostinganAdapter extends RecyclerView.Adapter<PostinganAdapter.ImageViewHolder> {
    private List<Integer> postingan;
    private Context context;

    public PostinganAdapter(Context context, List<Integer> postingan) {
        this.context = context;
        this.postingan = postingan;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.postingan_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int resId = postingan.get(position);
        holder.gambar_postingan.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return postingan.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar_postingan;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar_postingan = itemView.findViewById(R.id.gambar_postingan);
        }
    }
}