package com.example.doan_thibanglaixe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class ChonBoDeThi extends AppCompatActivity {
    private RecyclerView rcvLoaide;
    private RecyclerView rcvLoaibang;
    private Button btnChon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_bo_de_thi);
        setControl();
        getListLoaibang();
        getListLoaide();
        setEvent();
    }

    private void setEvent() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Loaide_Adapter.maLoaide==0){
                    System.out.println("Vui lòng chọn loại đề thi");
                    return;
                }
                if(Loaibang_Adapter.loaibl == 0){
                    System.out.println("Vui lòng chọn loại bằng");
                    return;
                }
                else{
                    Intent intent = new Intent(ChonBoDeThi.this,Menu.class);

                    startActivity(intent);
                }
            }
        });
    }

    private void setControl() {
        rcvLoaibang = findViewById(R.id.rcv_loaibanglai);
        rcvLoaide = findViewById(R.id.rcv_loaide);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        rcvLoaide.setLayoutManager(linearLayoutManager1);
        rcvLoaibang.setLayoutManager(linearLayoutManager2);
        btnChon = findViewById(R.id.btnchon);
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
}