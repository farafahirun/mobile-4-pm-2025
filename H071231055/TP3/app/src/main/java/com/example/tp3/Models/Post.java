package com.example.tp3.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Post implements Parcelable {
    int foto_profil;
    private List<Integer> postingan, postfeedList, highlightList;
    String username, jumlah_suka, jumlah_komen, jumlah_share, caption, jumlah_postingan, jumlah_pengikut, jumlah_mengikuti, nickname, bio;

    protected Post(Parcel in) {
        foto_profil = in.readInt();
        postingan = in.readArrayList(Integer.class.getClassLoader());
        username = in.readString();
        jumlah_suka = in.readString();
        jumlah_komen = in.readString();
        jumlah_share = in.readString();
        caption = in.readString();
        jumlah_postingan = in.readString();
        jumlah_pengikut = in.readString();
        jumlah_mengikuti = in.readString();
        nickname = in.readString();
        bio = in.readString();
        postfeedList = in.readArrayList(Integer.class.getClassLoader());
        highlightList = in.readArrayList(Integer.class.getClassLoader());
    }

    public Post(int foto_profil,
                List<Integer> postingan,
                String username,
                String jumlah_suka,
                String jumlah_komen,
                String jumlah_share,
                String caption,
                String jumlah_postingan,
                String jumlah_pengikut,
                String jumlah_mengikuti,
                String nickname,
                String bio,
                List<Integer> postfeedList,
                List<Integer> highlightList) {
        this.foto_profil = foto_profil;
        this.postingan = postingan;
        this.username = username;
        this.jumlah_suka = jumlah_suka;
        this.jumlah_komen = jumlah_komen;
        this.jumlah_share = jumlah_share;
        this.caption = caption;
        this.jumlah_postingan = jumlah_postingan;
        this.jumlah_pengikut = jumlah_pengikut;
        this.jumlah_mengikuti = jumlah_mengikuti;
        this.nickname = nickname;
        this.bio = bio;
        this.postfeedList = postfeedList;
        this.highlightList = highlightList;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(foto_profil);
        dest.writeList(postingan);
        dest.writeString(username);
        dest.writeString(jumlah_suka);
        dest.writeString(jumlah_komen);
        dest.writeString(jumlah_share);
        dest.writeString(caption);
        dest.writeString(jumlah_postingan);
        dest.writeString(jumlah_pengikut);
        dest.writeString(jumlah_mengikuti);
        dest.writeString(nickname);
        dest.writeString(bio);
        dest.writeList(postfeedList);
        dest.writeList(highlightList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getFoto_profil() {
        return foto_profil;
    }

    public void setFoto_profil(int foto_profil) {
        this.foto_profil = foto_profil;
    }

    public List<Integer> getPostingan() {
        return postingan;
    }

    public void setPostingan(List<Integer> postingan) {
        this.postingan = postingan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJumlah_suka() {
        return jumlah_suka;
    }

    public void setJumlah_suka(String jumlah_suka) {
        this.jumlah_suka = jumlah_suka;
    }

    public String getJumlah_komen() {
        return jumlah_komen;
    }

    public void setJumlah_komen(String jumlah_komen) {
        this.jumlah_komen = jumlah_komen;
    }

    public String getJumlah_share() {
        return jumlah_share;
    }

    public void setJumlah_share(String jumlah_share) {
        this.jumlah_share = jumlah_share;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getJumlah_postingan() {
        return jumlah_postingan;
    }

    public void setJumlah_postingan(String jumlah_postingan) {
        this.jumlah_postingan = jumlah_postingan;
    }

    public String getJumlah_pengikut() {
        return jumlah_pengikut;
    }

    public void setJumlah_pengikut(String jumlah_pengikut) {
        this.jumlah_pengikut = jumlah_pengikut;
    }

    public String getJumlah_mengikuti() {
        return jumlah_mengikuti;
    }

    public void setJumlah_mengikuti(String jumlah_mengikuti) {
        this.jumlah_mengikuti = jumlah_mengikuti;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Integer> getPostfeedList() {
        return postfeedList;
    }

    public void setPostfeedList(List<Integer> postfeedList) {
        this.postfeedList = postfeedList;
    }

    public List<Integer> getHighlightList() {
        return highlightList;
    }

    public void setHighlightList(List<Integer> highlightList) {
        this.highlightList = highlightList;
    }
}