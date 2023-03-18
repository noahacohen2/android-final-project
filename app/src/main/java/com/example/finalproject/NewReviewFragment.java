package com.example.finalproject;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class NewReviewFragment extends Fragment {
    ActivityResultLauncher<Void> cameraLauncher;
    ImageView newReviewImgBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_review, container, false);
        newReviewImgBtn = view.findViewById(R.id.newReview_addimg_btn);

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                if (result != null) {
//                    isAvatarSelected = true;

                    newReviewImgBtn.setImageBitmap(result);
                }
            }
        });

        newReviewImgBtn.setOnClickListener(view1 -> {
            cameraLauncher.launch(null);
        });

        Button saveBtn = view.findViewById(R.id.addreview_save_btn);
//        saveBtn.setOnClickListener(view1 -> {
//
//        });

        return view;
    }
}