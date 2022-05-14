package com.example.doan_thibanglaixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.doan_thibanglaixe.adapter.Loailuat_Adapter;
import com.example.doan_thibanglaixe.api.ApiLuatGTService;
import com.example.doan_thibanglaixe.model.Loailuat;
import com.example.doan_thibanglaixe.model.Loaimeo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraCuuLuat extends AppCompatActivity {
    Loailuat_Adapter adapterLoailuat;
    GridView grvLoailuat;
    List<Loailuat> filter;
    SearchView svLoailuat;
    private List<Loailuat> list;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu_luat);
        setControl();
        setEvent();
        loadListView();
    }
    private void setEvent() {
        svLoailuat.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        ApiLuatGTService.apiluatGTService.getListLoaiLuat().enqueue(new Callback<List<Loailuat>>() {
            @Override
            public void onResponse(Call<List<Loailuat>> call, Response<List<Loailuat>> response) {
                list = response.body();
                adapterLoailuat = new Loailuat_Adapter(TraCuuLuat.this, R.layout.item_loailuat, response.body());
                grvLoailuat.setAdapter(adapterLoailuat);
            }

            @Override
            public void onFailure(Call<List<Loailuat>> call, Throwable t) {

            }
        });

    }

    private void getFilter(String s){
        filter = new ArrayList<>();
        for (Loailuat lm: list) {
            if(lm.getTenLoaiLuatGt().toLowerCase().contains(s.toLowerCase())){
                filter.add(lm);
            }
        }
        adapterLoailuat.setFilterList(filter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    private void setControl() {
        grvLoailuat = findViewById(R.id.gvLoailuat);
        svLoailuat = findViewById(R.id.svLoailuat);
        toolbar = findViewById(R.id.toolbar_loailuat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}