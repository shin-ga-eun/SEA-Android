package com.bugkillers.sea.network;

import com.bugkillers.sea.domain.dto.artItem.SaveArtItemDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @POST("/sea/artItem")
    Call<Void> saveArtItem (@Body SaveArtItemDto saveArtItemDto);

    @DELETE("/sea/artItem/{ano}")
    Call<Void> deleteArtItem (@Path("ano") Long ano);
}
