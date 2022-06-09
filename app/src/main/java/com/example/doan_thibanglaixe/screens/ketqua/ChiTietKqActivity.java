package com.example.doan_thibanglaixe.screens.ketqua;

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

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.data.api.ApiKetquaService;
import com.example.doan_thibanglaixe.data.model.Cauhoi;
import com.example.doan_thibanglaixe.data.model.Ketqua;
import com.example.doan_thibanglaixe.screens.login.LoginActivity;
import com.example.doan_thibanglaixe.screens.menu.MenuActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietKqActivity extends AppCompatActivity {
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
                    replaceFragment(new FragmentChitietkq());
                } else return;
            }
        });
        tvTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < FragmentChitietkq.size - 1) {
                    position = position + 1;
                    replaceFragment(new FragmentChitietkq());
                } else return;
            }
        });
    }

    public Integer getPosition() {
        System.out.println("getPosition: " + position);
        return position;
    }

    private void getCauhoi_CTKQ(int luotthi) {
        ApiCauhoiiService.apiCauhoiService.getCauhoi_KQ(LoginActivity.user.getEmail(), luotthi, MenuActivity.bodethi.getMabodethi()).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                ListCauhoi_ctkq = response.body();
                System.out.println("vô 2");
                System.out.println("cau1:" + ListCauhoi_ctkq.get(0).getNoidung());
                replaceFragment(new FragmentChitietkq());
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {

            }
        });

    }

    private void getKQLuotthi(int luotthi) {
        ApiKetquaService.apiKetquaService.getLuotthi(LoginActivity.user.getEmail(), luotthi, MenuActivity.bodethi.getMabodethi()).enqueue(new Callback<List<Ketqua>>() {
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