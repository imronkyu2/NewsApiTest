package com.example.newsapitest.view.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapitest.R;
import com.example.newsapitest.databinding.ActivityMainBinding;
import com.example.newsapitest.view.home.adapter.ViewPagerAdapter;
import com.example.newsapitest.view.home.fragment.category.CategoryFragment;
import com.example.newsapitest.view.home.fragment.home.HomeFragment;
import com.example.newsapitest.view.home.fragment.wishlist.WishlistFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViewPager();
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new HomeFragment(), "Home", R.drawable.ic_news_active, R.drawable.ic_news);
        adapter.addFragment(new CategoryFragment(), "Category", R.drawable.ic_category_active, R.drawable.ic_category);
        adapter.addFragment(new WishlistFragment(), "Wishlist", R.drawable.ic_wishlist_active, R.drawable.ic_wishlist);

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setTabTextColors(getColor(R.color.blue1C274C), getColor(R.color.purple));
        binding.tabLayout.setSelectedTabIndicatorColor(getColor(R.color.purple));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    tab.setText(adapter.getFragmentTitle(position));
                    tab.setIcon(adapter.getFragmentIconActive(position));
                }).attach();
    }
}