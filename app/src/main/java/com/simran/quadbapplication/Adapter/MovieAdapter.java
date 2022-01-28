package com.simran.quadbapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.simran.quadbapplication.Activities.DetailsActivity;
import com.simran.quadbapplication.DataClasses.Movie;
import com.simran.quadbapplication.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<Movie> movies;
    Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movieTitle.setText(movie.getName());
        Glide.with(context)
                .load(movie.getImage().get("original"))
                .transform(new RoundedCorners(10))
                .into(holder.movieImg);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void update(int start, List<Movie> movieList) {
        movies.addAll(start, movieList);
        notifyItemRangeInserted(start, movieList.size());
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImg;
        TextView movieTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImg = itemView.findViewById(R.id.movie_list_img);
            movieTitle = itemView.findViewById(R.id.movie_list_txt);
        }
    }
}
