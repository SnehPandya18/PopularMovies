package com.snehpandya.popularmovies.apiservice;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sneh.pandya on 14/09/17.
 */

public class RetrofitService {

    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();
    public TMDBApi mTMDBApi = mRetrofit.create(TMDBApi.class);
}
