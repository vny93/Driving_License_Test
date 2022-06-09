package com.example.doan_thibanglaixe.screens.lithuyet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.adapter.LoaiLTAdapter;
import com.example.doan_thibanglaixe.data.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.data.model.Cauhoi;
import com.example.doan_thibanglaixe.screens.menu.MenuActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CauhoiltActivity extends AppCompatActivity {

    public static List<Cauhoi> ListCauhoi;
    private TextView tvLui, tvTien;
    private Integer position=0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoilithuyet);
        getCauhoiLT();
        setConTrol();
        setEvent();
        Intent intent = getIntent();
        String loailt = intent.getStringExtra("loailt");
        toolbar.setTitle(loailt);


    }

    private void setEvent() {
        tvLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position > 0){
                    position = position -1;
                    System.out.println("Lùi: "+position);
                    replaceFragment(new FragmentHoclt());
                }
                else return;

            }
        });

        tvTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position < FragmentHoclt.size-1){
                    position = position + 1;
                    System.out.println("Tiến: "+position);
                    replaceFragment(new FragmentHoclt());
                }else return;

            }
        });
    }

    public Integer getPosition(){
        return position;
    }
    public void getCauhoiLT(){
        System.out.println("mabode:"+ MenuActivity.bodethi.getMabodethi());
        System.out.println("loailt:"+ LoaiLTAdapter.loailt.getMaLoaiLiThuyet());
        ApiCauhoiiService.apiCauhoiService.getCauhoiLT(MenuActivity.bodethi.getMabodethi(), LoaiLTAdapter.loailt.getMaLoaiLiThuyet()).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                ListCauhoi = response.body();
                System.out.println("size: "+ListCauhoi.size());
                replaceFragment(new FragmentHoclt());
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {

            }
        });
    }

    private void setConTrol() {
        tvLui = findViewById(R.id.tvLui);
        tvTien = findViewById(R.id.tvTien);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame3, fragment);
        transaction.commit();
    }
}