package com.example.newsapitest.view.submenu.web;

import android.os.Bundle;
import android.webkit.WebSettings;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapitest.R;
import com.example.newsapitest.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.newsapitest.databinding.ActivityWebViewBinding binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String url = getIntent().getStringExtra("url");

        binding.layoutToolbar.imageButton.setOnClickListener(view -> onBackPressed());
        binding.layoutToolbar.source.setText(getText(R.string.WebView));

        if (url != null) {
            WebSettings webSettings = binding.webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            binding.webView.loadUrl(url);
        }
    }
}