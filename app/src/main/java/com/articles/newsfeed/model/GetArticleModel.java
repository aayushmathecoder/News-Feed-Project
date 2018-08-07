package com.articles.newsfeed.model;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.articles.newsfeed.model.pojo.RootObject;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetArticleModel {
    private static final String API_KEY = "3363a374df9f4660a260a187915f0937";
    private static final String COUNTRY = "us";

    public void getArticles() {
        /*Create handle for the RetrofitInstance interface*/
        ApiInterface apiInterface = APIClient.getRetrofitInstance().create(ApiInterface.class);
        String pageSize = "20";
        Call<RootObject> call = apiInterface.getArticles(COUNTRY, pageSize, API_KEY);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(@NonNull Call<RootObject> call, @NonNull Response<RootObject> response) {
                RootObject rootObject = response.body();
                if (rootObject != null) {

                    EventBus.getDefault().post(rootObject.getArticles());


                }

            }

            @Override
            public void onFailure(@NonNull Call<RootObject> call, @NonNull Throwable t) {
                //this is an actual network failure :( inform the user and possibly retry
                if (t instanceof IOException) {
                    Log.d("Connectivity issue", "Retry");
                } else {
                    //conversion issue! big problems "
                    Log.d("Converter issue", "Check Conversions");
                }
            }
        });


    }


}
