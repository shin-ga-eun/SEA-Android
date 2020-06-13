package com.bugkillers.sea.activity.main.navigator.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bugkillers.sea.R;

public class ChatFragment extends Fragment {

    private ChatView chatView;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        chatView = ViewModelProviders.of(this).get(ChatView.class);
        final View root = inflater.inflate(R.layout.fragment_chat, container, false);

        return root;
    }
}



