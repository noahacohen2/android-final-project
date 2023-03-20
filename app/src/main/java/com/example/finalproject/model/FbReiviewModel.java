package com.example.finalproject.model;

import android.util.Log;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FbReiviewModel {
    FirebaseFirestore db;

    FbReiviewModel() {
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false).build();
        db.setFirestoreSettings(settings);
    }

    public void getAllReviews(Model.GetAllStudentsListener callback) {
        Log.d("noa", "getAllReviews");
        db.collection("reviews").get().addOnCompleteListener((task) -> {
            ArrayList<Review> list = new ArrayList<>();
            if(task.isSuccessful()) {
                QuerySnapshot jsonsList = task.getResult();
                for (DocumentSnapshot json: jsonsList) {
                    Review rv = Review.fromJson(json.getData());
                    list.add(rv);
                }
            }
            callback.onComplete(list);
        });
    }

    public void addReview(Review review, Model.AddReviewListener listener) {
        db.collection("reviews").add(review.toJson()).addOnCompleteListener((task) -> {
            listener.onComplete();
        });
    }
    // set - מחפש אם קיים id ומעדכן אותו אחרת יותר חדש

}
