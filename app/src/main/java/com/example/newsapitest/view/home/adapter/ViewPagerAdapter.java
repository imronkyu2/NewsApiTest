package com.example.newsapitest.view.home.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    private final List<Integer> fragmentIconActiveList = new ArrayList<>();
    private final List<Integer> fragmentIconInactiveList = new ArrayList<>();

    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment fragment, String title, int iconActiveResId, int iconInactiveResId) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
        fragmentIconActiveList.add(iconActiveResId);
        fragmentIconInactiveList.add(iconInactiveResId);
    }

    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public String getFragmentTitle(int position) {
        return fragmentTitleList.get(position);
    }

    public int getFragmentIconActive(int position) {
        return fragmentIconActiveList.get(position);
    }

    public int getFragmentIconInactive(int position) {
        return fragmentIconInactiveList.get(position);
    }
}
