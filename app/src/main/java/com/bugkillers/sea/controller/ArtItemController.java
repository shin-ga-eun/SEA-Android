package com.bugkillers.sea.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bugkillers.sea.domain.dto.artItem.SaveArtItemDto;
import com.bugkillers.sea.network.NetRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtItemController {

    Context context;

    public ArtItemController(Context context) {
        this.context = context;
    }


    public Boolean saveArtItem(SaveArtItemDto saveArtItemDto){

        Call<Void> response= NetRetrofit.getInstance().getNetRetrofitInterface().saveArtItem(saveArtItemDto);
        response.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("Retrofit", response.toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Err", t.getMessage());

            }
        });

        if(response.isCanceled()){
            return false;
        }

        return true;
    }
}
