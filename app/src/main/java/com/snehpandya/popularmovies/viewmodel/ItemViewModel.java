package com.snehpandya.popularmovies.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.snehpandya.popularmovies.apiservice.RetrofitService;
import com.snehpandya.popularmovies.model.Response;
import com.snehpandya.popularmovies.model.Result;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sneh.pandya on 15/09/17.
 */

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<List<Result>> mListLiveData = new MutableLiveData<>();

    public ItemViewModel() {
        getMovies();
    }

    public LiveData<List<Result>> getData() {
        return mListLiveData;
    }

    private void getMovies() {
        RetrofitService retrofitService = new RetrofitService();
        Observable<Response> mResponse = retrofitService.mTMDBApi.getResponse("YOUR_API_KEY", 1);
        mResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateUI, this::handleError);
    }

    private void updateUI(Response response) {
        mListLiveData.setValue(response.getResults());
    }

    private void handleError(Throwable throwable) {
        Log.d("TAG", "handleError: " + throwable);
    }
}
