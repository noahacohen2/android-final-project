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
//        for (int i = 0; i < 20; i++) {
//            addReview(new Review("name " + i, 4F, "noa", String.valueOf(i)));
//        }
    }

//    ArrayList<Review> data = new ArrayList<>();

    public interface GetAllStudentsListener {
        void onComplete(ArrayList<Review> data);
    }
    public void getAllReviews(GetAllStudentsListener callback) {
        firebaseModel.getAllReviews(callback);
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
        firebaseModel.addReview(review, ()->{
        Log.d("noa", "add!");
        });
    }
}