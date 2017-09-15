package com.snehpandya.popularmovies.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.data.MoviesAdapter;
import com.snehpandya.popularmovies.databinding.ActivityMainBinding;
import com.snehpandya.popularmovies.viewmodel.ItemViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        itemViewModel.getData().observe(this, data -> {
            MoviesAdapter moviesAdapter = new MoviesAdapter(data);
            mBinding.recyclerview.setAdapter(moviesAdapter);
        });
    }
}
