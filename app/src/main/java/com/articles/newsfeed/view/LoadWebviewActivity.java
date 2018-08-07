package com.articles.newsfeed.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.articles.newsfeed.R;
import com.articles.newsfeed.presenter.GetArticlePresenter;

public class LoadWebviewActivity extends AppCompatActivity {
     WebView mWebview;
    private String mUrl;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_webview);
        mUrl = getIntent().getStringExtra("url");
        initUI();


    }

    private void initUI() {
        mWebview = (WebView) findViewById(R.id.webView);
        mProgressBar=findViewById(R.id.progressBar1);
        mWebview.setWebViewClient(new ArticleWebClient());
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebview.loadUrl(mUrl);

    }
    public class ArticleWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            mProgressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            mProgressBar.setVisibility(View.GONE);
        }
    }

}
