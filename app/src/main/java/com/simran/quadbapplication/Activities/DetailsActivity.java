package com.simran.quadbapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.simran.quadbapplication.DataClasses.Movie;
import com.simran.quadbapplication.R;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView titleTextView, typeTextView, languageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // init views
        titleTextView = findViewById(R.id.details_title);
        typeTextView = findViewById(R.id.details_type);
        languageTextView = findViewById(R.id.details_language);
        imageView = findViewById(R.id.details_img);

        // get the movie object
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movie");


        // set the values
        String title = "Name: " + movie.getName();
        titleTextView.setText(title);

        String type = "Type: " + movie.getType();
        typeTextView.setText(type);

        String language = "Language: " + movie.getLanguage();
        languageTextView.setText(language);

        Glide.with(getApplicationContext())
                .load(movie.getImage().get("original"))
                .transform(new RoundedCorners(10))
                .into(imageView);
    }
}