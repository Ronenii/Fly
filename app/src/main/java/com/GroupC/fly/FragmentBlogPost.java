package com.GroupC.fly;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FragmentBlogPost extends Fragment {
    private enum UserOpt { TAKE_A_PHOTO, CHOOSE_FROM_GALLERY, CANCEL };
    private FragmentBlogPostViewModel mViewModel;
    private ActivityResultLauncher<Intent> mCameraLauncher;

    private Uri imgUri;

    public static FragmentBlogPost newInstance() {
        return new FragmentBlogPost();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_blog_post, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCameraLauncher = getActivityLauncher();
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

        ImageView uploadImg = getView().findViewById(R.id.addPostImg);
        uploadImg.setOnClickListener(this::onPickImage);
    }

    public void onPickImage(View view) {
        final CharSequence[] opt = {"Take a Photo", "Choose from gallery", "Cancel"};
        final Activity hostingActivity = getActivity();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(hostingActivity);

        dialogBuilder
                .setTitle("Add a photo")
                .setItems(opt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pick) {
                        // User chose to take a photo.
                        if (opt[pick].equals(opt[UserOpt.TAKE_A_PHOTO.ordinal()])) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            if (intent.resolveActivity(hostingActivity.getPackageManager()) != null) {
                                File storageDir = hostingActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                                File imgFile = new File(storageDir, makeUniqueFileName());
                                imgUri = FileProvider.getUriForFile(
                                        hostingActivity,
                                        BuildConfig.APPLICATION_ID + ".provider",
                                        imgFile
                                );

                                Log.v("[test]:", imgFile.getAbsolutePath());
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                                mCameraLauncher.launch(intent);
                            }
                        } else if (opt[pick].equals(opt[UserOpt.CHOOSE_FROM_GALLERY.ordinal()])) {

                        } else if (opt[pick].equals(opt[UserOpt.CANCEL.ordinal()])) {
                            dialogInterface.dismiss();
                        }
                    }
                });

        dialogBuilder.show();
    }

    private ActivityResultLauncher<Intent> getActivityLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.i("[info]:", "Took a photo");
                        ImageView postImgView = getActivity().findViewById(R.id.addPostImg);
                        postImgView.setImageURI(imgUri);
                    } else {
                        Log.d("[debug]:", String.valueOf(result.getResultCode()));
                    }
                });
    }

    private String makeUniqueFileName() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH:mm:ss");

            return formatter.format(now).replace("/", "") + ".jpg";
        }
        return "userImage.jpg";
    }

    public void onUpload(View view) {
    }
}