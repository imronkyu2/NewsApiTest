package com.example.newsapitest.view.submenu.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.newsapitest.R;
import com.example.newsapitest.databinding.ActivityDetailArticleBinding;
import com.example.newsapitest.model.articel.Article;
import com.example.newsapitest.view.submenu.web.WebViewActivity;
import com.example.newsapitest.viewmodel.LocalArticleViewModel;

public class DetailArticleActivity extends AppCompatActivity {
    private ActivityDetailArticleBinding binding;
    private LocalArticleViewModel localArticleViewModel;
    private boolean save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialization();
    }

    private void initialization() {
        localArticleViewModel = ViewModelProviders.of(this).get(LocalArticleViewModel.class);
        binding.layoutToolbar.imageButton.setOnClickListener(view -> onBackPressed());
        Intent intent = getIntent();
        if (intent != null) {
            Article article = intent.getParcelableExtra("Article");
            if (article != null) {
                binding.layoutToolbar.source.setText(article.getAuthor());

                Glide.with(getApplicationContext())
                        .load(article.getUrlToImage())
                        .placeholder(R.drawable.ic_no_image)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade(500))
                        .into(binding.imgViewCover);

                binding.publishedAt.setText(article.getPublishedAt());
                binding.author.setText(article.getAuthor());
                binding.title.setText(article.getTitle());
                binding.description.setText(article.getDescription());
                binding.url.setText(article.getUrl());

                binding.url.setOnClickListener(view -> {
                    Intent intent1 = new Intent(this, WebViewActivity.class);
                    intent1.putExtra("url", article.getUrl());
                    startActivity(intent1);
                });


                binding.wishlist.setOnClickListener(view -> {
                    Toast.makeText(getApplicationContext(), "Belum Di Implement", Toast.LENGTH_LONG).show();
                });

            }
        }
    }

}