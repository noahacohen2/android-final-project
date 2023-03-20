package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.finalproject.databinding.FragmentUserProfileBinding;
import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserProfileFragment extends Fragment {
    ArrayList<Review> reviewsList = new ArrayList<>();
    FragmentUserProfileBinding binding;
    FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        ReviewsListFragment reviewListFragment = (ReviewsListFragment) getChildFragmentManager().findFragmentById(R.id.listContainer);

        ReviewRecyclerAdapter.OnItemClickListener reviewRowOnClickListener = new ReviewRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int pos) {
                Bundle bundle = new Bundle();
                Review rv = reviewsList.get(pos);
                bundle.putParcelable("Review", rv);
                Navigation.findNavController(view).navigate(R.id.action_userProfileFragment_to_newReviewFragment, bundle);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {

            }
        };

        Model.instance().getUserReviews("1",(data)-> {
            reviewsList = data;
            if (reviewListFragment != null) {
                reviewListFragment.setParameters(reviewsList, reviewRowOnClickListener);
            }
        });

        if (reviewListFragment != null) {
            reviewListFragment.setParameters(reviewsList, reviewRowOnClickListener);
        }

        NavDirections action = UserProfileFragmentDirections.actionUserProfileFragmentToEditUserProfileFragment();
        binding.editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(action));
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                changeActivity(LoginActivity.class);
            }
        });

        return view;
    }

    private void changeActivity(Class activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
    }
}