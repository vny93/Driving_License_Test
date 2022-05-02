package com.example.doan_thibanglaixe;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.doan_thibanglaixe.adapter.Loailithuyet_Adapter;
import com.example.doan_thibanglaixe.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.fragment.Fragment_Hoclithuyet;
import com.example.doan_thibanglaixe.fragment.Fragment_Lambaithi;
import com.example.doan_thibanglaixe.model.Cauhoi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Cauhoilithuyet extends FragmentActivity {

    public static List<Cauhoi> ListCauhoi;
    private TextView tvLui, tvTien;
    private Integer position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cauhoilithuyet);
        getCauhoiLT();
        setConTrol();
        setEvent();



    }

    private void setEvent() {
        tvLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position > 0){
                    position = position -1;
                    System.out.println("Lùi: "+position);
                    replaceFragment(new Fragment_Hoclithuyet());
                }
                else return;

            }
        });

        tvTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position < Fragment_Hoclithuyet.size-1){
                    position = position + 1;
                    System.out.println("Tiến: "+position);
                    replaceFragment(new Fragment_Hoclithuyet());
                }else return;

            }
        });
    }

    public Integer getPosition(){
        return position;
    }
    public void getCauhoiLT(){
        System.out.println("mabode:"+Menu.bodethi.getMabodethi());
        System.out.println("loailt:"+Loailithuyet_Adapter.loailt.getMaLoaiLiThuyet());
        ApiCauhoiiService.apiCauhoiService.getCauhoiLT(Menu.bodethi.getMabodethi(),Loailithuyet_Adapter.loailt.getMaLoaiLiThuyet()).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                ListCauhoi = response.body();
                System.out.println("size: "+ListCauhoi.size());
                replaceFragment(new Fragment_Hoclithuyet());
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {

            }
        });
    }

    private void setConTrol() {
        tvLui = findViewById(R.id.tvLui);
        tvTien = findViewById(R.id.tvTien);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame3, fragment);
        transaction.commit();
    }
}