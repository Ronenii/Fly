package com.GroupC.fly;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModel;

import com.GroupC.fly.data.Objects.MasterPost;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.lang.ref.WeakReference;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FragmentBlogPostViewModel extends ViewModel {
    ActivityResultLauncher<Intent> mMediaLauncher;
    Uri mImgUri;
    WeakReference<TextInputEditText> mPostTitle;
    WeakReference<TextInputEditText> mPostDesc;
    WeakReference<Button> mBtnUpload;
    WeakReference<ImageView> uploadImg;


    // Function for when the user pick the cancel option.
     void dispatchCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
    }

    // Function for opening the gallery.
    void dispatchChooseFromGallery() {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mMediaLauncher.launch(iGallery);
    }

    // Function for opening the camera for a picture.
    void dispatchCameraSnapShot(Activity hostingActivity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

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

    public void onUpdateText(View view) {
        mPostTitle.get().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPostTitle.get().setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public MasterPost onUpload(View view) {
        MasterPost postData = new MasterPost();

        if (mPostTitle != null) {
            postData.setPostTitle(mPostTitle.get());
            Log.i("[onUpload]:", Objects.requireNonNull(mPostTitle.get().getText()).toString());
        }
        if (mPostDesc != null) {
            postData.setPostDesc(mPostDesc.get());
            Log.i("[onUpload]:", Objects.requireNonNull(mPostDesc.get().getText()).toString());
        }
        if (mImgUri != null) {
            postData.setImgUri(mImgUri);
            Log.i("[onUpload]:", mImgUri.getPath());
        }

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