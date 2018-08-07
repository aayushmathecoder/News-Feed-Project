package com.articles.newsfeed.presenter;

import com.articles.newsfeed.model.GetArticleModel;
import com.articles.newsfeed.model.pojo.Article;

import java.util.ArrayList;
import java.util.Collections;

public class GetArticlePresenter {

    public void getArticles()
    {
        GetArticleModel getArticleModel=new GetArticleModel();
        getArticleModel.getArticles();
    }


    public void sortByPublishedTime(ArrayList<Article> articleArrayList) {

        Collections.sort(articleArrayList,new ArticlePublishedTimeComparator());

    }
}
