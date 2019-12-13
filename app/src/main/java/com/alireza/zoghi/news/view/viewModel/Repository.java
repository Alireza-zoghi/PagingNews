package com.alireza.zoghi.news.view.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.alireza.zoghi.news.api.NewsAPI;
import com.alireza.zoghi.news.api.RetrofitProvider;
import com.alireza.zoghi.news.model.Articles;
import com.alireza.zoghi.news.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/
public class Repository {
    private static final String TAG = "Repository";
    private List<Articles> articles = new ArrayList<>();
    private MutableLiveData<List<Articles>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Articles>> getMutableLiveData() {
        NewsAPI newsAPI = RetrofitProvider.getService();

        Call<NewsResponse> call = newsAPI.getNewsResponse("iran", "publishedAt", "c6cd5286eab44585ad85d16ae9efae9d");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse = response.body();
                if (newsResponse != null && newsResponse.getArticles() != null) {
                    articles = newsResponse.getArticles();
                    mutableLiveData.setValue(articles);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

}

