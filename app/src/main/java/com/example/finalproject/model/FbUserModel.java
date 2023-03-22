package com.example.finalproject.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

    public void getUserData(String userId, UserModel.GetUserDataListener listener) {
        usersCollection.document(userId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    User user = User.fromJson(task.getResult().getData());
                    listener.onComplete(user);
                }
            }
        });
    }

    public void createUser(User user, UserModel.AddUserListener listener) {
        usersCollection.document(user.getUid()).set(user.toJson()).addOnCompleteListener((task) -> {
            listener.onComplete();
        });
    }

    public void updateUser(User user, UserModel.UpdateUserListener listener) {
        usersCollection.document(user.getUid()).update(user.toJson()).addOnCompleteListener((task) -> {
            listener.onComplete();
        });
    }
}
