package com.snehpandya.popularmovies.viewmodel;

import android.databinding.BaseObservable;
import android.util.Log;

import com.snehpandya.popularmovies.BR;
import com.snehpandya.popularmovies.apiservice.RetrofitService;
import com.snehpandya.popularmovies.model.Response;
import com.snehpandya.popularmovies.model.Result;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sneh.pandya on 15/09/17.
 */

public class ItemViewModel extends BaseObservable {

    private List<Result> mResults = new ArrayList<>();

    public ItemViewModel() {
    }

    public List<Result> getResult() {
        return mResults;
    }

    private void setResult(List<Result> result) {
        this.mResults = result;
        notifyPropertyChanged(BR.result);
    }

    public void getMovies(int page) {
        RetrofitService retrofitService = new RetrofitService();
        Observable<Response> mResponse = retrofitService.mTMDBApi.getResponse("YOUR_API_KEY", page);
        mResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateUI, this::handleError);
    }

    private void updateUI(Response response) {
        setResult(response.getResults());
    }

    private void handleError(Throwable throwable) {
        Log.d("TAG", "handleError: " + throwable);
    }
}
