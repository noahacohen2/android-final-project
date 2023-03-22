package com.example.finalproject.model;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReviewModel {

    final public static ReviewModel instance = new ReviewModel();
    private Executor executor = Executors.newSingleThreadExecutor();
    private FbReviewModel fbReviewModel = new FbReviewModel();
    AppLocalDbRepository localDb = AppLocalDb.getAppDb();

    public interface GetAllReviewsListener {
        void onComplete(ArrayList<Review> data);
    }

    public void getAllMusicalReviews(Integer eventId, GetAllReviewsListener callback) {
        fbReviewModel.getAllMusicalReviews(eventId,callback);
    }

    public interface GetUserReviewsListener {
        void onComplete(ArrayList<Review> data);
    }

    private LiveData<List<Review>> userReviewList;
    public LiveData<List<Review>> getUserReviews() {
        if(userReviewList == null){
            userReviewList = localDb.reviewDao().getUserReviews(UserModel.instance.getUserId());
            refreshAllUserReviews();
        }
        return userReviewList;
    }
    public void refreshAllUserReviews() {
        Long localLastUSerReviewUpdate = User.getLocalLastReviewUpdate();
        fbReviewModel.getUserReviewsSince(localLastUSerReviewUpdate, UserModel.instance.getUserId() ,list -> {
            executor.execute(()-> {
                Long time = localLastUSerReviewUpdate;
                for (Review rv : list) {
                    localDb.reviewDao().insertAll(rv);

                    if (time < rv.getLastUpdated()) {
                        time = rv.getLastUpdated();
                    }
                }

                User.setLocalLastReviewUpdate(time);
            });
        });

    }

    public interface AddReviewListener {
        void onComplete();
    }
    public void addReview(Review review, AddReviewListener callback) {
        fbReviewModel.addReview(review, () -> {
            refreshAllUserReviews();
            callback.onComplete();
        });
    }

    public interface UpdateReviewListener {
        void onComplete();
    }
    public void updateReview(Review review, UpdateReviewListener callback) {
        fbReviewModel.updateReview(review, callback);
    }
}
