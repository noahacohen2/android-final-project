package com.example.finalproject;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.finalproject.model.Review;

import java.util.List;

class ReviewViewHolder extends RecyclerView.ViewHolder{
    TextView seatTv;
    TextView textTv;
    RatingBar ratingBar;
    ImageView reviewIv;

    List<Review> data;
    public ReviewViewHolder(@NonNull View itemView, List<Review> data) {
        super(itemView);
        this.data = data;
        seatTv = itemView.findViewById(R.id.reviewrow_seat_tv);
        textTv = itemView.findViewById(R.id.reviewrow_review_tv);
        ratingBar = itemView.findViewById(R.id.reviewrow_ratingBar);
        reviewIv = itemView.findViewById(R.id.reviewrow_img);
    }

    public void bind(Review review, int pos) {
        seatTv.setText(review.seat);
        textTv.setText(review.text);
        ratingBar.setRating(review.rate);
        //TODO: add image here
    }
}

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewViewHolder>{


    LayoutInflater inflater;
    List<Review> data;

    public void setData(List<Review> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ReviewRecyclerAdapter(LayoutInflater inflater, List<Review> data){
        this.inflater = inflater;
        this.data = data;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.review_row,parent,false);
        return new ReviewViewHolder(view, data);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = data.get(position);
        holder.bind(review,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}