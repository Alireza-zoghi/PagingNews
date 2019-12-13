package com.alireza.zoghi.news.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alireza.zoghi.news.R;
import com.alireza.zoghi.news.databinding.ItemNewsOneBinding;
import com.alireza.zoghi.news.databinding.ItemNewsOneBinding;
import com.alireza.zoghi.news.databinding.ItemNewsTwoBinding;
import com.alireza.zoghi.news.model.Articles;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/
public class RecyclerViewAdapter extends PagedListAdapter<Articles, RecyclerViewAdapter.NewsViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private LayoutInflater layoutInflater;
    private onNewsItemClick onNewsItemClick;

    public RecyclerViewAdapter(onNewsItemClick onNewsItemClick) {
        super(NewsDiffCallback);
        this.onNewsItemClick = onNewsItemClick;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new NewsViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_news_two, parent, false));
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Articles articles = getItem(position);
        holder.binding.parent.setOnClickListener(view -> onNewsItemClick.onClick(articles));
        holder.bindTo(articles);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsTwoBinding binding;

        private NewsViewHolder(@NonNull ItemNewsTwoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindTo(Articles articles) {
            binding.setModel(articles);
            Picasso.get().load(articles.getUrlToImage()).into(binding.ivImage);
        }
    }

    private static DiffUtil.ItemCallback<Articles> NewsDiffCallback = new DiffUtil.ItemCallback<Articles>() {
        @Override
        public boolean areItemsTheSame(@NonNull Articles oldItem, @NonNull Articles newItem) {
            return oldItem.getUrl().equals(newItem.getUrl());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Articles oldItem, @NonNull Articles newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };

    public interface onNewsItemClick {
        void onClick(Articles articles);
    }

}
