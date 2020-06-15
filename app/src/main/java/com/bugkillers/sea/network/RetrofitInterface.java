package com.bugkillers.sea.network;

import com.bugkillers.sea.domain.dto.artItem.GetAnoAndTitleDto;
import com.bugkillers.sea.domain.dto.artItem.GetArtItemDto;
import com.bugkillers.sea.domain.dto.artItem.SaveArtItemDto;
import com.bugkillers.sea.domain.dto.artItem.UpdateArtItemDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @POST("/sea/artItem")
    Call<Void> saveArtItem (@Body SaveArtItemDto saveArtItemDto);

    @DELETE("/sea/artItem/{ano}")
    Call<Void> deleteArtItem (@Path("ano") Long ano);

    @GET("/sea/artItem/{ano}")
    Call<GetArtItemDto> getArtItem (@Path("ano") Long ano);

    @PUT("/sea/artItem/{ano}")
    Call<Void> updateArtItem (@Path("ano") Long ano, @Body UpdateArtItemDto updateArtItemDto);

    @GET("/sea/artItem/{ano}")
    Call<GetAnoAndTitleDto> getListArtItem (@Path("ano") Long ano);


}
