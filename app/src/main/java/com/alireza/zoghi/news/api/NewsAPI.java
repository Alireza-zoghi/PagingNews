package com.alireza.zoghi.news.api;

import com.alireza.zoghi.news.model.NewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/

public interface NewsAPI {

    @GET("everything")
    Single<NewsResponse> getNewsResponse(@Query("q") String search, @Query("sortBy") String sort, @Query("apiKey") String apiKey, @Query("page") long page);

}