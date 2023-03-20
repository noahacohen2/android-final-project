package com.example.finalproject.model;

import java.util.ArrayList;

public class Model {
    private static final Model _instance = new Model();
    private FbReiviewModel fbReviewModel = new FbReiviewModel();
    private FbUserModel fbUserModel = new FbUserModel();

    public static Model instance() {
        return _instance;
    }

    private Model() {
    }

    // Reviews
    public interface GetAllReviewsListener {
        void onComplete(ArrayList<Review> data);
    }

    public void getAllReviews(GetAllReviewsListener callback) {
        fbReviewModel.getAllReviews(callback);
    }

    public interface GetUserReviewsListener {
        void onComplete(ArrayList<Review> data);
    }
    public void getUserReviews(String user, GetUserReviewsListener callback) {
        fbReviewModel.getUserReviews(user,callback);
    }

    public interface AddReviewListener {
        void onComplete();
    }

    public void addReview(Review review, AddReviewListener callback) {
        fbReviewModel.addReview(review, callback);
    }

    public interface UpdateReviewListener {
        void onComplete();
    }
    public void updateReview(Review review, UpdateReviewListener callback) {
        fbReviewModel.updateReview(review, callback);
    }

    // End reviews

    // User
    public interface AddUserListener {
        void onComplete();
    }

    public interface UpdateUserListener {
        void onComplete();
    }

    public void createUser(User user, AddUserListener callback) {
        fbUserModel.createUser(user, callback);
    }

    public void updateUser(User user, UpdateUserListener callback) {
        fbUserModel.updateUser(user, callback);
    }
     // End User
}