package com.example.sertifikasi_android.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.2.227/sertifikasi/apimobilejunior/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        return service;
    }

}
