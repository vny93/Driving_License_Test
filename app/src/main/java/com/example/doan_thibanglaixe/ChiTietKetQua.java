package com.example.doan_thibanglaixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.doan_thibanglaixe.adapter.Loailithuyet_Adapter;
import com.example.doan_thibanglaixe.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.api.ApiKetquaService;
import com.example.doan_thibanglaixe.fragment.Fragment_Chitietkq;
import com.example.doan_thibanglaixe.fragment.Fragment_Hoclithuyet;
import com.example.doan_thibanglaixe.model.Cauhoi;
import com.example.doan_thibanglaixe.model.Ketqua;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietKetQua extends AppCompatActivity {
    public static List<Cauhoi> ListCauhoi_ctkq;
    public static List<Ketqua> ListKetQuaDachon;
    private TextView tvLui, tvTien;
    private Integer position = 0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_ket_qua);
        Intent intent = getIntent();
        Integer luotthi = intent.getIntExtra("luotthi", 0);
        luotthi = luotthi + 1;
        System.out.println("luot thi: " + luotthi);
        setConTrol();
        setEvent();
        toolbar.setTitle("Kết quả đề: "+luotthi);
        getKQLuotthi(luotthi); //lấy ra các đáp án đã chọn khi làm đề đó
    }

    private void setEvent() {
        tvLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0) {
                    position = position - 1;
                    replaceFragment(new Fragment_Chitietkq());
                } else return;
            }
        });
        tvTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < Fragment_Chitietkq.size - 1) {
                    position = position + 1;
                    replaceFragment(new Fragment_Chitietkq());
                } else return;
            }
        });
    }

    public Integer getPosition() {
        System.out.println("getPosition: " + position);
        return position;
    }

    private void getCauhoi_CTKQ(int luotthi) {
        ApiCauhoiiService.apiCauhoiService.getCauhoi_KQ(MainActivity.user.getEmail(), luotthi, Menu.bodethi.getMabodethi()).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                ListCauhoi_ctkq = response.body();
                System.out.println("vô 2");
                System.out.println("cau1:" + ListCauhoi_ctkq.get(0).getNoidung());
                replaceFragment(new Fragment_Chitietkq());
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {

            }
        });

    }

    private void getKQLuotthi(int luotthi) {
        ApiKetquaService.apiKetquaService.getLuotthi(MainActivity.user.getEmail(), luotthi, Menu.bodethi.getMabodethi()).enqueue(new Callback<List<Ketqua>>() {
            @Override
            public void onResponse(Call<List<Ketqua>> call, Response<List<Ketqua>> response) {
                ListKetQuaDachon = response.body();
                System.out.println("vô 1");
                System.out.println("size:" + ListKetQuaDachon.size());
                getCauhoi_CTKQ(luotthi);
            }

            @Override
            public void onFailure(Call<List<Ketqua>> call, Throwable t) {

            }
        });

    }

    private void setConTrol() {
        tvLui = findViewById(R.id.tvLuiKQ);
        tvTien = findViewById(R.id.tvTienKQ);
        toolbar = findViewById(R.id.toolbarCTKQ);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frameKQ, fragment);
        transaction.commit();
    }
}