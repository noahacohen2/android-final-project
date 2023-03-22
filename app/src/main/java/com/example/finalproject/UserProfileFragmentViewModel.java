package com.example.finalproject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;

import java.util.ArrayList;
import java.util.List;

public class UserProfileFragmentViewModel extends ViewModel {
    //TODO
    private LiveData<List<Review>> data = Model.instance().getUserReviews("1");

    LiveData<List<Review>> getData(){
        return data;
    }
}
