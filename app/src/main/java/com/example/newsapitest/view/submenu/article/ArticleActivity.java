package com.example.newsapitest.view.submenu.article;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapitest.R;
import com.example.newsapitest.databinding.ActivityArticleBinding;
import com.example.newsapitest.model.articel.Article;
import com.example.newsapitest.utils.AppConstant;
import com.example.newsapitest.view.submenu.detail.DetailArticleActivity;
import com.example.newsapitest.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleActivity extends AppCompatActivity implements ArticleAdapterViewContract {
    private ActivityArticleBinding binding;
    private ArticleAdapter adapter;
    private final ArrayList<Article> articleArrayList = new ArrayList<>();
    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String id = getIntent().getStringExtra("ID");
        String category = getIntent().getStringExtra("Category");

        initialization();
        if (id != null & category != null) {
            binding.layoutToolbar.source.setText(category);
            getArticles(id, category);
        }
    }

    private void initialization() {
        binding.layoutToolbar.imageButton.setOnClickListener(view -> onBackPressed());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        adapter = new ArticleAdapter(getApplicationContext(), articleArrayList, this);
        binding.recyclerView.setAdapter(adapter);

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getArticles(String id, String category) {
        articleViewModel.getArticlesByResource(id, category).observe(this, articleResponse -> {
            if (articleResponse != null) {
                binding.progressBar.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                if (articles.size() > 0) {
                    articleArrayList.addAll(articles);
                    adapter.notifyDataSetChanged();
                    binding.recyclerView.scheduleLayoutAnimation();
                } else {
                    setViewFailed(AppConstant.SUCCESS_EMPTY);
                }
            } else {
                setViewFailed(AppConstant.FAILED);
            }
        });
    }

    private void setViewFailed(Integer successEmpty) {
        if (Objects.equals(successEmpty, AppConstant.SUCCESS_EMPTY)) {
            binding.imageFailed.setImageResource(R.drawable.ic_no_data);
        } else {
            binding.imageFailed.setImageResource(R.drawable.ic_error);
        }
        binding.progressBar.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);
        binding.imageFailed.setVisibility(View.VISIBLE);
    }

    @Override
    public void doGetDetailView(Article article) {
        Intent intent = new Intent(this, DetailArticleActivity.class);
        intent.putExtra("Article", article);
        startActivity(intent);
    }
}