package com.example.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Musical implements Parcelable {
    public String title;
    public String description;
    public String img;

    public Musical(String title, String description){
        this.title = title;
        this.description = description;
    }

    protected Musical(Parcel in) {
        this(in.readString(), in.readString());
    }

    public static final Creator<Musical> CREATOR = new Creator<Musical>() {
        @Override
        public Musical createFromParcel(Parcel in) {
            return new Musical(in);
        }

        @Override
        public Musical[] newArray(int size) {
            return new Musical[size];
        }
    };

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(img);
    }
}
