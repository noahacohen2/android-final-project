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
    public String imgUrl;

    public Review(String seat, Float stars, String content, String userId,String docId,String eventId) {
        this.seat = seat;
        this.stars = stars;
        this.content = content;
        this.userId = userId;
        this.docId = docId;
        this.eventId = eventId;
    }

    public Review(String seat, Float stars, String content, String userId, String docId,String eventId, String ImageUrl) {
        this(seat,stars,content,userId,docId,eventId);
        setImgUrl(ImageUrl);
    }


    public Review(Parcel parcel) {
        // seat, stars,content,userId,eventId,docId,imgUrl
        this( parcel.readString(),parcel.readFloat(),parcel.readString(),parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
    }

    public static Review fromJson(Map<String, Object> json, String docId) {
        String seat = (String)json.get("Seat");
        Double rateDouble = (Double)json.get("Stars");
        Float rate = rateDouble.floatValue();
        String text = (String)json.get("Content");
        String user = (String)json.get("UserId");
        String id = docId;
        String eventId = (String)json.get("EventId");
        String ImageUrl = (String)json.get("ImageUrl");
        return new Review(seat, rate, text, user, id, eventId, ImageUrl);
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("Stars", getStars());
        json.put("Content", getContent());
        json.put("Seat", getSeat());
        json.put("UserId", getUserId());
        json.put("EventId", getEventId());
        json.put("ImageUrl", getImgUrl());

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

    public String getImgUrl() { return imgUrl; }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        dest.writeString(docId);
        dest.writeString(seat);
        dest.writeFloat(stars);
        dest.writeString(content);
        dest.writeString(userId);
        dest.writeString(imgUrl);
    }

}
