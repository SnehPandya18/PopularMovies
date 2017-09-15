package com.snehpandya.popularmovies.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.apiservice.RetrofitService;
import com.snehpandya.popularmovies.data.MoviesAdapter;
import com.snehpandya.popularmovies.databinding.ActivityMainBinding;
import com.snehpandya.popularmovies.model.Response;
import com.snehpandya.popularmovies.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private List<Result> mMoviesList = new ArrayList<>();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        getMovies(page);

        mRecyclerView = findViewById(R.id.recyclerview);
    }

    private void getMovies(int page) {
        RetrofitService retrofitService = new RetrofitService();
        Call<Response> mResponse = retrofitService.mTMDBApi.getResponse("YOUR_API_KEY", page);
        mResponse.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                mMoviesList.addAll(response.body().getResults());
                MoviesAdapter mMoviesAdapter = new MoviesAdapter(mMoviesList);
                mRecyclerView.setAdapter(mMoviesAdapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t);
            }
        });
    }
}
