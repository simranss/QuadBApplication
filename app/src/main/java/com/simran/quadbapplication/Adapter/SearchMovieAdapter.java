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

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchMoviewVH> {

    Context context;
    List<Movie> movies;

    public SearchMovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public SearchMoviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
        return new SearchMoviewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMoviewVH holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getName());
        holder.type.setText(movie.getType());
        holder.language.setText(movie.getLanguage());
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
        int size = movies.size();
        movies.clear();
        notifyItemRangeRemoved(0, size);
        movies.addAll(start, movieList);
        notifyItemRangeInserted(start, movieList.size());
    }

    public static class SearchMoviewVH extends RecyclerView.ViewHolder {

        ImageView movieImg;
        TextView title, type, language;

        public SearchMoviewVH(@NonNull View itemView) {
            super(itemView);

            movieImg = itemView.findViewById(R.id.search_list_img);
            title = itemView.findViewById(R.id.search_list_title);
            type = itemView.findViewById(R.id.search_list_type);
            language = itemView.findViewById(R.id.search_list_language);
        }
    }
}
