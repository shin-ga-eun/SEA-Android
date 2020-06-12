package com.bugkillers.sea.activity.main.navigator.registerWork;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bugkillers.sea.R;

public class RegisterWorkFragment extends Fragment {

    private RegisterWorkView registerWorkView;
    ImageView imgArtWork;
    Button btnArtWork, btnSave;
    EditText edtArtist, edtTitle, edtDescription, edtPrice;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        registerWorkView = ViewModelProviders.of(this).get(RegisterWorkView.class);
        final View root = inflater.inflate(R.layout.fragment_registerwork, container, false);

        imgArtWork = (ImageView) root.findViewById(R.id.imgArtWork);
        btnArtWork = (Button) root.findViewById(R.id.btnArtWork);
        btnSave = (Button) root.findViewById(R.id.btnSave);
        edtArtist = (EditText) root.findViewById(R.id.edtArtist);
        edtTitle = (EditText) root.findViewById(R.id.edtTitle);
        edtDescription = (EditText) root.findViewById(R.id.edtDescription);
        edtPrice = (EditText) root.findViewById(R.id.edtPrice);

        //button - 이미지 등록
        btnArtWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(root.getContext(), "click", Toast.LENGTH_SHORT).show();

            }
        });

        //button - 작품 등록
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(root.getContext(), "save", Toast.LENGTH_SHORT).show();

            }
        });







//        registerWorkView.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//
//
//
//
//            }
//        });
        return root;
    }
}