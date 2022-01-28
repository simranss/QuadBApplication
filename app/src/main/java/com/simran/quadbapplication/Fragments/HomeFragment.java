package com.simran.quadbapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.simran.quadbapplication.Activities.DetailsActivity;
import com.simran.quadbapplication.Adapter.MovieAdapter;
import com.simran.quadbapplication.DataClasses.Movie;
import com.simran.quadbapplication.Utils.MovieQueryUtils;
import com.simran.quadbapplication.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HomeFragment extends Fragment {

    public static final String TAG = "Home";

    public List<Movie> movies;
    String jsonData;

    // Views
    ImageView firstMovieImg;
    TextView firstMovieName;
    RecyclerView recyclerView;
    FrameLayout firstMovieContainer;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_home, container, false);

        // init views
        firstMovieContainer = parent.findViewById(R.id.first_movie_container);
        firstMovieImg = parent.findViewById(R.id.first_movie_img);
        firstMovieName = parent.findViewById(R.id.first_movie_txt);
        recyclerView = parent.findViewById(R.id.movie_recycler);

        // init recyclerview with adapter
        movies = new ArrayList<>();
        MovieAdapter adapter = new MovieAdapter(movies, getContext());
        recyclerView.setAdapter(adapter);

        // thread for getting list of movies
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .writeTimeout(300, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .connectTimeout(300, TimeUnit.SECONDS)
                    .callTimeout(300, TimeUnit.SECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url("https://api.tvmaze.com/search/shows?q=all")
                    .build();

            try {

                Response response = client.newCall(request).execute();
                int statusCode = response.code();
                ResponseBody body = response.body();
                assert body != null;
                jsonData = body.string();
                response.close();

                movies = MovieQueryUtils.getMovies(jsonData);

                getActivity().runOnUiThread(() -> {
                    adapter.update(0, movies);
                    Movie movie = movies.get(0);
                    Glide.with(HomeFragment.this)
                            .load(movie.getImage().get("original"))
                            .transform(new RoundedCorners(10))
                            .into(firstMovieImg);
                    firstMovieName.setText(movie.getName());

                    // first movie click
                    firstMovieContainer.setOnClickListener(v -> {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra("movie", movie);
                        startActivity(intent);
                    });
                });

                Log.d(TAG, "run: " + statusCode);

            } catch (IOException | JSONException e) {
                Log.d(TAG, "onCreateView: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
        return parent;
    }
}