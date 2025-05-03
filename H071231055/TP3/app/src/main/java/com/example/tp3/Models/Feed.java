package com.example.tp3.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Feed implements Parcelable {
    int highlight;
    String username_highlight;
    private List<Integer> feedan;

    public List<Integer> getFeedan() {
        return feedan;
    }

    public void setFeedan(List<Integer> feedan) {
        this.feedan = feedan;
    }

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public String getUsername_highlight() {
        return username_highlight;
    }

    public void setUsername_highlight(String username_highlight) {
        this.username_highlight = username_highlight;
    }

    public Feed(int highlight, String username_highlight, List<Integer> feedan) {
        this.highlight = highlight;
        this.username_highlight = username_highlight;
        this.feedan = feedan;
    }

    protected Feed(Parcel in) {
        highlight = in.readInt();
        username_highlight = in.readString();
        feedan = in.readArrayList(Integer.class.getClassLoader());
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(highlight);
        dest.writeString(username_highlight);
        dest.writeList(feedan);
    }
}