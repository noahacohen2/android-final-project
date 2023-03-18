package com.example.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Review implements Parcelable {
    public String seat;
    public Float rate;
    public String text;
    public String user;

    public Review(String seat, Float rate, String text, String user) {
        this.seat = seat;
        this.rate = rate;
        this.text = text;
        this.user = user;
    }

    public Review(Parcel parcel) {
        this( parcel.readString(),parcel.readFloat(),parcel.readString(),parcel.readString());
    }

    public static Review fromJson(Map<String, Object> json) {
        String seat = (String)json.get("Seat");
        Float rate = (Float)json.get("Stars");
        String text = (String)json.get("Content");
        String user = (String)json.get("UserId");

        return new Review(seat, rate, text, user);
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("Stars", getRate());
        json.put("Content", getText());
        json.put("Seat", getSeat());
        json.put("UserId", getUser());
        json.put("EventId", "2");

        return json;
    }

    public String getUser() {
        return user;
    }

    public Float getRate() {
        return rate;
    }

    public String getSeat() {
        return seat;
    }

    public String getText() {
        return text;
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seat);
        dest.writeFloat(rate);
        dest.writeString(text);
        dest.writeString(user);
    }

}
