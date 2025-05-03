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

public class StoryanAdapter  extends RecyclerView.Adapter<StoryanAdapter.StoryViewHolder>{
    private List<Integer> recycle_highlight;
    private Context context;

    public StoryanAdapter(List<Integer> storyan, Context context) {
        this.recycle_highlight = storyan;
        this.context = context;
    }

    @NonNull
    @Override
    public StoryanAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.highlight_item, parent, false);
        return new StoryanAdapter.StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryanAdapter.StoryViewHolder holder, int position) {
        int resId = recycle_highlight.get(position);
        holder.foto_highlight.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return recycle_highlight.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView foto_highlight;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            foto_highlight = itemView.findViewById(R.id.foto_highlight);
        }
    }
}