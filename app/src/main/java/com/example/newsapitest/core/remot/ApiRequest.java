package com.example.newsapitest.core.remot;

import com.example.newsapitest.model.articel.ArticleResponse;
import com.example.newsapitest.model.source.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("everything/")
    Call<ArticleResponse> getEverythingArticles(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );

    @GET("sources/")
    Call<SourceResponse> getSourceResponseLiveData(
            @Query("category") String category,
            @Query("apikey") String apiKey);

    @GET("everything/")
    Call<ArticleResponse> getArticlesByResource(
            @Query("sources") String sources,
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
}
