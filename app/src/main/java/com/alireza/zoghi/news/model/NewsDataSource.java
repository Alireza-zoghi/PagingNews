package com.alireza.zoghi.news.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.alireza.zoghi.news.api.NewsAPI;
import com.alireza.zoghi.news.api.RetrofitProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/

public class NewsDataSource extends PageKeyedDataSource<Long, Articles> {
    private static final String TAG = "NewsDataSource";
    private NewsAPI newsAPI;

    public NewsDataSource(NewsAPI newsAPI) {
        this.newsAPI = newsAPI;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Articles> callback) {
        newsAPI = RetrofitProvider.getService();

        Call<NewsResponse> call = newsAPI.getNewsResponse("iran", "publishedAt", "c6cd5286eab44585ad85d16ae9efae9d", 1);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse = response.body();
                List<Articles> articles;
                if (newsResponse != null && newsResponse.getArticles() != null) {
                    articles = newsResponse.getArticles();
                    callback.onResult(articles, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure loadInitial: " + t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Articles> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Articles> callback) {
        newsAPI = RetrofitProvider.getService();

        Call<NewsResponse> call = newsAPI.getNewsResponse("iran", "publishedAt", "c6cd5286eab44585ad85d16ae9efae9d", params.key);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse = response.body();
                List<Articles> articles;
                if (newsResponse != null && newsResponse.getArticles() != null) {
                    articles = newsResponse.getArticles();
                    callback.onResult(articles, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure loadAfter: " + t.getMessage());
            }
        });
    }
}
