package com.alireza.zoghi.news.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/

public class RetrofitProvider {

    private static Retrofit retrofit = null;

    public static NewsAPI getService() {

        if (retrofit == null) {

            String BASE_URL = "https://newsapi.org/v2/";
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(NewsAPI.class);

    }

}
