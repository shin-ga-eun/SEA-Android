package com.bugkillers.sea.network;


import com.bugkillers.sea.domain.dto.artItem.SaveArtItemDto;
import com.bugkillers.sea.domain.dto.member.IsMemberDto;
import com.bugkillers.sea.domain.dto.member.LoginResponseDto;
import com.bugkillers.sea.domain.dto.member.MemberJoinDto;
import com.bugkillers.sea.domain.dto.member.MemberLoginDto;
import com.bugkillers.sea.domain.dto.artItem.GetAnoAndTitleDto;
import com.bugkillers.sea.domain.dto.artItem.GetArtItemDto;
import com.bugkillers.sea.domain.dto.artItem.UpdateArtItemDto;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @POST("/sea/join")
    Call<Void> joinMember (@Body MemberJoinDto memberJoinDto);

    @POST("/sea/login")
    Call<LoginResponseDto> loginMember (@Body MemberLoginDto memberLoginDto);

    @POST("/sea/isMember")
    Call<MemberLoginDto> isMember (@Body IsMemberDto isMemberDto);

    @POST("/sea/artist/test")
    Call<String> artistTest ();


    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWR3bDc2NzZAbmF2ZXIuY29tIiwicm9sZXMiOiJDVVNUT01FUiIsImlhdCI6MTU5MjI1NTcxOCwiZXhwIjoxNTkyMjU3NTE4fQ.zy7NIbrA3U8Vd4G5O9K2R9qrJS_VHmX5-tojOphBn0mcmEaVFbfjXJbcdrCoW0cnVBOVOCSHsIgzPjUBIdwIeQ";

    @Headers({"TOKEN: "+token})
    @POST("/sea/artItem")
    Call<Void> saveArtItem (@Body SaveArtItemDto saveArtItemDto);

    @Headers({"TOKEN: "+token})
    @DELETE("/sea/artItem/{ano}")
    Call<Void> deleteArtItem (@Path("ano") Long ano);

    @Headers({"TOKEN: "+token})
    @GET("/sea/artItem/{ano}")
    Call<GetArtItemDto> getArtItem (@Path("ano") Long ano);

    @Headers({"TOKEN: "+token})
    @PUT("/sea/artItem/{ano}")
    Call<Void> updateArtItem (@Path("ano") Long ano, @Body UpdateArtItemDto updateArtItemDto);

    @Headers({"TOKEN: "+token})
    @GET("/sea/artItem")
    Call<List<GetAnoAndTitleDto>> getListArtItem ();

}
