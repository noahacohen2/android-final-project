package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.finalproject.databinding.FragmentMusicalBinding;
import com.example.finalproject.model.LiveDataEvents;
import com.example.finalproject.model.Musical;
import com.example.finalproject.model.Review;
import com.example.finalproject.model.ReviewModel;

import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class MusicalFragment extends Fragment {
    ArrayList<Review> reviewsList = new ArrayList<>();
    ReviewsListFragment reviewListFragment;
    Musical currMusical;
    FragmentMusicalBinding binding;
    ReviewRecyclerAdapter.OnItemClickListener reviewRowOnClickListener;

    public static MusicalFragment newInstance(String musicalId, String param2) {
        MusicalFragment fragment = new MusicalFragment();
        return fragment;
    }

    private void setParameters(Musical musical) {
        this.currMusical = musical;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMusicalBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        NavDirections action = MusicalFragmentDirections.actionMusicalFragmentToNewReviewFragment(null);
        binding.addReviewBtn.setOnClickListener(Navigation.createNavigateOnClickListener(action));


        reviewListFragment = (ReviewsListFragment) getChildFragmentManager().findFragmentById(R.id.musicalFc);

        reviewRowOnClickListener = new ReviewRecyclerAdapter.OnItemClickListener() {

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

        reloadData();

        LiveDataEvents.instance().EventReviewListReload.observe(getViewLifecycleOwner(), unused -> {
            reloadData();
        });

        setParameters(MusicalFragmentArgs.fromBundle(getArguments()).getMusical());

        Model.instance().getAllReviews((reviewsData) -> {
            reviewsList = reviewsData;
            if (reviewListFragment != null) {
                reviewListFragment.setParameters(reviewsList, reviewRowOnClickListener);
            }
        });

        return view;
    }

    void reloadData() {
        ReviewModel.instance.getAllReviews((reviewsData) -> {
            reviewsList = reviewsData;
            if (reviewListFragment != null) {
                reviewListFragment.setParameters(reviewsList, reviewRowOnClickListener);
            }
        });

    }
}