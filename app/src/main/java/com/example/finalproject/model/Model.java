package com.example.finalproject.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Model {
    private static final Model _instance = new Model();
    private FbReviewModel fbReviewModel = new FbReviewModel();
    private FbUserModel fbUserModel = new FbUserModel();
    private  FbImgModel fbImgModel = new FbImgModel();

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
    public void createUser(User user, AddUserListener callback) {
        fbUserModel.createUser(user, callback);
    }
     // End User

    // Images
    public interface UploadImageListener{
        void onComplete(String uri);
    }
    public void uploadImage(String name, Bitmap bitmap, UploadImageListener callback) {
        fbImgModel.uploadImage(name, bitmap, callback);
    }
}