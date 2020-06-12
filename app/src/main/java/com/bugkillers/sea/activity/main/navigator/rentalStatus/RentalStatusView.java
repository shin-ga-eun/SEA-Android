package com.bugkillers.sea.activity.main.navigator.rentalStatus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RentalStatusView extends ViewModel {

    private MutableLiveData<String> mText;

    public RentalStatusView() {
        mText = new MutableLiveData<>();
        mText.setValue("대여현황");
    }

    public LiveData<String> getText() {
        return mText;
    }
}