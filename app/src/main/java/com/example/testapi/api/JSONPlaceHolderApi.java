package com.example.testapi.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {

    @GET("list.json")
    public Call<GetTasksResponse> getList();

    @GET("{id}.json")
    public Call<SecondGetTasksResponse> getFullList(@Path("id") String id);
}
