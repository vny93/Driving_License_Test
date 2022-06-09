package com.example.doan_thibanglaixe.screens.luatgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.adapter.LuatAdapter;
import com.example.doan_thibanglaixe.data.api.ApiLuatGTService;
import com.example.doan_thibanglaixe.data.model.Luat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietLuatActivity extends AppCompatActivity {
    LuatAdapter adapterLuat;
    GridView grvLuat;
    List<Luat> filter;
    SearchView svLuat;
    private List<Luat> list;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_luat);
        setControl();
        setEvent();
        loadListView();
    }
    private void setEvent() {
        svLuat.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getFilter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getFilter(s);
                return false;
            }
        });
    }

    public void loadListView(){
        Intent intent = getIntent();
        Integer maloailuat = intent.getIntExtra("maloailuat",0);
        ApiLuatGTService.apiluatGTService.getLuat(maloailuat).enqueue(new Callback<List<Luat>>() {
            @Override
            public void onResponse(Call<List<Luat>> call, Response<List<Luat>> response) {
                list = response.body();
                adapterLuat = new LuatAdapter(ChiTietLuatActivity.this, R.layout.item_chitietluat, response.body());
                grvLuat.setAdapter(adapterLuat);
            }

            @Override
            public void onFailure(Call<List<Luat>> call, Throwable t) {

            }
        });

    }

    private void getFilter(String s){
        filter = new ArrayList<>();
        for (Luat lm: list) {
            if(lm.getNoidung().toLowerCase().contains(s.toLowerCase())){
                filter.add(lm);
            }
        }
        adapterLuat.setFilterList(filter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    private void setControl() {
        grvLuat = findViewById(R.id.gvLuat);
        svLuat = findViewById(R.id.svLuat);
        toolbar = findViewById(R.id.toolbar_chitietluat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}