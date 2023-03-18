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
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.bio = bio;
    }

    public void setExtraData(String firstName,
                        String lastName, String mail, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.bio = bio;
    }
}
