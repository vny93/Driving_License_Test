package com.example.doan_thibanglaixe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_thibanglaixe.Menu;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.adapter.Loaibang_Adapter;
import com.example.doan_thibanglaixe.adapter.Loaide_Adapter;
import com.example.doan_thibanglaixe.api.ApiLoaiBanglaiService;
import com.example.doan_thibanglaixe.api.ApiLoaideService;
import com.example.doan_thibanglaixe.model.Loaibang;
import com.example.doan_thibanglaixe.model.Loaide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment3 extends Fragment {
    private RecyclerView rcvLoaide;
    private RecyclerView rcvLoaibang;
    private Menu menu;
    private View view = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_3,container,false);
        menu = (Menu) getActivity();
        rcvLoaibang = view.findViewById(R.id.rcv_loaibanglai);
        rcvLoaide = view.findViewById(R.id.rcv_loaide);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(menu);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(menu);
        rcvLoaide.setLayoutManager(linearLayoutManager1);
        rcvLoaibang.setLayoutManager(linearLayoutManager2);

        setConTrol();
        setEvent();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getListLoaibang();
        getListLoaide();
    }

    private void getListLoaide() {
        ApiLoaideService.apiLoaideService.getListLoaide().enqueue(new Callback<List<Loaide>>() {
            @Override
            public void onResponse(Call<List<Loaide>> call, Response<List<Loaide>> response) {
                Loaide_Adapter loaide_adapter = new Loaide_Adapter(response.body());
                rcvLoaide.setAdapter(loaide_adapter);

                System.out.println("lấy loại đề thành công!");
            }

            @Override
            public void onFailure(Call<List<Loaide>> call, Throwable t) {

            }
        });
    }

    private void getListLoaibang() {
        ApiLoaiBanglaiService.apiLoaibanglaiService.getListLoaibang().enqueue(new Callback<List<Loaibang>>() {
            @Override
            public void onResponse(Call<List<Loaibang>> call, Response<List<Loaibang>> response) {
                Loaibang_Adapter loaibang_adapter = new Loaibang_Adapter(response.body());
                rcvLoaibang.setAdapter(loaibang_adapter);
                System.out.println("lấy loại bằng thành công!");
            }

            @Override
            public void onFailure(Call<List<Loaibang>> call, Throwable t) {

            }
        });
    }

    private void setEvent() {
    }

    private void setConTrol() {

    }
}
