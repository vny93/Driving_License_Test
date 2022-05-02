package com.example.doan_thibanglaixe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doan_thibanglaixe.Cauhoilithuyet;
import com.example.doan_thibanglaixe.LiThuyet;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.adapter.Loailithuyet_Adapter;
import com.example.doan_thibanglaixe.api.ApiLoailithuyetService;
import com.example.doan_thibanglaixe.model.Loailithuyet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Loailithuyet extends Fragment {

    private RecyclerView rcvLoailt;
    private View view;
    private LiThuyet mhocLithuyet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //    return inflater.inflate(R.layout.fragment_phep_tinh, container, false);
        view = inflater.inflate(R.layout.fragment_loailithuyet, container, false);
        mhocLithuyet = (LiThuyet) getActivity();
        rcvLoailt = view.findViewById(R.id.rcv_loailt);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mhocLithuyet);
        rcvLoailt.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getListLoailt();
    }

    private void getListLoailt() {

        ApiLoailithuyetService.apiLoailtService.getListLoailt().enqueue(new Callback<List<Loailithuyet>>() {
            @Override
            public void onResponse(Call<List<Loailithuyet>> call, Response<List<Loailithuyet>> response) {
                Loailithuyet_Adapter loailtAdapter = new Loailithuyet_Adapter(response.body(), new Loailithuyet_Adapter.IClickTVListener() {
                    @Override
                    public void onClickTv() {
                        Intent intent = new Intent(getActivity(), Cauhoilithuyet.class);
                        startActivity(intent);
                    }
                });
                rcvLoailt.setAdapter(loailtAdapter);
             //   RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mhocLithuyet, DividerItemDecoration.VERTICAL);
           //     rcvLoailt.addItemDecoration(itemDecoration);
                System.out.println("lấy thành công!");
            }

            @Override
            public void onFailure(Call<List<Loailithuyet>> call, Throwable t) {

            }
        });
    }
}
