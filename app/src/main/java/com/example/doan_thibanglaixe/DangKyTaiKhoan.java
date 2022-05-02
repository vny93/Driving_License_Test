package com.example.doan_thibanglaixe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan_thibanglaixe.api.ApiUserService;
import com.example.doan_thibanglaixe.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyTaiKhoan extends AppCompatActivity {
    EditText edtEmail,edtHoten,edtSDT,edtMatKhau;
    Button btnDangKy;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_tai_khoan);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                checkRegister();
            }
        });
    }

    private void checkRegister() {
        String email = edtEmail.getText().toString().trim();
        String hoTen = edtHoten.getText().toString().trim();
        String sdt = edtSDT.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();
        if(email.isEmpty() || hoTen.isEmpty() || sdt.isEmpty() || matKhau.isEmpty()){
            Toast.makeText(DangKyTaiKhoan.this,"Vui lòng điền đẩy đủ thông tin",Toast.LENGTH_SHORT).show();
            return;
        }
        CheckUser();
    }

    private void CheckUser() {
        Log.e("email",edtEmail.getText().toString());
        Log.e("hoten",edtHoten.getText().toString());
        Log.e("sdt", edtSDT.getText().toString());
        Log.e("sdt", edtMatKhau.getText().toString());
        ApiUserService.apiUserService.getUserByID(edtEmail.getText().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(DangKyTaiKhoan.this, "Tài khoản này đã tồn tại rồi", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                XulyDangKy();
            }
        });
    }

    private void XulyDangKy() {
        ApiUserService.apiUserService.insertTK(edtEmail.getText().toString(),
                                                edtHoten.getText().toString(),
                                                edtSDT.getText().toString(),
                                                edtMatKhau.getText().toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                Toast.makeText(DangKyTaiKhoan.this,"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(DangKyTaiKhoan.this,"Đăng ký thất bại!",Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }


    private void setControl() {
        edtEmail = findViewById(R.id.edtEmail);
        edtHoten = findViewById(R.id.edtHoTen);
        edtSDT = findViewById(R.id.edtSDT);
        edtMatKhau = findViewById(R.id.edtMatkhau);
        btnDangKy= findViewById(R.id.btnDangKy);
    }
}