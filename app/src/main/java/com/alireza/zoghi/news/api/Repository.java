package com.alireza.zoghi.news.api;

import com.alireza.zoghi.news.model.NewsResponse;

import io.reactivex.Single;

/*************************************************
 * Created by AliReza Zoghi on 12/14/19 7:22 PM
 ************************************************/

public class Repository {
    private static Repository repository;

    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }

    public Single<NewsResponse> getMutableLiveData(String search, String sort, String apiKey, long page) {
        return RetrofitProvider.getService().getNewsResponse(search, sort, apiKey, page);
    }

}

