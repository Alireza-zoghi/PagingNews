package com.alireza.zoghi.news;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/
public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<NewsDataSource> mutableLiveData;

    public NewsDataSourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        NewsDataSource newsDataSource = new NewsDataSource();
        mutableLiveData.postValue(newsDataSource);
        return newsDataSource;
    }
}
