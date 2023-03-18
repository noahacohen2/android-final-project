//package com.example.finalproject;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MusicalFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class MusicalFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public MusicalFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MusicalFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MusicalFragment newInstance(String param1, String param2) {
//        MusicalFragment fragment = new MusicalFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_musical, container, false);
//    }
//}

package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class MusicalFragment extends Fragment {
    ArrayList<Review> reviewsList = new ArrayList<>();

    public enum FragmentContainerState {
        NEW_REVIEW,
        ALL_REVIEWS
    }

    public static MusicalFragment newInstance(String musicalId, String param2) {
        MusicalFragment fragment = new MusicalFragment();
        return fragment;
    }

    //    List<Student> data;
    public static FragmentContainerState containerState = FragmentContainerState.ALL_REVIEWS;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musical, container, false);
        Button addReviewBtn = view.findViewById(R.id.musical_addReview_btn);

        ReviewsListFragment reviewListFragment = (ReviewsListFragment) getChildFragmentManager().findFragmentById(R.id.musical_fc);

        if (reviewListFragment != null) {
            reviewListFragment.setParameters(reviewsList);
        }

        Model.instance().getAllReviews((reviewsData) -> {
            reviewsList = reviewsData;
            Log.d("noa", "callback");
            if (reviewListFragment != null) {
                reviewListFragment.setParameters(reviewsList);
            }
        });


        addReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager childFragmentManager = getChildFragmentManager();
                FragmentTransaction transaction = childFragmentManager.beginTransaction();

                if(containerState == FragmentContainerState.ALL_REVIEWS) {
                    transaction.replace(R.id.musical_fc, new NewReviewFragment());
                    addReviewBtn.setText("Cancel");
                    containerState = FragmentContainerState.NEW_REVIEW;
                } else {
                    // Create child fragment and set arguments
                    ReviewsListFragment reviewListFragment =  ReviewsListFragment.newInstance(reviewsList);
                    transaction.replace(R.id.musical_fc,reviewListFragment);

                    addReviewBtn.setText("Add review");
                    containerState = FragmentContainerState.ALL_REVIEWS;
                }

                transaction.commit();
            }
        });
        return view;
    }
}