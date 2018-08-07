package com.articles.newsfeed.model;

import com.articles.newsfeed.model.pojo.RootObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

@GET("v2/top-headlines")
 Call<RootObject> getArticles(@Query("country") String param1,
                                    @Query("pageSize") String param2,
                                    @Query("apiKey") String param3);

}
