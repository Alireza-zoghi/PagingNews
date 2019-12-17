package com.alireza.zoghi.news;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.alireza.zoghi.news.api.Repository;
import com.alireza.zoghi.news.api.RetrofitProvider;
import com.alireza.zoghi.news.model.Articles;
import com.alireza.zoghi.news.model.NewsResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/

public class NewsDataSource extends PageKeyedDataSource<Long, Articles> {
    private static final String TAG = "NewsDataSource";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Articles> callback) {
        Repository.getInstance().getMutableLiveData("iran", "publishedAt", "c6cd5286eab44585ad85d16ae9efae9d", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<NewsResponse>() {
                    @Override
                    public void onSuccess(NewsResponse newsResponse) {
                        List<Articles> articles;
                        if (newsResponse != null && newsResponse.getArticles() != null) {
                            Log.e(TAG, "first page was loaded");
                            articles = newsResponse.getArticles();
                            callback.onResult(articles, null, (long) 2);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error on load first page:" + e.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Articles> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Articles> callback) {
        Repository.getInstance().getMutableLiveData("iran", "publishedAt", "c6cd5286eab44585ad85d16ae9efae9d", params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<NewsResponse>() {
                    @Override
                    public void onSuccess(NewsResponse newsResponse) {
                        List<Articles> articles;
                        if (newsResponse != null && newsResponse.getArticles() != null) {
                            Log.e(TAG, "page " + params.key + " was loaded");
                            articles = newsResponse.getArticles();
                            callback.onResult(articles, params.key + 1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error on load first page:" + e.getMessage());
                    }
                });
    }
}
