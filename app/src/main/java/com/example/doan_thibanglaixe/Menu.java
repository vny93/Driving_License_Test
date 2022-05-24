package com.example.doan_thibanglaixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan_thibanglaixe.adapter.Loaibang_Adapter;
import com.example.doan_thibanglaixe.adapter.Loaide_Adapter;
import com.example.doan_thibanglaixe.api.ApiBodethiService;
import com.example.doan_thibanglaixe.fragment.Fragment1;
import com.example.doan_thibanglaixe.fragment.Fragment2;
import com.example.doan_thibanglaixe.fragment.Fragment3;
import com.example.doan_thibanglaixe.fragment.Fragment4;
import com.example.doan_thibanglaixe.fragment.Fragment5;
import com.example.doan_thibanglaixe.model.Bodethi;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    TextView tvEmail2, tvUsername;
    ImageView ImvAvatar, imvHoclt;
    View view = null;
    public static Bodethi bodethi;
    private static final int FRAGMENT_1 = 0;
    private static final int FRAGMENT_2 = 1;
    private static final int FRAGMENT_3 = 2;
    private static final int FRAGMENT_4 = 3;
    private static final int FRAGMENT_5 = 4;
    private int mCurrentFragment = FRAGMENT_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setControl();
        setEvent();

    }

    private void setEvent() {

    }

    private void setControl() {

        ImvAvatar = findViewById(R.id.ImvAvatar);
        toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new Fragment1()); // gán mặc định khi vào menu
        navigationView.getMenu().findItem(R.id.nax_1).setCheckable(true);

        //ánh xạ header_nav
        View headerLayout = navigationView.getHeaderView(0);
        tvUsername = (TextView) headerLayout.findViewById(R.id.tvUsername);
        tvEmail2 = (TextView) headerLayout.findViewById(R.id.tvEmail2);
        tvEmail2.setText(MainActivity.user.getEmail().toString());
        tvUsername.setText(MainActivity.user.getHoten().toString());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nax_1) {
            if (mCurrentFragment != FRAGMENT_1) {
                getMabode(Loaibang_Adapter.loaibanglai.getMaloaibang(), Loaide_Adapter.loaiBoDe.getMaloaide());
                replaceFragment(new Fragment1());
                mCurrentFragment = FRAGMENT_1;
            }

        } else if (id == R.id.nax_2) {
            if (mCurrentFragment != FRAGMENT_2) {
                replaceFragment(new Fragment2());
                mCurrentFragment = FRAGMENT_2;
            }

        } else if (id == R.id.nax_3) {
            if (mCurrentFragment != FRAGMENT_3) {
                replaceFragment(new Fragment3());
                mCurrentFragment = FRAGMENT_3;
            }

        } else if (id == R.id.nax_4) {
            if (mCurrentFragment != FRAGMENT_4) {
                replaceFragment(new Fragment4());
                mCurrentFragment = FRAGMENT_4;
            }

        } else if (id == R.id.nax_5) {
            if (mCurrentFragment != FRAGMENT_5) {
                replaceFragment(new Fragment5());
                mCurrentFragment = FRAGMENT_5;
            }
        } else if (id == R.id.nax_6) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
    private void getMabode(Integer maloaibang, Integer maloaide){
        ApiBodethiService.apiBodethiService.getBode(maloaibang,maloaide).enqueue(new Callback<Bodethi>() {
            @Override
            public void onResponse(Call<Bodethi> call, Response<Bodethi> response) {
                bodethi = response.body();
                System.out.println("Lấy mã bộ đề thành công"+bodethi.getMabodethi());
            }

            @Override
            public void onFailure(Call<Bodethi> call, Throwable t) {

            }
        });
    }

}