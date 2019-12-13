package com.alireza.zoghi.news.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.alireza.zoghi.news.R;
import com.alireza.zoghi.news.databinding.ActivityNewsDetailBinding;
import com.alireza.zoghi.news.model.Articles;
import com.squareup.picasso.Picasso;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 1:04 AM
 ************************************************/

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Articles articles = getIntent().getParcelableExtra("article");
        if (articles != null) {
            ActivityNewsDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);

            binding.setModel(articles);
            Picasso.get().load(articles.getUrlToImage()).into(binding.image);
        }


    }
}
