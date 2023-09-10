package com.example.newsapitest.viewmodel;

import static com.example.newsapitest.utils.AppConstant.API_KEY;
import static com.example.newsapitest.utils.AppConstant.ARTICLE_QUERY;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapitest.core.remot.AppRepository;
import com.example.newsapitest.model.articel.ArticleResponse;
import com.example.newsapitest.model.source.SourceResponse;

public class ArticleViewModel extends AndroidViewModel {
    private final AppRepository articleRepository;
    private final LiveData<ArticleResponse> articleResponseLiveData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);

        articleRepository = new AppRepository();
        this.articleResponseLiveData = articleRepository.getEverythingArticles(ARTICLE_QUERY, API_KEY);
    }

    public LiveData<ArticleResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }


    public LiveData<SourceResponse> getSourceResponseLiveData(String category) {
        return articleRepository.getSourceResponseLiveData(category, API_KEY);
    }

    public LiveData<ArticleResponse> getArticlesByResource(String category, String query) {
        return articleRepository.getArticlesByResource(category, query, API_KEY);
    }

    public LiveData<ArticleResponse> getSearchArticleResponseLiveData(String query) {
        return articleRepository.getEverythingArticles(query, API_KEY);
    }
}
