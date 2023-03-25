package com.GroupC.fly;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class FragmentBlogPost extends Fragment {
    private enum UserOpt { TAKE_A_PHOTO, CHOOSE_FROM_GALLERY, CANCEL }
    private FragmentBlogPostViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blog_post, container, false);

        // Get the view model (all the business logic is in there).
        mViewModel = new ViewModelProvider(this).get(FragmentBlogPostViewModel.class);
        mViewModel.mMediaLauncher = getActivityLauncher();

        // Create the variables.
        mViewModel.uploadImg = new WeakReference<>(Objects.requireNonNull(rootView).findViewById(R.id.addPostImg));
        mViewModel.mPostTitle = new WeakReference<>(Objects.requireNonNull(rootView).findViewById(R.id.addPostTitle));
        mViewModel.mPostDesc = new WeakReference<>(Objects.requireNonNull(rootView).findViewById(R.id.addPostDesc));
        mViewModel.mBtnUpload = new WeakReference<>(Objects.requireNonNull(rootView).findViewById(R.id.buttonUpload));

        // Register listeners
        mViewModel.uploadImg.get().setOnClickListener(this::onPickImage);
        mViewModel.mPostTitle.get().setOnClickListener(mViewModel::onUpdateText);
        mViewModel.mPostDesc.get().setOnClickListener(mViewModel::onUpdateText);
        mViewModel.mBtnUpload.get().setOnClickListener(mViewModel::onUpload);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onPickImage(View view) {
        final CharSequence[] opt = {"Take a Photo", "Choose from gallery", "Cancel"};
        final Activity hostingActivity = getActivity();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(hostingActivity);

        assert hostingActivity != null;

        dialogBuilder
                .setTitle("Add a photo")
                .setItems(opt, (dialogInterface, pick) -> {
                    // Building a hash table of Integer (reflecting pick) and Runnable.
                    // Intent to remove unnecessary if's.
                    final Map<Integer, Runnable> optDispatchTable = new Hashtable<>();

                    optDispatchTable.put(UserOpt.TAKE_A_PHOTO.ordinal(), () -> mViewModel.dispatchCameraSnapShot(hostingActivity));
                    optDispatchTable.put(UserOpt.CHOOSE_FROM_GALLERY.ordinal(), () -> mViewModel.dispatchChooseFromGallery());
                    optDispatchTable.put(UserOpt.CANCEL.ordinal(), () -> mViewModel.dispatchCancel(dialogInterface));

                    Objects.requireNonNull(optDispatchTable.get(pick)).run();
                })
                .show();
    }

    // Function for getting an activity launcher.
    // Used to launch the camera and gallery.
    private ActivityResultLauncher<Intent> getActivityLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // On success store the image, and update the ImageView.
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.i("[info]:", "Took a photo");
                        ImageView postImgView = Objects.requireNonNull(getActivity()).findViewById(R.id.addPostImg);
                        postImgView.setImageURI(mViewModel.mImgUri);
                    } else {
                        Log.d("[debug]:", String.valueOf(result.getResultCode()));
                    }
                }
        );
    }
}