package com.example.doan_thibanglaixe.screens.lithuyet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.adapter.LoaiLTAdapter;
import com.example.doan_thibanglaixe.data.api.ApiLoailithuyetService;
import com.example.doan_thibanglaixe.data.model.Loailithuyet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLoailt extends Fragment {

    private RecyclerView rcvLoailt;
    private View view;
    private LiThuyetActivity mhocLithuyet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //    return inflater.inflate(R.layout.fragment_phep_tinh, container, false);
        view = inflater.inflate(R.layout.fragment_loailithuyet, container, false);
        mhocLithuyet = (LiThuyetActivity) getActivity();
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
                LoaiLTAdapter loailtAdapter = new LoaiLTAdapter(response.body(), new LoaiLTAdapter.IClickTVListener() {
                    @Override
                    public void onClickTv(int pos) {
                        Intent intent = new Intent(getActivity(), CauhoiltActivity.class);
                        intent.putExtra("loailt",response.body().get(pos).getTenloai());
                        startActivity(intent);
                    }
                });
                rcvLoailt.setAdapter(loailtAdapter);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mhocLithuyet, DividerItemDecoration.VERTICAL);
                rcvLoailt.addItemDecoration(itemDecoration);
                System.out.println("lấy thành công!");
            }

            @Override
            public void onFailure(Call<List<Loailithuyet>> call, Throwable t) {

            }
        });
    }
}
