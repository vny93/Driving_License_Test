package com.example.doan_thibanglaixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.doan_thibanglaixe.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.fragment.Fragment_Ketqua;
import com.example.doan_thibanglaixe.model.Cauhoi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KetQuaActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        replaceFragment(new Fragment_Ketqua());
        setConTrol();
    }

    private void setConTrol() {
        toolbar = findViewById(R.id.toolbar_kq);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    private void getKq(){
      //  ApiKetquaService.apiKetquaService.getLuotthi()
    }

    private void getCauhoiKQ(){
        String email=MainActivity.user.getEmail().toString();
        Integer luotthi=1;
        Integer mabode=Menu.bodethi.getMabodethi();
        ApiCauhoiiService.apiCauhoiService.getCauhoi_KQ(email,luotthi,mabode).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {

            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {

            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_5, fragment);
        transaction.commit();
    }
}