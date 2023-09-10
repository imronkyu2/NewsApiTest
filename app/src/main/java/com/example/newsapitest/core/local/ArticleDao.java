package com.example.newsapitest.core.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newsapitest.model.articel.Article;

import java.util.List;

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM articles")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT COUNT(*) FROM articles WHERE id = :articleId")
    int checkIfArticleExists(int articleId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article article);

    @Query("DELETE FROM articles WHERE id = :articleId")
    void deleteArticleById(int articleId);
}
