package com.bugkillers.sea.activity.main.navigator.listWork;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListWorkView extends ViewModel {

    private MutableLiveData<String> mText;

    public ListWorkView() {
        mText = new MutableLiveData<>();
        mText.setValue("작품리스트");
    }

    public LiveData<String> getText() {
        return mText;
    }
}