package com.example.sertifikasi_android.api;

import com.example.sertifikasi_android.model.ProdukModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api_get.php")
    Call<ArrayList<ProdukModel>> ambilData();
}
