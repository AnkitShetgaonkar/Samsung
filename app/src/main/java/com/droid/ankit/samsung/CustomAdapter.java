package com.droid.ankit.samsung;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.droid.ankit.samsung.data.MovieData;

import java.util.ArrayList;

/**
 * Created by Ankit on 3/17/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<MovieData> mMovieData;

    public CustomAdapter(ArrayList<MovieData> movieData) {
        this.mMovieData = movieData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getMovieNameTv().setText(mMovieData.get(position).getMovieName());
        holder.getPromotionTv().setText(mMovieData.get(position).getPopularityName());
        holder.getGenreTv().setText(mMovieData.get(position).getGenre());
        Glide.with(holder.getImageView().getContext()).load(mMovieData.get(position).getImageUrl()).
                apply(RequestOptions.circleCropTransform()).into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movieNameTv;
        private TextView promotionTv;
        private ImageView imageView;
        private TextView genreTv;

        public ViewHolder(View itemView) {
            super(itemView);
            movieNameTv = itemView.findViewById(R.id.tv_1);
            promotionTv = itemView.findViewById(R.id.tv_2);
            imageView = itemView.findViewById(R.id.tv_image);
            genreTv = itemView.findViewById(R.id.tv_genre);
        }

        public TextView getMovieNameTv() {
            return movieNameTv;
        }

        public TextView getPromotionTv() {
            return promotionTv;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getGenreTv() {
            return genreTv;
        }
    }
}
