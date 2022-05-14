package com.example.doan_thibanglaixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.doan_thibanglaixe.adapter.Meothi_Adapter;
import com.example.doan_thibanglaixe.api.ApiMeothiService;
import com.example.doan_thibanglaixe.model.Loaimeo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeoActivity extends AppCompatActivity {
    Meothi_Adapter adapterMeothi;
    ListView lvloaimeo;
    List<Loaimeo> filter;
    SearchView svLoaimeo;
    private List<Loaimeo> list;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meo_thi);
        setControl();
        setEvent();
        loadListView();
    }
    private void setEvent(){
        svLoaimeo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        ApiMeothiService.apiMeothiService.getListLoaiMeo().enqueue(new Callback<List<Loaimeo>>() {
            @Override
            public void onResponse(Call<List<Loaimeo>> call, Response<List<Loaimeo>> response) {
                list = response.body();
                adapterMeothi = new Meothi_Adapter(MeoActivity.this, R.layout.item_meothi, response.body());
                lvloaimeo.setAdapter(adapterMeothi);

            }

            @Override
            public void onFailure(Call<List<Loaimeo>> call, Throwable t) {

            }
        });

    }
    private void getFilter(String s){
        filter = new ArrayList<>();
        for (Loaimeo lm: list) {
            if(lm.getTenloaimeo().toLowerCase().contains(s.toLowerCase())){
                filter.add(lm);
            }
        }
        adapterMeothi.setFilterList(filter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    private void setControl() {
        lvloaimeo = findViewById(R.id.lvLoaimeo);
        svLoaimeo = findViewById(R.id.svLoaimeo);
        toolbar = findViewById(R.id.toolbar_meo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}