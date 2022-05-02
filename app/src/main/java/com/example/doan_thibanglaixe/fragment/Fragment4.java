package com.example.doan_thibanglaixe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan_thibanglaixe.DangKyTaiKhoan;
import com.example.doan_thibanglaixe.MainActivity;
import com.example.doan_thibanglaixe.Menu;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.api.ApiUserService;
import com.example.doan_thibanglaixe.model.Taikhoan;
import com.example.doan_thibanglaixe.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment4 extends Fragment {
    EditText edtEmail, edtHoten, edtSDT;
    Button btnLuutt;
    View view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //    return inflater.inflate(R.layout.fragment_phep_tinh, container, false);
        view = inflater.inflate(R.layout.fragment_4, container, false);
        setControl();
        setEvent();
        LoadThongtincn();
        return view;
    }

    private void setEvent() {
        btnLuutt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String hoten = edtHoten.getText().toString().trim();
                String sdt = edtSDT.getText().toString().trim();
                if(email.isEmpty() || hoten.isEmpty() || sdt.isEmpty()){
                    Toast.makeText(getContext(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                user.setEmail(email);
                user.setHoten(hoten);
                user.setSdt(sdt);
                ApiUserService.apiUserService.update(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        MainActivity.user = response.body();
                        LoadThongtincn();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getContext(),"lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void LoadThongtincn() {
        ApiUserService.apiUserService.getUserByID(MainActivity.user.getEmail().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                edtEmail.setText("  " + response.body().getEmail());
                edtHoten.setText("  " + response.body().getHoten());
                edtSDT.setText("  " + response.body().getSdt());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        edtEmail = view.findViewById(R.id.fg_edtEmail);
        edtHoten = view.findViewById(R.id.fg_edtHoten);
        edtSDT = view.findViewById(R.id.fg_edtSDT);
        btnLuutt = view.findViewById(R.id.fg_btnLuutt);
    }
}
