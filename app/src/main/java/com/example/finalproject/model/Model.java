package com.example.finalproject.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

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

    // Musicals
    public interface GetAllMusicalsListener {
        void onComplete(ArrayList<Musical> data);
    }

//    // todo: set to void and get real data and add calback param
//    public List<Musical> getAllMusicals() {
//        List<Musical> temp = new ArrayList<>();
//        temp.add(new Musical("Frozen", "lalalallala", "2 hours", "lalalalalalalalalalalalalalalalala", "from 20$", "NY"));
//        temp.add(new Musical("Lion King", "lbllbblblblbl", "2 hours", "lalalalalalalalalalalalalalalalala", "from 20$", "NY"));
//
//        return temp;
//    }

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

    // Images
    public interface UploadImageListener{
        void onComplete(String uri);
    }
    public void uploadImage(String name, Bitmap bitmap, UploadImageListener callback) {
        fbImgModel.uploadImage(name, bitmap, callback);
    }
}