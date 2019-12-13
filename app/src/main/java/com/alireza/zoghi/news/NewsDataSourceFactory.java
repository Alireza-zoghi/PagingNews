package com.alireza.zoghi.news;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.alireza.zoghi.news.api.NewsAPI;
import com.alireza.zoghi.news.model.NewsDataSource;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/
public class NewsDataSourceFactory extends DataSource.Factory {

    private NewsDataSource newsDataSource;
    private NewsAPI newsAPI;
    private MutableLiveData<NewsDataSource> mutableLiveData;

    public NewsDataSourceFactory(NewsAPI newsAPI) {
        this.newsAPI = newsAPI;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        newsDataSource = new NewsDataSource(newsAPI);
        mutableLiveData.postValue(newsDataSource);
        return newsDataSource;
    }

    public MutableLiveData<NewsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
