package com.example.finalproject.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static final Model _instance = new Model();
    private  FirebaseModel firebaseModel = new FirebaseModel();

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
        firebaseModel.getAllReviews(callback);
    }

    public interface GetUserReviewsListener {
        void onComplete(ArrayList<Review> data);
    }
    public void getUserReviews(String user, GetUserReviewsListener callback) {
        firebaseModel.getUserReviews(user,callback);
    }

    public interface AddReviewListener {
        void onComplete();
    }
    public void addReview(Review review, AddReviewListener callback) {
        firebaseModel.addReview(review, callback);
    }

    public interface UpdateReviewListener {
        void onComplete();
    }
    public void updateReview(Review review, UpdateReviewListener callback) {
        firebaseModel.updateReview(review, callback);
    }
    //End reviews
}