package com.example.sertifikasi_android;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sertifikasi_android.api.ApiConfig;
import com.example.sertifikasi_android.api.ApiService;
import com.example.sertifikasi_android.model.ProdukModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HapusFragment extends Fragment {

    private HapusShowProdukAdapter hapusShowProdukAdapter;
    private ArrayList<ProdukModel> produkModels;
    private RecyclerView RVItem;

    public HapusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hapus, container, false);
        initView(view);

        produkModels = new ArrayList<>();

        ApiService apiService = ApiConfig.getApiService();
        apiService.ambilData().enqueue(new Callback<ArrayList<ProdukModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProdukModel>> call, Response<ArrayList<ProdukModel>> response) {
                produkModels.clear();
                produkModels = response.body();
                if (response.isSuccessful()){
//                    produkModels.clear();
//                    produkModels = response.body();
                    hapusShowProdukAdapter = new HapusShowProdukAdapter(getActivity(), produkModels);
                    RVItem.setAdapter(hapusShowProdukAdapter);
                    RVItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    hapusShowProdukAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProdukModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

//    private void getDataFromServer() {
//        ApiService apiService = ApiConfig.getApiService();
//        apiService.ambilData().enqueue(new Callback<ArrayList<ProdukModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ProdukModel>> call, Response<ArrayList<ProdukModel>> response) {
//                produkModels = response.body();
//                if (response.isSuccessful()){
//                    hapusShowProdukAdapter = new HapusShowProdukAdapter(getActivity(), produkModels);
//                    RVItem.setAdapter(hapusShowProdukAdapter);
//                    RVItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//                    hapusShowProdukAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ProdukModel>> call, Throwable t) {
//                Toast.makeText(getActivity(), "" + t.getMessage(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void initView(View view) {
        RVItem = view.findViewById(R.id.RVItem);
    }
}
