package com.example.doan_thibanglaixe.screens.quenmk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.api.ApiTKService;
import com.example.doan_thibanglaixe.data.api.ApiUserService;
import com.example.doan_thibanglaixe.data.model.Taikhoan;
import com.example.doan_thibanglaixe.screens.login.LoginActivity;
import com.example.doan_thibanglaixe.screens.luachon.ChonBoDeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText edtMkmoi, edtMkmoi2;
    Button btnDoimk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mkMoi = edtMkmoi.getText().toString();
                String mkMoi2 = edtMkmoi2.getText().toString();
                if (mkMoi.isEmpty() || mkMoi2.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(mkMoi.equals(mkMoi2))) {
                    Toast.makeText(ResetPasswordActivity.this, "Xác nhận mật khẩu mới không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                Taikhoan taiKhoan = new Taikhoan();
                taiKhoan.setTendangnhap(getIntent().getStringExtra("user"));
                taiKhoan.setMatkhau(mkMoi);
                taiKhoan.setTrangthai("1");
                doiMK(taiKhoan);
            }
        });
    }

    private void doiMK(Taikhoan taikhoan) {
        ApiUserService.apiUserService.updateTK(taikhoan).enqueue(new Callback<Taikhoan>() {
            @Override
            public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                Toast.makeText(ResetPasswordActivity.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                edtMkmoi.setText("");
                edtMkmoi2.setText("");
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Taikhoan> call, Throwable t) {
                Toast.makeText(ResetPasswordActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        edtMkmoi = findViewById(R.id.reset_mkmoi);
        edtMkmoi2 = findViewById(R.id.reset_mkmoi2);
        btnDoimk = findViewById(R.id.btn_resetpw);
    }
}