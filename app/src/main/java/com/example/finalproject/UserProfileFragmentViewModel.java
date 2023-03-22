package com.example.finalproject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;

import java.util.List;

public class UserProfileFragmentViewModel extends ViewModel {
    private LiveData<List<Review>> reviewList = Model.instance().getUserReviews();

    LiveData<List<Review>> getReviewListData(){
        return reviewList;
    }
}
