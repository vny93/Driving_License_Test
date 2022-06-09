package com.example.doan_thibanglaixe.screens.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_thibanglaixe.data.api.ApiUserService;
import com.example.doan_thibanglaixe.screens.luachon.ChonBoDeActivity;
import com.example.doan_thibanglaixe.screens.dangkytk.DangKyTaiKhoan;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.api.ApiTKService;
import com.example.doan_thibanglaixe.data.model.Taikhoan;
import com.example.doan_thibanglaixe.data.model.User;
import com.example.doan_thibanglaixe.screens.quenmk.QuenMkActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static User user;

    EditText edtTenDN, edtPass;
    TextView tvForgotpass, tvDangKy;
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
                checkLogin();
            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DangKyTaiKhoan.class);
                startActivity(intent);
            }
        });
        tvForgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, QuenMkActivity.class);
                intent.putExtra("user", edtTenDN.getText().toString());
                startActivity(intent);
            }
        });
    }

//    private void checkLogin() {
//        String email = edtTenDN.getText().toString().trim();
//        String pass = edtPass.getText().toString().trim();
//        if (email.isEmpty() || pass.isEmpty()) {
//            Toast.makeText(LoginActivity.this, "Vui lòng điền đẩy đủ thông tin", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        check(email,pass);
//    }
//
//    private void check(String email, String pass) {
//        ApiTKService.apiTKService.getTKByID(email).enqueue(new Callback<Taikhoan>() {
//            @Override
//            public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
//                Taikhoan tk = response.body();
//                if(pass.equals(tk.getMatkhau())){
//                    getLogin(email);
//
//                }
//                else{
//                    Toast.makeText(LoginActivity.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Taikhoan> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Tài khoản này chưa được đăng ký", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void getLogin(String email){
//        ApiUserService.apiUserService.getUserByID(email).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                user = response.body();
//                Intent intent = new Intent(LoginActivity.this, ChonBoDeActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//    }

    private void checkLogin() {
        String email = edtTenDN.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Vui lòng điền đẩy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        checkTKtontai(email, pass);
    }

    private void checkTKtontai(String email, String pass) {
        ApiTKService.apiTKService.getTKByID(email).enqueue(new Callback<Taikhoan>() {
            @Override
            public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                checkDN(email, pass);
            }

            @Override
            public void onFailure(Call<Taikhoan> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Tài khoản này chưa được đăng ký", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void XulyLogin(String email, String pass) {
        ApiUserService.apiUserService.getUserByTk(email, pass).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(LoginActivity.this, "call api success", Toast.LENGTH_SHORT).show();
                user = response.body();
                Intent intent = new Intent(LoginActivity.this, ChonBoDeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkDN(String email, String pass) {
        ApiTKService.apiTKService.checkDN(email, pass).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean check = response.body();
                if (check == true) {
                    XulyLogin(email, pass);
                } else
                    Toast.makeText(LoginActivity.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
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