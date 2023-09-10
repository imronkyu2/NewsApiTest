package com.example.newsapitest.view.submenu.source;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapitest.databinding.AdapterSourcesBinding;
import com.example.newsapitest.model.source.SourcesItem;

import java.util.ArrayList;
import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    private final List<SourcesItem> sourcesItems;
    private final SourcesAdapterViewContract mView;

    public SourcesAdapter(ArrayList<SourcesItem> sourcesItemsList, SourcesAdapterViewContract viewContract) {
        this.sourcesItems = sourcesItemsList;
        this.mView = viewContract;
    }

    @NonNull
    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterSourcesBinding binding = AdapterSourcesBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SourcesAdapter.ViewHolder holder, int i) {
        SourcesItem source = sourcesItems.get(i);
        holder.binding.name.setText(source.getName());
        holder.binding.url.setText(source.getUrl());
        holder.binding.category.setText(source.getCategory());
        holder.binding.layout.setOnClickListener(view -> mView.doGetArticel(source.getId()));

    }

    @Override
    public int getItemCount() {
        return sourcesItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterSourcesBinding binding;

        public ViewHolder(@NonNull AdapterSourcesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}