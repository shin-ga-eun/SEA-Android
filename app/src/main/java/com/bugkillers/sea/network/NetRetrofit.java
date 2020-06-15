package com.bugkillers.sea.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {

    private static final NetRetrofit ourInstance = new NetRetrofit();

    public static NetRetrofit getInstance() {
        return ourInstance;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            //Json 파일을 자바오브젝트로 변환시킬수 있게 Gson을 사용
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitInterface netRetrofitInterface;

    public RetrofitInterface getNetRetrofitInterface() {
        if (netRetrofitInterface == null) {
            netRetrofitInterface = retrofit.create(RetrofitInterface.class);
        }

        return netRetrofitInterface;
    }
}
