package com.articles.newsfeed.presenter;

import com.articles.newsfeed.model.GetArticleModel;
import com.articles.newsfeed.presenter.GetArticlePresenter;
import com.articles.newsfeed.view.GetArticleActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GetArticlePresenter.class})
@PowerMockIgnore("javax.net.ssl.*")
public class GetArticlePresenterTest {

    private GetArticlePresenter mockPresenter;
    private GetArticleActivity mockView;
    boolean isCallbackTriggered;

    @Before
    public void setup() {
        mockPresenter = PowerMockito.mock(GetArticlePresenter.class);

    }

    @Test
    public void testGetArticles() {
        GetArticleModel mockGetArticleModel= PowerMockito.mock(GetArticleModel.class);
        Mockito.doCallRealMethod().when(mockGetArticleModel).getArticles();
        mockGetArticleModel.getArticles();
    }



}