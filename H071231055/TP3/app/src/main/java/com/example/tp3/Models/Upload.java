package com.example.tp3.Models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Upload implements Parcelable {
    private Uri imageUri;
    private String caption;

    public Upload(Uri imageUri, String caption) {
        this.imageUri = imageUri;
        this.caption = caption;
    }

    protected Upload(Parcel in) {
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        caption = in.readString();
    }

    public static final Creator<Upload> CREATOR = new Creator<Upload>() {
        @Override
        public Upload createFromParcel(Parcel in) {
            return new Upload(in);
        }

        @Override
        public Upload[] newArray(int size) {
            return new Upload[size];
        }
    };

    public Uri getImageUri() {
        return imageUri;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(imageUri, flags);
        dest.writeString(caption);
    }
}