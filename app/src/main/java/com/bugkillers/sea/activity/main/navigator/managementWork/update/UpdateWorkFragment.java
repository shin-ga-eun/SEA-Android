package com.bugkillers.sea.activity.main.navigator.managementWork.update;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.domain.dto.artItem.GetArtItemDto;
import com.bugkillers.sea.domain.dto.artItem.RelayArtItemDto;
import com.bugkillers.sea.domain.dto.artItem.UpdateArtItemDto;
import com.bugkillers.sea.network.NetRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateWorkFragment extends Fragment {



    MainActivity mainActivity;

    InputMethodManager imm;
    ImageView imgArtWork;
    TextView textArtist;
    EditText  edtTitle, edtDescription, edtPrice;
    Button btnArtWork, btnSave;

    static final int UPDATE = 0;
    static final int LIST = 1;
    static final int DETAIL = 2;

    //MainActivity 참조
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    //keyboard hide method
    private void hideKeyboard(){
        imm.hideSoftInputFromWindow(edtTitle.getWindowToken(),0);
        imm.hideSoftInputFromWindow(edtDescription.getWindowToken(),0);
        imm.hideSoftInputFromWindow(edtPrice.getWindowToken(),0);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_updatework, container, false);

        imgArtWork = (ImageView) root.findViewById(R.id.imgArtWork);
        btnArtWork = (Button) root.findViewById(R.id.btnArtWork);
        btnSave = (Button) root.findViewById(R.id.btnSave);
        textArtist = (TextView) root.findViewById(R.id.textArtist);
        edtTitle = (EditText) root.findViewById(R.id.edtTitle);
        edtDescription = (EditText) root.findViewById(R.id.edtDescription);
        edtPrice = (EditText) root.findViewById(R.id.edtPrice);
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);


        final RelayArtItemDto relayArtItemDto = mainActivity.getRelayArtItemDto();
        textArtist.setText(relayArtItemDto.getArtist());
        edtTitle.setText(relayArtItemDto.getTitle());
        edtDescription.setText(relayArtItemDto.getDescription());
        edtPrice.setText(""+relayArtItemDto.getPrice());

        //btnArtWork click
        btnArtWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"이미지 수정버튼 click",Toast.LENGTH_SHORT).show();
            }
        });

        //btnSave click
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateArtItemDto updateArtItemDto = new UpdateArtItemDto();

                updateArtItemDto.setTitle(edtTitle.getText().toString());
                updateArtItemDto.setDescription(edtDescription.getText().toString());
                updateArtItemDto.setPrice(Integer.parseInt(edtPrice.getText().toString()));

                Call<Void> response = NetRetrofit.getInstance().getNetRetrofitInterface().updateArtItem(relayArtItemDto.getAno(), updateArtItemDto);

                response.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Log.d("Retrofit update artItem", response.toString());
                            Toast.makeText(getContext(),"작품 정보수정 성공..", Toast.LENGTH_SHORT).show();
                        }

                        //keyboard hide
                        hideKeyboard();

                        //convert fragment
                        mainActivity.onFragmentChange(LIST);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(),"작품 정보수정 실패..", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        return root;
    }
}
