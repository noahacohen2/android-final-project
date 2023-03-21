package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;
import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;

public class MusicalFragment extends Fragment {
    ArrayList<Review> reviewsList = new ArrayList<>();
    ReviewsListFragment reviewListFragment;

    public static MusicalFragment newInstance(String musicalId, String param2) {
        MusicalFragment fragment = new MusicalFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musical, container, false);
        Button addReviewBtn = view.findViewById(R.id.musical_addReview_btn);

        NavDirections action = MusicalFragmentDirections.actionMusicalFragmentToNewReviewFragment(null);
        addReviewBtn.setOnClickListener(Navigation.createNavigateOnClickListener(action));


        reviewListFragment = (ReviewsListFragment) getChildFragmentManager().findFragmentById(R.id.musical_fc);

        ReviewRecyclerAdapter.OnItemClickListener reviewRowOnClickListener = new ReviewRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int pos) {

            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {

            }
        };

        if (reviewListFragment != null) {
            reviewListFragment.setParameters(reviewsList, reviewRowOnClickListener);
        }

        Model.instance().getAllReviews((reviewsData) -> {
            reviewsList = reviewsData;
            if (reviewListFragment != null) {
                reviewListFragment.setParameters(reviewsList, reviewRowOnClickListener);
            }
        });

        return view;
    }
}