package com.example.newsapitest.core.local;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapitest.model.articel.Article;

public class LocalRepository {
    private final ArticleDao articleDao;

    public LocalRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        articleDao = database.articleDao();
    }

    public LiveData<Boolean> checkData(Article article) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new Thread(() -> {
            try {
                int existingCount = articleDao.checkIfArticleExists(article.getId());

                articleDao.getAllArticles();
                if (existingCount == 0) {
                    resultLiveData.postValue(true);
                } else {
                    resultLiveData.postValue(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultLiveData.postValue(false);
            }
        }).start();
        return resultLiveData;
    }

    public MutableLiveData<Boolean> saveData(Article articles) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new Thread(() -> {
            try {
                int existingCount = articleDao.checkIfArticleExists(articles.getId());
                articleDao.getAllArticles();

                if (existingCount == 0) {
                    articleDao.insertArticle(articles);
                    resultLiveData.postValue(true);
                } else {
                    resultLiveData.postValue(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultLiveData.postValue(false);
            }
        }).start();
        return resultLiveData;
    }

    public MutableLiveData<Boolean> deleteData(Article articles) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new Thread(() -> {
            try {
                articleDao.getAllArticles();

                articleDao.deleteArticleById(articles.getId());
                resultLiveData.postValue(false);
            } catch (Exception e) {
                e.printStackTrace();
                resultLiveData.postValue(false);
            }
        }).start();
        return resultLiveData;
    }
}
