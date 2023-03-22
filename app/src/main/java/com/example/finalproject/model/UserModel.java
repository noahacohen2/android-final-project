package com.example.finalproject.model;

public class UserModel {
    public String userId;
    private FbUserModel fbUserModel = new FbUserModel();
    final public static UserModel instance = new UserModel();

    public interface GetUserDataListener {
        void onComplete(User user);
    }

    public interface AddUserListener {
        void onComplete();
    }

    public interface UpdateUserListener {
        void onComplete();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void getUserData(GetUserDataListener callback) {
        fbUserModel.getUserData(userId, callback);
    }

    public void createUser(User user, AddUserListener callback) {
        fbUserModel.createUser(user, callback);
    }

    public void updateUser(User user, UpdateUserListener callback) {
        fbUserModel.updateUser(user, callback);
    }
}
