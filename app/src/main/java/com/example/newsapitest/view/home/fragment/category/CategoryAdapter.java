package com.example.newsapitest.view.home.fragment.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapitest.databinding.AdapterCategoryBinding;
import com.example.newsapitest.model.category.CategoryItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<CategoryItem> categoryList;
    private final Context context;
    private final CategoryAdapterViewContract mView;

    public CategoryAdapter(Context context, List<CategoryItem> categoryList, CategoryAdapterViewContract viewContract) {
        this.context = context;
        this.categoryList = categoryList;
        this.mView = viewContract;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterCategoryBinding binding = AdapterCategoryBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new CategoryAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryItem categoryItem = categoryList.get(position);
        holder.binding.tvTitle.setText(categoryItem.getCategoryName());
        holder.binding.image.setImageResource(categoryItem.getCategoryIcon());
        holder.binding.layout.setOnClickListener(view -> mView.doGetSource(categoryItem.getCategoryName()));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterCategoryBinding binding;

        public ViewHolder(@NonNull AdapterCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}