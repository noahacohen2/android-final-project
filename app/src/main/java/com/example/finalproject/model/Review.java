package com.example.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Review implements Parcelable {
    public String docId;
    public String seat;
    public Float stars;
    public String content;
    public String userId;
    public String eventId;

    public Review(String seat, Float stars, String content, String userId) {
        this.seat = seat;
        this.stars = stars;
        this.content = content;
        this.userId = userId;
        this.docId = null;
        this.eventId = null;
    }

    public Review(String seat, Float stars, String content, String userId, String docId) {
        this(seat, stars, content, userId);
        this.docId = docId;
    }


    public Review(Parcel parcel) {
        this( parcel.readString(),parcel.readFloat(),parcel.readString(),parcel.readString());
    }

    public static Review fromJson(Map<String, Object> json, String docId) {
        String seat = (String)json.get("Seat");
        Double rateDouble = (Double)json.get("Stars");
        Float rate = rateDouble.floatValue();
        String text = (String)json.get("Content");
        String user = (String)json.get("UserId");
        String id = docId;
        return new Review(seat, rate, text, user, id);
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("Stars", getStars());
        json.put("Content", getContent());
        json.put("Seat", getSeat());
        json.put("UserId", getUserId());
        json.put("EventId", getEventId());

        return json;
    }

    public String getDocId() {
        return docId;
    }

    public String getUserId() {
        return userId;
    }

    public Float getStars() {
        return stars;
    }

    public String getSeat() {
        return seat;
    }

    public String getContent() {
        return content;
    }

    public String getEventId() { return eventId; };

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
        dest.writeString(docId);
        dest.writeString(seat);
        dest.writeFloat(stars);
        dest.writeString(content);
        dest.writeString(userId);
    }

}
