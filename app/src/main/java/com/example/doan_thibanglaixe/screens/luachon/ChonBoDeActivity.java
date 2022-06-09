package com.example.doan_thibanglaixe.screens.luachon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.doan_thibanglaixe.adapter.LoaiBLAdapter;
import com.example.doan_thibanglaixe.adapter.LoaideAdapter;
import com.example.doan_thibanglaixe.data.api.ApiLoaiBanglaiService;
import com.example.doan_thibanglaixe.data.model.Loaibang;
import com.example.doan_thibanglaixe.screens.menu.MenuActivity;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.api.ApiLoaideService;
import com.example.doan_thibanglaixe.data.model.Loaide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChonBoDeActivity extends AppCompatActivity {
    private Button btnChon;
    private Spinner spLoaide, spLoaibl;
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
                if(LoaideAdapter.maLoaide==0){
                    System.out.println("Vui lòng chọn loại đề thi");
                    return;
                }
                if(LoaiBLAdapter.maLoaiBL == 0){
                    System.out.println("Vui lòng chọn loại bằng");
                    return;
                }
                else{
                    Intent intent = new Intent(ChonBoDeActivity.this, MenuActivity.class);

                    startActivity(intent);
                }
            }
        });
    }

    private void setControl() {
        spLoaide = findViewById(R.id.spLoaide);
        spLoaibl = findViewById(R.id.spLoaibl);
        btnChon = findViewById(R.id.btnchon);
    }

    private void loadLoaide(List<Loaide> loaides){
        LoaideAdapter adapterLoaide = new LoaideAdapter(getApplicationContext(),R.layout.item_selected_spinnerloaide,loaides);
        spLoaide.setAdapter(adapterLoaide);
        System.out.println("lấy loại đề thành công!");
        spLoaide.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LoaideAdapter.maLoaide = adapterLoaide.getItem(i).getMaloaide();
                System.out.println("maloaide "+ LoaideAdapter.maLoaide);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void loadLoaiBL(List<Loaibang> loaibangs){
        LoaiBLAdapter adapterLoaiBL = new LoaiBLAdapter(getApplicationContext(),R.layout.item_selected_spinnerloaibl,loaibangs);
        spLoaibl.setAdapter(adapterLoaiBL);
        System.out.println("lấy loại bằng thành công!");
        spLoaibl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LoaiBLAdapter.maLoaiBL = adapterLoaiBL.getItem(i).getMaloaibang();
                System.out.println("maloaiBL "+ LoaiBLAdapter.maLoaiBL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getListLoaide() {
        ApiLoaideService.apiLoaideService.getListLoaide().enqueue(new Callback<List<Loaide>>() {
            @Override
            public void onResponse(Call<List<Loaide>> call, Response<List<Loaide>> response) {
                List loaides = response.body();
                loadLoaide(loaides);
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
                List loaibls = response.body();
                loadLoaiBL(loaibls);
            }

            @Override
            public void onFailure(Call<List<Loaibang>> call, Throwable t) {

            }
        });
    }
}