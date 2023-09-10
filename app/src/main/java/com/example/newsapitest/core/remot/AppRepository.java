package com.example.newsapitest.core.remot;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapitest.model.articel.ArticleResponse;
import com.example.newsapitest.model.source.SourceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {
    private static final String TAG = AppRepository.class.getSimpleName();
    private final ApiRequest apiRequest;

    public AppRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ArticleResponse> getEverythingArticles(String query, String key) {
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getEverythingArticles(query, key)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticleResponse> call, @NonNull Response<ArticleResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body());
                        } else {
                            Log.e(TAG, "onResponse error:: " + response.message());
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArticleResponse> call, @NonNull Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<SourceResponse> getSourceResponseLiveData(String category, String apiKey) {
        final MutableLiveData<SourceResponse> data = new MutableLiveData<>();
        apiRequest.getSourceResponseLiveData(category, apiKey)
                .enqueue(new Callback<SourceResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SourceResponse> call, @NonNull Response<SourceResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body());
                        } else {
                            Log.e(TAG, "onResponse error:: " + response.message());
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SourceResponse> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure error:: " + t.getMessage());
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<ArticleResponse> getArticlesByResource(String category, String query, String key) {
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getArticlesByResource(category, query, key)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticleResponse> call, @NonNull Response<ArticleResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body());
                        } else {
                            Log.e(TAG, "onResponse error:: " + response.message());
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArticleResponse> call, @NonNull Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
