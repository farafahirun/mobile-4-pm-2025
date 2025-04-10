package com.example.tp2;

public class Profil {
    private String username, handle, bio;
    private int profileImage, headerImage;

    public Profil(String username, String handle, String bio, int profileImage, int headerImage) {
        this.username = username;
        this.handle = handle;
        this.bio = bio;
        this.profileImage = profileImage;
        this.headerImage = headerImage;
    }

    public String getUsername() {
        return username;
    }

    public String getHandle() {
        return handle;
    }

    public String getBio() {
        return bio;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public int getHeaderImage() {
        return headerImage;
    }
}
