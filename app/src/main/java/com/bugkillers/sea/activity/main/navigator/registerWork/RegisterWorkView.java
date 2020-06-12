package com.bugkillers.sea.activity.main.navigator.registerWork;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterWorkView extends ViewModel {

    private MutableLiveData<String> mText;

    public RegisterWorkView() {
        mText = new MutableLiveData<>();
        mText.setValue("작품등록화면");
    }

    public LiveData<String> getText() {
        return mText;
    }
}