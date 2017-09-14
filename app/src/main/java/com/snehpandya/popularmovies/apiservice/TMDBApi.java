package com.snehpandya.popularmovies.apiservice;

import com.snehpandya.popularmovies.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sneh.pandya on 14/09/17.
 */

public interface TMDBApi {

    @GET("3/movie/popular")
    Call<Response> getResponse(@Query("api_key") String key, @Query("page") int page);
}
