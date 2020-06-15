package com.bugkillers.sea.network;


import android.content.SharedPreferences;

import com.bugkillers.sea.domain.dto.artItem.SaveArtItemDto;
import com.bugkillers.sea.domain.dto.member.IsMemberDto;
import com.bugkillers.sea.domain.dto.member.LoginResponseDto;
import com.bugkillers.sea.domain.dto.member.MemberJoinDto;
import com.bugkillers.sea.domain.dto.member.MemberLoginDto;

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

    @POST("/sea/join")
    Call<Void> joinMember (@Body MemberJoinDto memberJoinDto);

    @POST("/sea/login")
    Call<LoginResponseDto> loginMember (@Body MemberLoginDto memberLoginDto);

    @POST("/sea/isMember")
    Call<MemberLoginDto> isMember (@Body IsMemberDto isMemberDto);

    @POST("/sea/artist/test")
    Call<String> artistTest ();
}
