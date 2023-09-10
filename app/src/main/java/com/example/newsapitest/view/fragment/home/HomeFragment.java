package com.example.newsapitest.view.fragment.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newsapitest.R;
import com.example.newsapitest.databinding.FragmentHomeBinding;
import com.example.newsapitest.model.articel.Article;
import com.example.newsapitest.utils.AppConstant;
import com.example.newsapitest.view.submenu.article.ArticleAdapterViewContract;
import com.example.newsapitest.view.submenu.detail.DetailArticleActivity;
import com.example.newsapitest.view.submenu.search.SearchActivity;
import com.example.newsapitest.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements ArticleAdapterViewContract {
    private FragmentHomeBinding binding;
    private HomeListAdapter adapter;
    private final ArrayList<Article> articleArrayList = new ArrayList<>();
    private ArticleViewModel articleViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        initialization();
        getArticles();
        return rootView;
    }


    private void initialization() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.myRecyclerView.setLayoutManager(layoutManager);
        binding.myRecyclerView.setHasFixedSize(true);

        adapter = new HomeListAdapter(requireContext(), articleArrayList, this);
        binding.myRecyclerView.setAdapter(adapter);

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        binding.imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(requireContext(), SearchActivity.class);
            startActivity(intent);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getArticles() {
        articleViewModel.getArticleResponseLiveData().observe(getViewLifecycleOwner(), articleResponse -> {
            if (articleResponse != null) {
                binding.progressBar.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                if (articles.size() > 0) {
                    articleArrayList.addAll(articles);
                    adapter.notifyDataSetChanged();
                    binding.myRecyclerView.scheduleLayoutAnimation();
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
        binding.myRecyclerView.setVisibility(View.GONE);
        binding.imageFailed.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void doGetDetailView(Article article) {
        Intent intent = new Intent(requireContext(), DetailArticleActivity.class);
        intent.putExtra("Article", article);
        startActivity(intent);
    }
}
