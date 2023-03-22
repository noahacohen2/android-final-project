package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.finalproject.databinding.FragmentUserProfileBinding;
import com.example.finalproject.model.LiveDataEvents;
import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;
import com.example.finalproject.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserProfileFragment extends Fragment {
    FragmentUserProfileBinding binding;
    FirebaseAuth mAuth;
    UserProfileFragmentViewModel viewModel;
    User currUserData;

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
                Review rv = viewModel.getReviewListData().getValue().get(pos);
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

        viewModel.getReviewListData().observe(getViewLifecycleOwner(),list->{
            reviewListFragment.setParameters(list, reviewRowOnClickListener);
        });

        Model.instance().getUserData(user -> {
            binding.usernameTv.setText(user.getFirstName() + " " + user.getLastName());
            binding.mailTv.setText(user.getMail());
            binding.bioTv.setText(user.getBio());
            currUserData = user;

            if(currUserData.getImgUrl() != null) {
                Picasso.get().load(currUserData.getImgUrl()).placeholder(R.drawable.bear).into(binding.avatarImg);
            } else {
                binding.avatarImg.setImageResource(R.drawable.bear);
            }
        });


        binding.editBtn.setOnClickListener(view1 -> {
            Bundle userBundle = new Bundle();
            userBundle.putParcelable("User", currUserData);
            Navigation.findNavController(view1).navigate(R.id.action_userProfileFragment_to_editUserProfileFragment, userBundle);
        });

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                changeActivity(LoginActivity.class);
            }
        });

        // todo: change temp user

        User user = new User( "123456","lal@gmail.com", "daniel",
                "sabag", "daniel123" , "i am lala", "xUddApYCDjdysmQZrEeQu9jgNwR2","https://firebasestorage.googleapis.com/v0/b/olaf-android.appspot.com/o/images%2F14bc3e06-c07d-47ee-96fa-0b675921efdb.jpg?alt=media&token=c1c76581-9bbf-4e28-a217-92c6241e3671");
        if(user.getImgUrl() != null) {
            Picasso.get().load(user.getImgUrl()).placeholder(R.drawable.bear).into(binding.avatarImg);
        } else {
            binding.avatarImg.setImageResource(R.drawable.bear);
        }
        LiveDataEvents.instance().EventReviewListReload.observe(getViewLifecycleOwner(),unused->{
            Model.instance().refreshAllUserReviews();
        });

        return view;
    }

    private void changeActivity(Class activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(UserProfileFragmentViewModel.class);
    }
}