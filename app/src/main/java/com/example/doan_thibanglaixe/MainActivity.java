package com.example.doan_thibanglaixe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_thibanglaixe.api.ApiTKService;
import com.example.doan_thibanglaixe.api.ApiUserService;
import com.example.doan_thibanglaixe.model.Taikhoan;
import com.example.doan_thibanglaixe.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static User user;

    EditText edtTenDN,edtPass;
    TextView tvForgotpass,tvDangKy;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                btnDangNhap.setTextColor(Color.WHITE);
//                btnDangNhap.setBackgroundColor(Color.BLACK); // Thay đổi màu background.
                checkLogin();

            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DangKyTaiKhoan.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin(){
        String email = edtTenDN.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(MainActivity.this,"Vui lòng điền đẩy đủ thông tin",Toast.LENGTH_SHORT).show();
            return;
        }
        checkTKtontai(email,pass);
    }
    private void checkTKtontai(String email, String pass){
        ApiTKService.apiTKService.getTKByID(email).enqueue(new Callback<Taikhoan>() {
            @Override
            public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                checkDN(email, pass);
            }

            @Override
            public void onFailure(Call<Taikhoan> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Sai thông tin tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void XulyLogin(String email, String pass) {
        System.out.println("aaaaa");
        ApiUserService.apiUserService.getUserByTk(email,pass).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this,"call api success",Toast.LENGTH_SHORT).show();
                System.out.println("1");
                user=response.body();
                System.out.println("2");
                Intent intent = new Intent(MainActivity.this,Menu.class);
                System.out.println("3");
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
               // Toast.makeText(MainActivity.this,"Sai thông tin tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void checkDN(String email, String pass) {
            ApiTKService.apiTKService.checkDN(email, pass).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    Boolean check = response.body();
                    System.out.println("check: " + check);
                    if (check == true) {
                        System.out.println("hihihi");
                        XulyLogin(email, pass);
                    } else
                        Toast.makeText(MainActivity.this, "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            });
        }

    private void setControl() {
        edtTenDN = findViewById(R.id.tendn);
        edtPass = findViewById(R.id.password);
        tvForgotpass = findViewById(R.id.tvForgotpass);
        tvDangKy = findViewById(R.id.tvDangKy);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }
}