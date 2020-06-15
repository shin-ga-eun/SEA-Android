package com.bugkillers.sea.activity.main.navigator.registerWork;

import android.app.IntentService;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
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
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.controller.ArtItemController;
import com.bugkillers.sea.domain.dto.artItem.SaveArtItemDto;
import com.bugkillers.sea.network.NetRetrofit;
import com.bugkillers.sea.network.RetrofitInterface;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterWorkFragment extends Fragment {

    static final int UPDATE = 0;
    static final int LIST = 1;

//    private ArtItemController artItemController;
    MainActivity mainActivity;

    InputMethodManager imm;
    ImageView imgArtWork;
    Button btnArtWork, btnSave;
    EditText edtArtist, edtTitle, edtDescription, edtPrice;

    //MainActivity 참조
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    //keyboard hide method
    private void hideKeyboard(){
        imm.hideSoftInputFromWindow(edtArtist.getWindowToken(),0);
        imm.hideSoftInputFromWindow(edtTitle.getWindowToken(),0);
        imm.hideSoftInputFromWindow(edtDescription.getWindowToken(),0);
        imm.hideSoftInputFromWindow(edtPrice.getWindowToken(),0);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View root = inflater.inflate(R.layout.fragment_registerwork, container, false);

        imgArtWork = (ImageView) root.findViewById(R.id.imgArtWork);
        btnArtWork = (Button) root.findViewById(R.id.btnArtWork);
        btnSave = (Button) root.findViewById(R.id.btnSave);
        edtArtist = (EditText) root.findViewById(R.id.edtArtist);
        edtTitle = (EditText) root.findViewById(R.id.edtTitle);
        edtDescription = (EditText) root.findViewById(R.id.edtDescription);
        edtPrice = (EditText) root.findViewById(R.id.edtPrice);
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

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
                final SaveArtItemDto saveArtItemDto = new SaveArtItemDto();

                saveArtItemDto.setArtist(edtArtist.getText().toString());
                saveArtItemDto.setTitle(edtTitle.getText().toString());
                saveArtItemDto.setDescription(edtDescription.getText().toString());
                saveArtItemDto.setPrice(Integer.parseInt(edtPrice.getText().toString()));

                Call<Void> response= NetRetrofit.getInstance().getNetRetrofitInterface().saveArtItem(saveArtItemDto);
                response.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("Retrofit register artItem", response.toString());
                        Toast.makeText(getContext(),"작품등록성공..",Toast.LENGTH_SHORT).show();

                        //keyboard hide
                        hideKeyboard();

                        //convert fragment
                        mainActivity.onFragmentChange(LIST);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("Err", t.getMessage());
                        Toast.makeText(getContext(),"작품등록실패..",Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });



        return root;
    }
}