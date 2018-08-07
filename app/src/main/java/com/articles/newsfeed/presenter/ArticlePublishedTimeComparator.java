package com.articles.newsfeed.presenter;

import com.articles.newsfeed.model.pojo.Article;

import java.util.Comparator;

class ArticlePublishedTimeComparator implements Comparator<Article> {


    @Override
    public int compare(Article article, Article article1) {
        return article1.getPublishedAt().compareTo(article.getPublishedAt());
    }
}
