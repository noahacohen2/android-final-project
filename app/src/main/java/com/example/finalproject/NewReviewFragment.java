package com.example.finalproject;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.databinding.FragmentNewReviewBinding;
import com.example.finalproject.model.Model;
import com.example.finalproject.model.Review;

public class NewReviewFragment extends Fragment {

    FragmentNewReviewBinding binding;
    ActivityResultLauncher<Void> cameraLauncher;
    Review currentReview;

    private void setParameters(Review rv) {
        this.currentReview = rv;
        if(rv != null) {
            Log.d("noa-seat", rv.getSeat());
        }
    }
    public static NewReviewFragment newInstance(Review rv){
        NewReviewFragment frag = new NewReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Review",rv);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initScreen() {
        binding.seatEt.setText(currentReview.getSeat());
        binding.starsRating.setRating(currentReview.getStars());
        binding.contentEt.setText(currentReview.getContent());
        // TODO: add img here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewReviewBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        setParameters(NewReviewFragmentArgs.fromBundle(getArguments()).getReview());

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                if (result != null) {
                    binding.addImgBtn.setImageBitmap(result);
                }
            }
        });

        binding.addImgBtn.setOnClickListener(view1 -> {
            cameraLauncher.launch(null);
        });

        binding.saveBtn.setOnClickListener(view1 -> {
            String seat = binding.seatEt.getText().toString();
            Float rate = binding.starsRating.getRating();
            String content = binding.contentEt.getText().toString();

            if(currentReview == null) {
                Review rv = new Review(seat,rate,content, "2");
                Model.instance().addReview(rv, () -> {
                    Navigation.findNavController(view1).popBackStack();
                });
            } else {
                String id = "2";
                Review rv = new Review(seat,rate,content, "1", currentReview.getDocId());
                Model.instance().updateReview(rv, () -> {
                    Navigation.findNavController(view1).popBackStack();
                });
            }

        });

        binding.cancleBtn.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).popBackStack();
        });

        if(currentReview != null) {
            initScreen();
        }

        return view;
    }
}