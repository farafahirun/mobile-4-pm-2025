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

public class FeedanAdapter  extends RecyclerView.Adapter<FeedanAdapter.FeedViewHolder>{
    private List<Integer> recycle_highlight;
    private Context context;

    public FeedanAdapter(List<Integer> feedan, Context context) {
        this.recycle_highlight = feedan;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedanAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.highlight_item, parent, false);
        return new FeedanAdapter.FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedanAdapter.FeedViewHolder holder, int position) {
        int resId = recycle_highlight.get(position);
        holder.foto_highlight.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return recycle_highlight.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView foto_highlight;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            foto_highlight = itemView.findViewById(R.id.foto_highlight);
        }
    }
}
