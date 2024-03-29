package com.example.finalproject.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.finalproject.FinalProjectApplication;

import java.util.HashMap;
import java.util.Map;

public class User implements Parcelable {

    static final String LOCAL_LAST_UPDATED = "user_review_local_last_update";

    public String password;
    public String firstName;
    public String lastName;
    public String mail;
    public String bio;
    public String uid;
    public String imgUrl;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public User(String password, String mail, String firstName,
                String lastName, String bio) {
        this(mail, password);
        setExtraData(firstName, lastName, bio);
    }

    public User(String password, String mail, String firstName,
                String lastName, String bio, String uid, String imgUrl) {
        this(password, mail, firstName, lastName, bio);
        this.uid = uid;
        this.imgUrl = imgUrl;
    }


    public void setExtraData(String firstName,
                             String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public static User fromJson(Map<String, Object> json) {
        String password = (String)json.get("Password");
        String mail = (String)json.get("Mail");
        String firstname = (String)json.get("Firstname");
        String lastname = (String)json.get("Lastname");
        String bio = (String)json.get("Bio");
        String uid = (String)json.get("Uid");
        String imgUrl = (String)json.get("ImgUrl");

        return new User(password, mail, firstname, lastname, bio, uid, imgUrl);
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("Password", this.getPassword());
        json.put("Mail", this.getMail());
        json.put("Firstname", this.getFirstName());
        json.put("Lastname", this.getLastName());
        json.put("Bio", this.getBio());
        json.put("Uid", this.getUid());
        json.put("ImgUrl", this.getImgUrl());

        return json;
    }

    public String getPassword(){
        return  this.password;
    }

    public String getFirstName(){
        return  this.firstName;
    }

    public String getLastName(){
        return  this.lastName;
    }

    public String getMail(){
        return  this.mail;
    }

    public String getBio(){
        return  this.bio;
    }

    public String getUid() {
        return uid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static  Long getLocalLastReviewUpdate() {
        SharedPreferences sharedPref = FinalProjectApplication.getMyContext().getSharedPreferences("lastUserReviewUpdate", Context.MODE_PRIVATE);
        return sharedPref.getLong(LOCAL_LAST_UPDATED, 0);
    }

    public static void setLocalLastReviewUpdate(Long time) {
        SharedPreferences sharedPref = FinalProjectApplication.getMyContext().getSharedPreferences("lastUserReviewUpdate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(LOCAL_LAST_UPDATED,time);
        editor.commit();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(password);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(mail);
        parcel.writeString(bio);
        parcel.writeString(uid);
        parcel.writeString(imgUrl);

    }

    protected User(Parcel in) {
        this( in.readString(),in.readString(),in.readString(),in.readString(),in.readString(),in.readString(),in.readString());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}