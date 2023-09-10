package com.example.newsapitest.view.fragment.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.newsapitest.databinding.FragmentWishlistBinding;

public class WishlistFragment extends Fragment {

    private FragmentWishlistBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}