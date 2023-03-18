package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReviewsListFragment extends Fragment {
    List<Review> data;
    ReviewRecyclerAdapter adapter;

    public void setParameters(ArrayList<Review> reviews) {
        data = reviews;
        Log.d("noa", "data change");
        if(reviews.size() > 0) {
            Log.d("noa", reviews.get(0).getSeat());
        }
        if(adapter != null) {
            adapter.setData(reviews);
            adapter.notifyDataSetChanged();
        }
    }

    public static ReviewsListFragment newInstance(ArrayList<Review> data) {
        ReviewsListFragment fragment = new ReviewsListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("reviews", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_reviews_list, container, false);
        RecyclerView list = view.findViewById(R.id.reviewlistfrag_list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setHasFixedSize(true);

        Bundle args = getArguments();
        if (args != null) {
            setParameters(args.getParcelableArrayList("reviews"));
        }

//        data = Model.instance().getAllReviews();
        adapter = new ReviewRecyclerAdapter(getLayoutInflater(),data);
        list.setAdapter(adapter);

        return view;
    }
}