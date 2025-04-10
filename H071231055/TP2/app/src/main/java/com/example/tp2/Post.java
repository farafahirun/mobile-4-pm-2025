package com.example.tp2;

public class Post {
    String name, username, date, content;
    int profileImage, verifiedIcon, menu;
    String replies, retweets, likes, views;

    public Post(String name, String username, String date, String content,
                int profileImage, int verifiedIcon, String replies, String retweets, String likes, String views) {
        this.name = name;
        this.username = username;
        this.date = date;
        this.content = content;
        this.profileImage = profileImage;
        this.verifiedIcon = verifiedIcon;
        this.replies = replies;
        this.retweets = retweets;
        this.likes = likes;
        this.views = views;
    }
}