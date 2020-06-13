package com.bugkillers.sea.activity.main.navigator.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatView extends ViewModel {

    private MutableLiveData<String> mText;

    public ChatView() {
        mText = new MutableLiveData<>();
        mText.setValue("채팅");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
