package com.example.newsapitest.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapitest.core.local.LocalRepository;
import com.example.newsapitest.model.articel.Article;

public class LocalArticleViewModel extends AndroidViewModel {
    private LocalRepository repository;

    public LocalArticleViewModel(Application application) {
        super(application);
        repository = new LocalRepository(application);
    }

    public LiveData<Boolean> getStatus(Article article) {
        return repository.checkData(article);
    }

    public LiveData<Boolean> saveData(Article articles) {
        return repository.saveData(articles);
    }

    public LiveData<Boolean> deleteData(Article articles) {
        return repository.deleteData(articles);
    }
}
