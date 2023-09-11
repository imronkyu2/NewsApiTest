package com.example.newsapitest.view.home.fragment.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.newsapitest.R;
import com.example.newsapitest.databinding.FragmentCategoryBinding;
import com.example.newsapitest.model.category.CategoryItem;
import com.example.newsapitest.view.source.SourcesActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryAdapterViewContract {
    private FragmentCategoryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        setupView();
        return rootView;
    }

    private void setupView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        List<CategoryItem> categoryList = generateCategoryData();
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categoryList, this);
        binding.recyclerView.setAdapter(categoryAdapter);
        binding.recyclerView.scheduleLayoutAnimation();

    }

    private List<CategoryItem> generateCategoryData() {
        List<CategoryItem> categoryList = new ArrayList<>();
        categoryList.add(new CategoryItem("Business", R.drawable.ic_business));
        categoryList.add(new CategoryItem("Entertainment", R.drawable.ic_entertainment));
        categoryList.add(new CategoryItem("General", R.drawable.ic_general));
        categoryList.add(new CategoryItem("Health", R.drawable.ic_health));
        categoryList.add(new CategoryItem("Sciences", R.drawable.ic_sciences));
        categoryList.add(new CategoryItem("Technology", R.drawable.ic_teknology));
        return categoryList;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void doGetSource(String categoryItem) {
        Intent intent = new Intent(requireContext(), SourcesActivity.class);
        intent.putExtra("Category", categoryItem);
        startActivity(intent);
    }
}