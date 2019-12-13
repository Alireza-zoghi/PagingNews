package com.alireza.zoghi.news.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import com.alireza.zoghi.news.R;
import com.alireza.zoghi.news.databinding.ActivityMainBinding;
import com.alireza.zoghi.news.model.Articles;
import com.alireza.zoghi.news.view.adapter.RecyclerViewAdapter;
import com.alireza.zoghi.news.view.viewModel.MainActivityVM;

import java.util.ArrayList;
import java.util.List;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onNewsItemClick {

    private ActivityMainBinding binding;
    private MainActivityVM viewModel;
    private PagedList<Articles> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityVM.class);
        initToolbar();
        getNews();
    }

    private void initToolbar() {
        Toolbar toolbar = binding.toolbar;
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getNews() {
        viewModel.getNewsPagedList().observe(this, articlesFromLiveData -> {
            articles = articlesFromLiveData;
            initRecycler();
        });
    }

    private void initRecycler() {
        RecyclerView recyclerView = binding.rvNews;

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        adapter.submitList(articles);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(Articles articles) {
        Intent intent=new Intent(MainActivity.this,NewsDetailActivity.class);
        intent.putExtra("article",articles);
        startActivity(intent);
    }
}
