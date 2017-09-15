package com.snehpandya.popularmovies.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.apiservice.RetrofitService;
import com.snehpandya.popularmovies.data.MoviesAdapter;
import com.snehpandya.popularmovies.databinding.ActivityMainBinding;
import com.snehpandya.popularmovies.model.Response;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        getMovies(page);
    }

    private void getMovies(int page) {
        RetrofitService retrofitService = new RetrofitService();
        Observable<Response> mResponse = retrofitService.mTMDBApi.getResponse("YOUR_API_KEY", page);
        mResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateUI, this::handleError);
    }

    private void updateUI(Response response) {
        MoviesAdapter moviesAdapter = new MoviesAdapter(response.getResults());
        mBinding.recyclerview.setAdapter(moviesAdapter);
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "handleError: " + throwable);
    }
}
