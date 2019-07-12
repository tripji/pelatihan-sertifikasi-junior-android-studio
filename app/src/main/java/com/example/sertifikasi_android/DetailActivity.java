package com.example.sertifikasi_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sertifikasi_android.api.ApiConfig;
import com.example.sertifikasi_android.api.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class DetailActivity extends AppCompatActivity {

    private String idBarang;
    private String namaBarang;
    private String imageBarang;
    private String deskripsiBarang;
    private String hargaBarang;
    private String stokBarang;

    private ImageView iv;
    private TextView tcNamaBarang;
    private TextView tcDeskripsiBarang;
    private TextView tcHargaBarang;
    private TextView tcStokBarang;
    private Button btnKirim;
    private Button btnEditBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();

        idBarang = getIntent().getStringExtra("ID_BARANG");
        namaBarang = getIntent().getStringExtra("NAMA_BARANG");
        imageBarang = getIntent().getStringExtra("IMAGE_BARANG");
        deskripsiBarang = getIntent().getStringExtra("DESKRIPSI_BARANG");
        hargaBarang = getIntent().getStringExtra("HARGA_BARANG");
        stokBarang = getIntent().getStringExtra("STOK_BARANG");

        Glide.with(this).load(imageBarang).override(512, 512)
                .into(iv);
        tcNamaBarang.setText(namaBarang);
        tcDeskripsiBarang.setText(deskripsiBarang);
        tcHargaBarang.setText(hargaBarang);
        tcStokBarang.setText(stokBarang);

/*
if (Integer.parseInt(String.valueOf(tcStokBarang)) == 0) {
btnKirim.setVisibility(GONE);
} else {
btnKirim.setVisibility(View.VISIBLE);
}
*/

        btnKirim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiService apiService = ApiConfig.getApiService();
                    apiService.beliData(idBarang).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(Integer.parseInt(stokBarang)<=0) {
                                btnKirim.setVisibility(View.INVISIBLE);
                                Toast.makeText(DetailActivity.this, "Stok Habis", Toast.LENGTH_SHORT).show();
                            } else {
                                btnKirim.setVisibility(View.VISIBLE);
                            if (response.isSuccessful()) {
                                    Toast.makeText(DetailActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finishAffinity();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(DetailActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            btnEditBarang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                ApiService apiService = ApiConfig.getApiService();
//                apiService.editData(idBarang).enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful()) {
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });
                    Intent intent = new Intent(DetailActivity.this, UpdateActivity.class);
                    intent.putExtra("ID_BARANG", idBarang);
                    intent.putExtra("NAMA_BARANG", namaBarang);
                    intent.putExtra("IMAGE_BARANG", imageBarang);
                    intent.putExtra("DESKRIPSI_BARANG", deskripsiBarang);
                    intent.putExtra("HARGA_BARANG", hargaBarang);
                    intent.putExtra("STOK_BARANG", stokBarang);
                    startActivity(intent);
                }
            });
        }


    private void initView() {
        iv = findViewById(R.id.iv);
        tcNamaBarang = findViewById(R.id.tcNamaBarang);
        tcDeskripsiBarang = findViewById(R.id.tcDeskripsiBarang);
        tcHargaBarang = findViewById(R.id.tcHargaBarang);
        tcStokBarang = findViewById(R.id.tcStokBarang);
        btnKirim = findViewById(R.id.btnKirim);
        btnEditBarang = findViewById(R.id.btnEditBarang);
    }
}
