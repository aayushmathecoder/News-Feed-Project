package com.articles.newsfeed.model.pojo;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootObject
{
    @SerializedName("status")
    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    @SerializedName("totalResults")
    private int totalResults;

    public int getTotalResults() { return this.totalResults; }

    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

    @SerializedName("articles")
    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() { return this.articles; }

    public void setArticles(ArrayList<Article> articles) { this.articles = articles; }
}