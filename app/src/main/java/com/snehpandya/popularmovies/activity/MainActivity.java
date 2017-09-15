package com.snehpandya.popularmovies.activity;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.data.MoviesAdapter;
import com.snehpandya.popularmovies.databinding.ActivityMainBinding;
import com.snehpandya.popularmovies.model.Result;
import com.snehpandya.popularmovies.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Result> mResults = new ArrayList<>();
    private ItemViewModel mViewModel = new ItemViewModel();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.getMovies(page);

        ActivityMainBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        mViewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                mResults = ((ItemViewModel) observable).getResult();
                MoviesAdapter moviesAdapter = new MoviesAdapter(mResults);
                mBinding.recyclerview.setAdapter(moviesAdapter);
            }
        });
    }
}
