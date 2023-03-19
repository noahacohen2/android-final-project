package com.example.finalproject.model;

public class User {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String mail;
    public String bio;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName,
                String lastName, String mail, String bio) {
        this(username, password);
        setExtraData(firstName, lastName, mail, bio);
    }

    public void setExtraData(String firstName,
                        String lastName, String mail, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
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
