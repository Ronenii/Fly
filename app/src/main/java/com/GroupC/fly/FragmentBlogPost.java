package com.GroupC.fly;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Map;

import com.GroupC.fly.data.Objects.MasterPost;

public class FragmentBlogPost extends Fragment {
    private enum UserOpt { TAKE_A_PHOTO, CHOOSE_FROM_GALLERY, CANCEL };
    private FragmentBlogPostViewModel mViewModel;
    private ActivityResultLauncher<Intent> mMediaLauncher;

    private Uri mImgUri;
    private TextInputEditText mPostTitle;
    private TextInputEditText mPostDesc;

    public static FragmentBlogPost newInstance() {
        return new FragmentBlogPost();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blog_post, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMediaLauncher = getActivityLauncher();
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
        Button mBtnUpload = getActivity().findViewById(R.id.buttonUpload);

        mPostTitle = getActivity().findViewById(R.id.addPostTitle);
        mPostDesc = getActivity().findViewById(R.id.addPostDesc);

        uploadImg.setOnClickListener(this::onPickImage);
        mPostTitle.setOnClickListener(this::onUpdateText);
        mPostTitle.setOnClickListener(this::onUpdateText);
        mBtnUpload.setOnClickListener(this::onUpload);
    }

    public void onPickImage(View view) {
        final CharSequence[] opt = {"Take a Photo", "Choose from gallery", "Cancel"};
        final Activity hostingActivity = getActivity();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(hostingActivity);

        dialogBuilder
                .setTitle("Add a photo")
                .setItems(opt, (dialogInterface, pick) -> {
                    // Building a hash table of Integer (reflecting pick) and Runnable.
                    // Intent to remove unnecessary if's.
                    final Map<Integer, Runnable> optDispatchTable = new Hashtable<>();

                    optDispatchTable.put(UserOpt.TAKE_A_PHOTO.ordinal(), () -> dispatchCameraSnapShot(hostingActivity));
                    optDispatchTable.put(UserOpt.CHOOSE_FROM_GALLERY.ordinal(), this::dispatchChooseFromGallery);
                    optDispatchTable.put(UserOpt.CANCEL.ordinal(), () -> dispatchCancel(dialogInterface));

                    optDispatchTable.get(pick).run();
                })
                .show();
    }

    // Function for when the user pick the cancel option.
    private void dispatchCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
    }

    // Function for opening the gallery.
    private void dispatchChooseFromGallery() {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mMediaLauncher.launch(iGallery);
    }

    // Function for opening the camera for a picture.
    private void dispatchCameraSnapShot(Activity hostingActivity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        assert hostingActivity != null;

        if (intent.resolveActivity(hostingActivity.getPackageManager()) != null) {
            // Accessing the storage directory.
            File storageDir = hostingActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            // Making an image file to store the image with a unique name.
            File imgFile = new File(storageDir, makeUniqueFileName());
            // Creating a Universal Resource Identifier object for the image.
            mImgUri = FileProvider.getUriForFile(
                    hostingActivity,
                    BuildConfig.APPLICATION_ID + ".provider",
                    imgFile
            );

            Log.v("[test]:", imgFile.getAbsolutePath());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImgUri);
            mMediaLauncher.launch(intent);
        }
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
                        ImageView postImgView = getActivity().findViewById(R.id.addPostImg);
                        postImgView.setImageURI(mImgUri);
                    } else {
                        Log.d("[debug]:", String.valueOf(result.getResultCode()));
                    }
                });
    }

    public void onUpdateText(View view) {
        mPostTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPostTitle.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public MasterPost onUpload(View view) {
        MasterPost postData = new MasterPost();

        postData.setPostTitle(mPostTitle);
        postData.setPostDesc(mPostDesc);
        postData.setImgUri(mImgUri);

        Log.i("[onUpload]:", mPostTitle.getText().toString());
        Log.i("[onUpload]:", mPostDesc.getText().toString());
        Log.i("[onUpload]:", mImgUri.getPath());

        return postData;
    }

    // Making a unique file name.
    private String makeUniqueFileName() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH:mm:ss");

            return formatter.format(now).replace(File.separator, "") + ".jpg";
        }
        return "userImage.jpg";
    }
}