package com.example.finalproject.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class FbUserModel {
    CollectionReference usersCollection;



    FbUserModel() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false).build();
        db.setFirestoreSettings(settings);
        usersCollection  = db.collection("users");
    }

    public void createUser(User user, Model.AddUserListener listener) {
        usersCollection.add(user.toJson()).addOnCompleteListener((task) -> {
            listener.onComplete();
        });
    }
}
