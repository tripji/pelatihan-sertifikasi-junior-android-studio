package com.example.sertifikasi_android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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
