package com.example.newsapitest.view.submenu.source;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapitest.R;
import com.example.newsapitest.databinding.ActivitySourcesBinding;
import com.example.newsapitest.model.source.SourcesItem;
import com.example.newsapitest.utils.AppConstant;
import com.example.newsapitest.view.submenu.article.ArticleActivity;
import com.example.newsapitest.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SourcesActivity extends AppCompatActivity implements SourcesAdapterViewContract {
    private ActivitySourcesBinding binding;
    private SourcesAdapter adapter;
    private final ArrayList<SourcesItem> sourcesItems = new ArrayList<>();
    private ArticleViewModel viewModel;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySourcesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        category = getIntent().getStringExtra("Category");
        initialization();
        if (category != null) {
            binding.layoutToolbar.source.setText(category);
            getMovieArticles(category);
        }
    }

    private void initialization() {
        binding.layoutToolbar.imageButton.setOnClickListener(view -> onBackPressed());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        adapter = new SourcesAdapter(sourcesItems, this);
        binding.recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getMovieArticles(String category) {
        viewModel.getSourceResponseLiveData(category).observe(this, sourceResponse -> {
            if (sourceResponse != null) {
                binding.progressBar.setVisibility(View.GONE);
                List<SourcesItem> articles = sourceResponse.getSources();
                if (articles.size() > 0) {
                    sourcesItems.addAll(articles);
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
    public void doGetArticel(String id) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("Category", category);
        startActivity(intent);
    }
}