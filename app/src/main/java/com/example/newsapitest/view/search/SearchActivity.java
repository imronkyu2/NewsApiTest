package com.example.newsapitest.view.search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapitest.R;
import com.example.newsapitest.databinding.ActivitySearchBinding;
import com.example.newsapitest.model.articel.Article;
import com.example.newsapitest.utils.AppConstant;
import com.example.newsapitest.view.article.ArticleAdapterViewContract;
import com.example.newsapitest.view.detail.DetailArticleActivity;
import com.example.newsapitest.view.home.fragment.home.HomeListAdapter;
import com.example.newsapitest.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements ArticleAdapterViewContract {
    private ActivitySearchBinding binding;
    private HomeListAdapter adapter;
    private ArticleViewModel articleViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialization();
    }

    private void initialization() {
        binding.layoutToolbar.imageButton.setOnClickListener(view -> onBackPressed());
        binding.layoutToolbar.source.setText(getString(R.string.Search));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        binding.search.setOnClickListener(v -> binding.search.setIconified(false));

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.progressBar.setVisibility(View.VISIBLE);
                getSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getSearch(String query) {
        articleViewModel.getSearchArticleResponseLiveData(query).observe(this, articleResponse -> {
            if (articleResponse != null) {
                binding.progressBar.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                if (articles.size() > 0) {
                    adapter = new HomeListAdapter(getApplicationContext(), (ArrayList<Article>) articles, this);
                    binding.recyclerView.setAdapter(adapter);
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