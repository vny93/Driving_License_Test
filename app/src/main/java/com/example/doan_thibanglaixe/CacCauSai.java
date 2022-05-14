package com.example.doan_thibanglaixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.doan_thibanglaixe.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.fragment.Fragment_Caccausai;
import com.example.doan_thibanglaixe.fragment.Fragment_Chitietkq;
import com.example.doan_thibanglaixe.model.Cauhoi;
import com.example.doan_thibanglaixe.model.Ketqua;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CacCauSai extends AppCompatActivity {
    public static List<Cauhoi> ListCausai;
    private TextView tvLui, tvTien;
    private Integer position = 0;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cac_cau_sai);
        setConTrol();
        setEvent();
        getCausai();
    }
    private void setEvent() {
        tvLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0) {
                    position = position - 1;
                    replaceFragment(new Fragment_Caccausai());
                } else return;
            }
        });
        tvTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < Fragment_Caccausai.size - 1) {
                    position = position + 1;
                    replaceFragment(new Fragment_Caccausai());
                } else return;
            }
        });
    }

    private void getCausai(){
        ApiCauhoiiService.apiCauhoiService.getCausai(MainActivity.user.getEmail(),Menu.bodethi.getMabodethi()).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                ListCausai = response.body();
                replaceFragment(new Fragment_Caccausai());
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {

            }
        });
    }

    public Integer getPosition() {
        System.out.println("getPosition: " + position);
        return position;
    }
    private void setConTrol() {
        tvLui = findViewById(R.id.tvLuiCS);
        tvTien = findViewById(R.id.tvTienCS);
        toolbar = findViewById(R.id.toolbarCausai);
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
        transaction.replace(R.id.content_frameCausai, fragment);
        transaction.commit();
    }
}