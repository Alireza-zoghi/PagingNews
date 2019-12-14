package com.alireza.zoghi.news.view.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.alireza.zoghi.news.NewsDataSourceFactory;
import com.alireza.zoghi.news.model.Articles;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/

public class MainActivityVM extends ViewModel {

    private LiveData<PagedList<Articles>> newsPagedList;

    public MainActivityVM() {
        NewsDataSourceFactory factory = new NewsDataSourceFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        Executor executor = Executors.newFixedThreadPool(3);

        newsPagedList = (new LivePagedListBuilder<Long, Articles>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Articles>> getNewsPagedList() {
        return newsPagedList;
    }
}
