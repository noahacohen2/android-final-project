package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;

import java.util.ArrayList;

public class UserProfileFragment extends Fragment {
    ArrayList<Review> reviewsList;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musical, container, false);
        reviewsList = Model.instance().getUserReviews("1");

        ReviewsListFragment ReviewListFragment = (ReviewsListFragment) getChildFragmentManager().findFragmentById(R.id.musical_fc);
        if (ReviewListFragment != null) {
            ReviewListFragment.setParameters(reviewsList);
        }

        // Inflate the layout for this fragment
        return view;
    }
}