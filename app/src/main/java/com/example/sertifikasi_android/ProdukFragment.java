package com.example.sertifikasi_android;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sertifikasi_android.api.ApiConfig;
import com.example.sertifikasi_android.api.ApiService;
import com.example.sertifikasi_android.model.ProdukModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProdukFragment extends Fragment {

    private MenampilkanProdukAdapter menampilkanProdukAdapter;
    private ArrayList<ProdukModel> produkModels;
    private RecyclerView RVItem;

    public ProdukFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produk, container, false);
        initView(view);
        produkModels = new ArrayList<>();
        getDataFromServer();

        return view;
    }

    private void getDataFromServer() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.ambilData().enqueue(new Callback<ArrayList<ProdukModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProdukModel>> call, Response<ArrayList<ProdukModel>> response) {
                produkModels = response.body();
                if (response.isSuccessful()){

                    menampilkanProdukAdapter = new MenampilkanProdukAdapter(getActivity(), produkModels);
                    RVItem.setAdapter(menampilkanProdukAdapter);
                    RVItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    menampilkanProdukAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProdukModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t.getMessage(),
                    Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView(View view) {
        RVItem = view.findViewById(R.id.RVItem);
    }
}
