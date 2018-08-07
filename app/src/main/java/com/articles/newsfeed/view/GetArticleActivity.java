package com.articles.newsfeed.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.articles.newsfeed.R;
import com.articles.newsfeed.model.data.LoadArticlesAdapter;
//import com.articles.newsfeed.model.network.NewsSyncJob;
import com.articles.newsfeed.model.pojo.Article;
import com.articles.newsfeed.presenter.GetArticlePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class GetArticleActivity extends AppCompatActivity {
    GetArticlePresenter mGetArticlePresenter;
    RecyclerView mArticleRecyclerView;
    private LoadArticlesAdapter mAdapter;
    boolean mIsScrolling = true;
    int currentItems, totalItems, scrollOutItems;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //setting it to false,since we have used our own text(in textview) to be displayed in toolbar
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //setting it to true,since pressing back button,we want to go one level up.
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        initUI();
        mProgressBar.setVisibility(View.VISIBLE);
        mGetArticlePresenter = new GetArticlePresenter();
        mGetArticlePresenter.getArticles();
        //Schedule News Syncing
       // NewsSyncJob.schedulePeriodicJob();
    }

    private void initUI() {
        mArticleRecyclerView = findViewById(R.id.card_recycler_view);
        mProgressBar=findViewById(R.id.progressBar);
    }

    @Subscribe
    public void onEventMainThread(final Object object) {

        mProgressBar.setVisibility(View.GONE);
        ArrayList<Article> articleArrayList = (ArrayList<Article>) object;
        //sort List on the basis of publishedAt field
        mGetArticlePresenter.sortByPublishedTime(articleArrayList);
        //set Adapter
        mAdapter = new LoadArticlesAdapter(articleArrayList, this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mArticleRecyclerView.setLayoutManager(mLayoutManager);
        mArticleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mArticleRecyclerView.setAdapter(mAdapter);
        // here add a recyclerView listener, to listen to scrolling,
        // we don't care when user scrolls upwards, will only be careful when user scrolls downwards
        // this listener is freely provided for by android, no external library
        mArticleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // for this tutorial, this is the ONLY method that we need, ignore the rest
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = mLayoutManager.getChildCount();
                totalItems = mLayoutManager.getItemCount();
                scrollOutItems = mLayoutManager.findFirstVisibleItemPosition();


                if (mIsScrolling && (currentItems + scrollOutItems == totalItems)) {
                    //fetch data
                    mIsScrolling = false;
                    mGetArticlePresenter.getArticles();
                }


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setQueryHint(getResources().getString(R.string.search_title));
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mAdapter != null) {
                    mAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
    }
}
