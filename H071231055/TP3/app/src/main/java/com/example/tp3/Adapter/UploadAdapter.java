package com.example.tp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.Models.Upload;
import com.example.tp3.R;

import java.util.List;

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.UploadViewHolder> {

    private Context context;
    private List<Upload> uploads;

    public UploadAdapter(Context context, List<Upload> uploads) {
        this.context = context;
        this.uploads = uploads;
    }

    @Override
    public UploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.upload_item, parent, false);
        return new UploadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UploadViewHolder holder, int position) {
        Upload upload = uploads.get(position);
        holder.imageView.setImageURI(upload.getImageUri());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailUploadActivity.class);
            intent.putExtra("upload", upload);  // Kirim objek Upload
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class UploadViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public UploadViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.input_foto);
        }
    }
}