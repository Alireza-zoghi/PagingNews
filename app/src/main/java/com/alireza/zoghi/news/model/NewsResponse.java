package com.alireza.zoghi.news.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*************************************************
 * Created by AliReza Zoghi on 12/13/19 12:52 AM
 ************************************************/
public class NewsResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResult;

    @SerializedName("articles")
    private List<Articles> articles;

    public NewsResponse() {
    }

    public NewsResponse(String status, int totalResult, List<Articles> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
