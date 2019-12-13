package com.alireza.zoghi.news.view.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.alireza.zoghi.news.NewsDataSourceFactory;
import com.alireza.zoghi.news.api.NewsAPI;
import com.alireza.zoghi.news.api.RetrofitProvider;
import com.alireza.zoghi.news.model.Articles;
import com.alireza.zoghi.news.model.NewsDataSource;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/
public class MainActivityVM extends ViewModel {

    private Repository repository;
    LiveData<NewsDataSource> newsDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Articles>> newsPagedList;

    public MainActivityVM() {
        repository = new Repository();

        NewsAPI newsAPI = RetrofitProvider.getService();
        NewsDataSourceFactory factory = new NewsDataSourceFactory(newsAPI);
        newsDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(3);
        newsPagedList = (new LivePagedListBuilder<Long, Articles>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Articles>> getAllNews() {
        return repository.getMutableLiveData();
    }

    public LiveData<PagedList<Articles>> getNewsPagedList() {
        return newsPagedList;
    }
}
