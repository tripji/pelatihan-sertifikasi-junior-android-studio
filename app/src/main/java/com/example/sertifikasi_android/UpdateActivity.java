package com.example.sertifikasi_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sertifikasi_android.api.ApiConfig;
import com.example.sertifikasi_android.api.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private String idBarang;
    private String namaBarang;
    private String imageBarang;
    private String deskripsiBarang;
    private String hargaBarang;
    private String stokBarang;
    private ImageView iconItemName;
    private EditText editNamaBarang;
    private ImageView iconHarga;
    private EditText editHargaBarang;
    private ImageView iconMinus;
    private ImageView iconPlus;
    private EditText editStok;
    private EditText editDeskripsiBarang;
    private ImageView ivImage;
    private EditText editImgUrl;
    private Button btnCekUrl;
    private Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();

        idBarang = getIntent().getStringExtra("ID_BARANG");
        namaBarang = getIntent().getStringExtra("NAMA_BARANG");
        imageBarang = getIntent().getStringExtra("IMAGE_BARANG");
        deskripsiBarang = getIntent().getStringExtra("DESKRIPSI_BARANG");
        hargaBarang = getIntent().getStringExtra("HARGA_BARANG");
        stokBarang = getIntent().getStringExtra("STOK_BARANG");

        editNamaBarang.setText(namaBarang);
        editImgUrl.setText(imageBarang);
        editDeskripsiBarang.setText(deskripsiBarang);
        editHargaBarang.setText(hargaBarang);
        editStok.setText(stokBarang);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBarang();
//                ApiService apiService = ApiConfig.getApiService();
//                apiService.tambahData(editNamaBarang.getText().toString().trim(),
//                        editImgUrl.getText().toString().trim(),
//                        editHargaBarang.getText().toString().trim(),
//                        editStok.getText().toString().trim(),
//                        editDeskripsiBarang.getText().toString().trim())
//                        .enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                                if (response.isSuccessful()) {
//                                    Toast.makeText(UpdateActivity.this, "Sukses Tambah",
//                                            Toast.LENGTH_SHORT).show();
//                                    editNamaBarang.setText("");
//                                    editImgUrl.setText("");
//                                    editHargaBarang.setText("");
//                                    editStok.setText("");
//                                    editDeskripsiBarang.setText("");
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Toast.makeText(UpdateActivity.this, "" +t.getMessage(),
//                                        Toast.LENGTH_SHORT).show();
//
//                            }
//                        });

            }


        });

        btnCekUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(UpdateActivity.this).load(editImgUrl.getText().toString().trim()).override(512,512).into(ivImage);
            }
        });

    }
    private void updateBarang() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.editData(idBarang,
                editNamaBarang.getText().toString().trim(),
                editImgUrl.getText().toString().trim(),
                editDeskripsiBarang.getText().toString().trim(),
                editHargaBarang.getText().toString().trim(),
                editStok.getText().toString().trim())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(UpdateActivity.this, "Data Sukses Diperbarui",
                                    Toast.LENGTH_SHORT).show();
                            finishAffinity();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(UpdateActivity.this, "" +t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void initView() {
        iconItemName = findViewById(R.id.icon_item_name);
        editNamaBarang = findViewById(R.id.edit_nama_barang);
        iconHarga = findViewById(R.id.icon_harga);
        editHargaBarang = findViewById(R.id.edit_harga_barang);
        iconMinus = findViewById(R.id.icon_minus);
        iconPlus = findViewById(R.id.icon_plus);
        editStok = findViewById(R.id.edit_stok);
        editDeskripsiBarang = findViewById(R.id.edit_deskripsi_barang);
        ivImage = findViewById(R.id.iv_image);
        editImgUrl = findViewById(R.id.edit_img_url);
        btnCekUrl = findViewById(R.id.btn_cek_url);
        btnKirim = findViewById(R.id.btn_kirim);
    }
}
