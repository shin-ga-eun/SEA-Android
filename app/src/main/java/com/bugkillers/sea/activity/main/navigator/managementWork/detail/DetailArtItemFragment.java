package com.bugkillers.sea.activity.main.navigator.managementWork.detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.domain.dto.artItem.GetArtItemDto;
import com.bugkillers.sea.domain.dto.artItem.RelayArtItemDto;
import com.bugkillers.sea.network.NetRetrofit;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailArtItemFragment extends Fragment {

    MainActivity mainActivity;

    TextView textArtist, textTitle, textDescription, textPrice;
    Button btnUpdate, btnDelete;
    GetArtItemDto getArtItemDto;


    static final int UPDATE = 0;
    static final int LIST = 1;
    static final int DETAIL = 2;

    //MainActivity 참조
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_detail_artitem, container, false);

        textArtist = (TextView) root.findViewById(R.id.textArtist);
        textTitle = (TextView) root.findViewById(R.id.textTitle);
        textDescription = (TextView) root.findViewById(R.id.textDescription);
        textPrice = (TextView) root.findViewById(R.id.textPrice);
        btnUpdate = (Button) root.findViewById(R.id.btnUpdate);
        btnDelete = (Button) root.findViewById(R.id.btnDelete);

        //select artItem by ano
        final Long ano = Long.parseLong("2"); //test

        Call<GetArtItemDto> response= NetRetrofit.getInstance().getNetRetrofitInterface().getArtItem(ano);
        response.enqueue(new Callback<GetArtItemDto>() {
            @Override
            public void onResponse(Call<GetArtItemDto> call, Response<GetArtItemDto> response) {
                if(response.isSuccessful()) {
                    Log.d("Retrofit get detail artItem",response.toString());

                    getArtItemDto = response.body();
                    textArtist.setText(getArtItemDto.getArtist());
                    textTitle.setText(getArtItemDto.getTitle());
                    textDescription.setText(getArtItemDto.getDescription());
                    textPrice.setText(""+getArtItemDto.getPrice());
                }

            }
            @Override
            public void onFailure(Call<GetArtItemDto> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        //btnUpdate click
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelayArtItemDto relayArtItemDto = new RelayArtItemDto();
                relayArtItemDto.setAno(ano);
                relayArtItemDto.setArtist(getArtItemDto.getArtist());
                relayArtItemDto.setTitle(getArtItemDto.getTitle());
                relayArtItemDto.setDescription(getArtItemDto.getDescription());
                relayArtItemDto.setPrice(getArtItemDto.getPrice());

                mainActivity.setRelayArtItemDto(relayArtItemDto);
                mainActivity.onFragmentChange(UPDATE);
            }
        });

        //btnDelete click
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                alert_confirm.setMessage("작품 리스트에서 삭제하시겠습니까?\n\n (현재 대여중인 작품이라면 대여기간 종료 후, 다시 시도해주세요..)").setCancelable(false).setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //delete retrofit
                                Call<Void> response= NetRetrofit.getInstance().getNetRetrofitInterface().deleteArtItem(ano);
                                response.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()) {
                                            Log.d("Retrofit delete artItem", response.toString());
                                            Toast.makeText(getContext(), "카드를 성공적으로 삭제하였습니다.", Toast.LENGTH_SHORT).show();
                                            mainActivity.onFragmentChange(LIST);
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Log.d("Err", t.getMessage());
                                        Toast.makeText(getContext(), "카드삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                                    }

                                });

                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();

            }
        });



        return root;
    }
}