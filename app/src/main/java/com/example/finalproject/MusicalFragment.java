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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.finalproject.databinding.FragmentMusicalBinding;
import com.example.finalproject.model.LiveDataEvents;
import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;
import android.os.Bundle;
import android.os.Parcel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class MusicalFragment extends Fragment {
    ArrayList<Review> reviewsList = new ArrayList<>();
    ReviewsListFragment reviewListFragment;
    FragmentMusicalBinding binding;
    ReviewRecyclerAdapter.OnItemClickListener reviewRowOnClickListener;

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
        binding = FragmentMusicalBinding.inflate(inflater,container,false);
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

        reloadData();

        LiveDataEvents.instance().EventReviewListReload.observe(getViewLifecycleOwner(),unused->{
            reloadData();
        });

        return view;
    }

    void reloadData(){
        Model.instance().getAllReviews((reviewsData) -> {
            reviewsList = reviewsData;
            if (reviewListFragment != null) {
                reviewListFragment.setParameters(reviewsList, reviewRowOnClickListener);
            }
        });
    }
}