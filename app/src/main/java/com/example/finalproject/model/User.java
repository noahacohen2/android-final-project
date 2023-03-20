package com.example.finalproject.model;

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
