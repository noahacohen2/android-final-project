package com.example.finalproject.model;

import android.util.Log;
import java.util.ArrayList;

public class Model {
    private static final Model _instance = new Model();
    private FbReiviewModel fbReviewModel = new FbReiviewModel();
    private FbUserModel fbUserModel = new FbUserModel();

    public static Model instance() {
        return _instance;
    }

    private Model() {
//        for (int i = 0; i < 20; i++) {
//            addReview(new Review("name " + i, 4F, "noa", String.valueOf(i)));
//        }
    }

//    ArrayList<Review> data = new ArrayList<>();

    public interface GetAllStudentsListener {
        void onComplete(ArrayList<Review> data);
    }
    public void getAllReviews(GetAllStudentsListener callback) {
        fbReviewModel.getAllReviews(callback);
//        return data;
    }

    ArrayList<Review> reviews = new ArrayList<>();
    public ArrayList<Review> getUserReviews(String user) {

        ArrayList<Review> result =new ArrayList<>();


        getAllReviews((data) -> {
            reviews = data;
        });

        for (Review review: reviews) {
            if (review.getUser().equals(user)) {

                result.add(review);
            }
        }
        return result;
    }

    public interface AddReviewListener {
        void onComplete();
    }
    public void addReview(Review review) {
//        data.add(review);
        fbReviewModel.addReview(review, ()->{
        Log.d("noa", "add!");
        });
    }

    public interface AddUserListener {
        void onComplete();
    }
    public void createUser(User user, AddUserListener callback) {
        fbUserModel.createUser(user, callback);
    }
}