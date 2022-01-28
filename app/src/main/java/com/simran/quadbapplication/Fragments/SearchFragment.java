package com.simran.quadbapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.simran.quadbapplication.Adapter.SearchMovieAdapter;
import com.simran.quadbapplication.DataClasses.Movie;
import com.simran.quadbapplication.R;
import com.simran.quadbapplication.Utils.MovieQueryUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchFragment extends Fragment {

    public static final String TAG = "Search";

    List<Movie> movies;
    String jsonData;
    SearchMovieAdapter adapter;

    // views
    RecyclerView recyclerView;
    EditText searchEditText;

    public SearchFragment() {
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
        View parent = inflater.inflate(R.layout.fragment_search, container, false);

        // init views
        searchEditText = parent.findViewById(R.id.search_edit_txt);
        recyclerView = parent.findViewById(R.id.search_recycler);

        // init recyclerview with adapter
        movies = new ArrayList<>();
        adapter = new SearchMovieAdapter(getContext(), movies);
        recyclerView.setAdapter(adapter);

        // search button onclick
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_SEND) {
                String searchTerm  = searchEditText.getText().toString();
                performSearch(searchTerm);
                return true;
            }
            return false;
        });

        return parent;
    }

    void performSearch(String searchTerm) {
        // thread for getting list of movies
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .writeTimeout(300, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .connectTimeout(300, TimeUnit.SECONDS)
                    .callTimeout(300, TimeUnit.SECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url("https://api.tvmaze.com/search/shows?q=$" + searchTerm)
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
                });

                Log.d(TAG, "run: " + statusCode);

            } catch (IOException | JSONException e) {
                Log.d(TAG, "onCreateView: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}