package com.snehpandya.popularmovies.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.databinding.ActivityDetailBinding;

/**
 * Created by sneh.pandya on 15/09/17.
 */

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mBinding.setResult(getIntent().getExtras().getParcelable("movie"));
    }
}
