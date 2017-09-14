package com.snehpandya.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.snehpandya.popularmovies.data.MoviesAdapter;
import com.snehpandya.popularmovies.model.Movies;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private List<Movies> mMoviesList;
    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        getMovies();

        mMoviesAdapter = new MoviesAdapter(mMoviesList);
        mRecyclerView.setAdapter(mMoviesAdapter);
    }

    private void getMovies() {
        mMoviesList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mMoviesList.add(new Movies("Movie " + i));
        }
    }
}
