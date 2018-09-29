package com.idealorb.indimovies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idealorb.indimovies.R;
import com.idealorb.indimovies.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewVH> {

    private final String TAG = ReviewAdapter.class.getSimpleName();
    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
        Log.d(TAG, "setReviews: main list size "+ this.reviews.size());
    }

    @NonNull
    @Override
    public ReviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_review_list_item, parent, false);

        return new ReviewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewVH holder, int position) {
        Review review = reviews.get(position);
        holder.initialTextView.setText(getAutherInitials(review.getAuthor()));
        holder.authorTextView.setText(review.getAuthor());
        holder.reviewTextView.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        return (reviews == null) ? 0 : reviews.size();
    }

    private String getAutherInitials(String authorName){
        String[] names = authorName.split(" ");
        StringBuilder initials = new StringBuilder();
        for (int i = 0; i<names.length; i++) {
            initials.append(names[i].charAt(0));
        }
        return initials.toString().toUpperCase();
    }

    public void setReviews(List<Review> reviews) {
        this.reviews.clear();
        Log.d(TAG, "setReviews: list size "+ reviews.size());
        this.reviews.addAll(reviews);
        Log.d(TAG, "setReviews: main list size "+ this.reviews.size());
    }

    class ReviewVH extends RecyclerView.ViewHolder{

        @BindView(R.id.initials_tv)
        TextView initialTextView;
        @BindView(R.id.reviewer_name_tv)
        TextView authorTextView;
        @BindView(R.id.review_tv)
        TextView reviewTextView;

        ReviewVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
