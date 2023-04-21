package com.GroupC.fly.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.GroupC.fly.ActivityLogic.values;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
    public HashService() {}

    public String getHashed(String pwd) {
        return digestPassword(pwd);
    }

    /**
     * Creates a SHA-256 hash of the password to encrypt it.
     * */
    private String digestPassword(@NonNull String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(values.SHA_TYPE);
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = md.digest(passwordBytes);

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            // handle exception
            Log.v("[digestPasswordError]:", e.toString());
            return null;
        }
    }
}
