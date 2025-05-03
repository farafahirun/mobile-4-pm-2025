package com.example.tp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.DetailFeedActivity;
import com.example.tp3.HighlightActivity2;
import com.example.tp3.R;
import java.util.List;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {

    private Context context;
    private List<Integer> highlightList;

    public HighlightAdapter(Context context, List<Integer> highlightList) {
        this.context = context;
        this.highlightList = highlightList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.highlight.setImageResource(highlightList.get(position));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, HighlightActivity2.class);
            intent.putExtra("highlightList", highlightList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView highlight;

        public ViewHolder(View itemView) {
            super(itemView);
            highlight = itemView.findViewById(R.id.highlight);
        }
    }
}
