package com.example.tp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ProfilAdapter extends RecyclerView.Adapter<ProfilAdapter.ViewHolder> {
    private List<Profil> profileList;

    public ProfilAdapter(List<Profil> profileList) {
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profil profile = profileList.get(position);
        holder.tvUsername.setText(profile.getUsername());
        holder.tvHandle.setText(profile.getHandle());
        holder.tvBio.setText(profile.getBio());
        holder.imgProfile.setImageResource(profile.getProfileImage());
        holder.imgHeader.setImageResource(profile.getHeaderImage());
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvHandle, tvBio;
        ImageView imgProfile, imgHeader;
        MaterialButton btnFollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvHandle = itemView.findViewById(R.id.tv_nickname);
            tvBio = itemView.findViewById(R.id.tv_bio);
            imgProfile = itemView.findViewById(R.id.foto_profil);
            imgHeader = itemView.findViewById(R.id.sampul_profil);
        }
    }
}
