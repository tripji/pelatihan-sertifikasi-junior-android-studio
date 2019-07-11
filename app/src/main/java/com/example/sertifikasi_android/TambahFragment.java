package com.example.sertifikasi_android;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.sertifikasi_android.api.ApiConfig;
import com.example.sertifikasi_android.api.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TambahFragment extends Fragment {


    private ImageView iconItemName;
    private EditText editNamaBarang;
    private ImageView iconHarga;
    private EditText editHargaBarang;
    private ImageView iconMinus;
    private ImageView iconPlus;
    private EditText editStok;
    private ImageView image;
    private EditText editImgUrl;
    private Button btnCekUrl;
    private Button btnKirim;
    private EditText editDeskripsiBarang;
    private ImageView ivImage;

    public TambahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah, container, false);
        initView(view);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService = ApiConfig.getApiService();
                apiService.tambahData(editNamaBarang.getText().toString().trim(),
                        editImgUrl.getText().toString().trim(),
                        editDeskripsiBarang.getText().toString().trim(),
                        editHargaBarang.getText().toString().trim(),
                        editStok.getText().toString().trim())
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Sukses Tambah",
                                            Toast.LENGTH_SHORT).show();
                                    editNamaBarang.setText("");
                                    editImgUrl.setText("");
                                    editDeskripsiBarang.setText("");
                                    editHargaBarang.setText("");
                                    editStok.setText("");
                                } else {
                                    Toast.makeText(getActivity(), "" + response.body(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getActivity(), "" + response.body(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getActivity(), "" + response.body(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getActivity(), "" + response.body(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getActivity(), "" + response.body(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getActivity(), "" + t.getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

        btnCekUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getActivity()).load(editImgUrl.getText().toString().trim()).override(512, 512 ).into(ivImage);
            }
        });

        return (view);
    }

    private void initView(View view) {
        iconItemName = view.findViewById(R.id.icon_item_name);
        editNamaBarang = view.findViewById(R.id.edit_nama_barang);
        iconHarga = view.findViewById(R.id.icon_harga);
        editHargaBarang = view.findViewById(R.id.edit_harga_barang);
        iconMinus = view.findViewById(R.id.icon_minus);
        iconPlus = view.findViewById(R.id.icon_plus);
        editStok = view.findViewById(R.id.edit_stok);
        image = view.findViewById(R.id.image);
        editImgUrl = view.findViewById(R.id.edit_img_url);
        btnCekUrl = view.findViewById(R.id.btn_cek_url);
        btnKirim = view.findViewById(R.id.btn_kirim);
        editDeskripsiBarang = view.findViewById(R.id.edit_deskripsi_barang);
        ivImage = view.findViewById(R.id.iv_image);
    }
}
