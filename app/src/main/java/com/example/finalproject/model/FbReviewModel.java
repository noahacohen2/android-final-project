package com.example.finalproject.model;

import android.util.Log;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class FbReviewModel {
    FirebaseFirestore db;

    FbReviewModel() {
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false).build();
        db.setFirestoreSettings(settings);
    }

    public void getAllReviews(ReviewModel.GetAllReviewsListener callback) {
        db.collection("reviews").get().addOnCompleteListener((task) -> {
            ArrayList<Review> list = new ArrayList<>();
            if(task.isSuccessful()) {
                QuerySnapshot jsonsList = task.getResult();
                for (DocumentSnapshot json: jsonsList) {

                    Review rv = Review.fromJson(json.getData(), json.getId());
                    list.add(rv);
                }
            }
            callback.onComplete(list);
        });
    }

    public void getUserReviewsSince(Long since, String user, ReviewModel.GetUserReviewsListener callback) {
        db.collection("reviews").whereEqualTo("UserId", user).whereGreaterThanOrEqualTo("lastUpdated", new Timestamp(since,0)).get().addOnCompleteListener((task) -> {
            ArrayList<Review> list = new ArrayList<>();
            if(task.isSuccessful()) {
                QuerySnapshot jsonsList = task.getResult();
                for (DocumentSnapshot json: jsonsList) {
                    Review rv = Review.fromJson(json.getData(), json.getId());
                    list.add(rv);
                }
            }
            callback.onComplete(list);
        });
    }

    public void addReview(Review review, ReviewModel.AddReviewListener listener) {
        db.collection("reviews").document(review.getDocId()).set(review.toJson())
                .addOnCompleteListener((task) -> {

                    listener.onComplete();
                });
    }

    public void updateReview(Review review, ReviewModel.UpdateReviewListener listener) {
        db.collection("reviews").document(review.getDocId()).update(review.toJson())
                .addOnCompleteListener(task ->{
            listener.onComplete();
        });
    }

}
