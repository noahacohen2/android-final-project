package com.example.finalproject.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String mail;
    public String bio;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public User(String password, String mail, String firstName,
                String lastName, String username, String bio) {
        this(mail, password);
        setExtraData(firstName, lastName, username, bio);
    }

    public void setExtraData(String firstName,
                        String lastName, String username, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.bio = bio;
    }

    public static User fromJson(Map<String, Object> json) {
        String password = (String)json.get("Password");
        String mail = (String)json.get("Mail");
        String firstname = (String)json.get("Firstname");
        String lastname = (String)json.get("Lastname");
        String username = (String)json.get("Username");
        String bio = (String)json.get("Bio");

        return new User(password, mail, firstname, lastname, username, bio);
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("Password", this.getPassword());
        json.put("Mail", this.getMail());
        json.put("Firstname", this.getFirstName());
        json.put("Lastname", this.getLastName());
        json.put("Username", this.getUsername());
        json.put("Bio", this.getBio());
        return json;
    }

    public String getUsername(){
        return  this.username;
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
}
