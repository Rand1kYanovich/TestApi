package com.example.testapi.api;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {

    @GET("list.json")
    public Call<Get> getList();
}
