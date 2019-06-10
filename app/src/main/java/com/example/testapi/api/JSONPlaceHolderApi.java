package com.example.testapi.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {

    @GET(".")
    public Call<Get> getList();
}
