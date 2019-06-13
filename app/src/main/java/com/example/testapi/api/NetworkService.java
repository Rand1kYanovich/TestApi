package com.example.testapi.api;

import android.net.Uri;

import java.net.URI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://glabstore.blob.core.windows.net/test/";
    private Retrofit mRetrofit;

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if(mInstance==null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi(){
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }

    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return okClient;
    }
}
