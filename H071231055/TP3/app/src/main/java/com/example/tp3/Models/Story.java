package com.example.tp3.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Story implements Parcelable {
    int highlight;
    String username_highlight;
    private List<Integer> storyan;

    public List<Integer> getStoryan() {
        return storyan;
    }

    public void setStoryan(List<Integer> storyan) {
        this.storyan = storyan;
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

    public Story(int highlight, String username_highlight, List<Integer> storyan) {
        this.highlight = highlight;
        this.username_highlight = username_highlight;
        this.storyan = storyan;
    }

    protected Story(Parcel in) {
        highlight = in.readInt();
        username_highlight = in.readString();
        storyan = in.readArrayList(Integer.class.getClassLoader());
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
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
        dest.writeList(storyan);
    }
}