package com.GroupC.fly.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.GroupC.fly.Utils.data.Objects.User;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<User> mUser = new MutableLiveData<>();

    public void setUser(User user) { mUser.setValue(user); }
    public LiveData<User> getUser() {
        return mUser;
    }
}