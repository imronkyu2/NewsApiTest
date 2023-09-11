package com.example.newsapitest.view.article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.newsapitest.R;
import com.example.newsapitest.databinding.AdapterArticleBinding;
import com.example.newsapitest.model.articel.Article;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Article> articleArrayList;
    private final ArticleAdapterViewContract mView;

    public ArticleAdapter(Context context, ArrayList<Article> articleArrayList, ArticleAdapterViewContract viewContract) {
        this.context = context;
        this.articleArrayList = articleArrayList;
        this.mView = viewContract;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterArticleBinding binding = AdapterArticleBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Article article = articleArrayList.get(i);
        holder.binding.tvTitle.setText(article.getTitle());
        holder.binding.tvAuthorAndPublishedAt.setText(article.getAuthor());
        Glide.with(context)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.ic_no_image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder.binding.imgViewCover);

        holder.binding.layout.setOnClickListener(view -> mView.doGetDetailView(article));
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AdapterArticleBinding binding;

        public ViewHolder(@NonNull AdapterArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
