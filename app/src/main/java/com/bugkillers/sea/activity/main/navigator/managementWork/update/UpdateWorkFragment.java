package com.bugkillers.sea.activity.main.navigator.managementWork.update;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bugkillers.sea.R;


public class UpdateWorkFragment extends Fragment {

    ImageView imgArtWork;
    EditText edtArtist, edtTitle, edtDescription, edtPrice;
    Button btnArtWork, btnSave;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_updatework, container, false);

        imgArtWork = (ImageView) root.findViewById(R.id.imgArtWork);
        btnArtWork = (Button) root.findViewById(R.id.btnArtWork);
        btnSave = (Button) root.findViewById(R.id.btnSave);
        edtArtist = (EditText) root.findViewById(R.id.edtArtist);
        edtTitle = (EditText) root.findViewById(R.id.edtTitle);
        edtDescription = (EditText) root.findViewById(R.id.edtDescription);
        edtPrice = (EditText) root.findViewById(R.id.edtPrice);

        btnArtWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }
}
