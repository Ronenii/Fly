package com.GroupC.fly;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentBlogPost extends Fragment {

    private FragmentBlogPostViewModel mViewModel;

    public static FragmentBlogPost newInstance() {
        return new FragmentBlogPost();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_blog_post, container, false);
    }

    /*
    * Remove the attribute later.
    * */

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentBlogPostViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onPickImage(View view) {

    }

    public void onUpload(View view) {
    }
}