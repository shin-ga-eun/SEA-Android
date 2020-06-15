package com.bugkillers.sea.network;

import com.bugkillers.sea.domain.dto.artItem.SaveArtItemDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/sea/artItem")
    Call<Void> saveArtItem (@Body SaveArtItemDto saveArtItemDto);
}
