package com.example.doan_thibanglaixe.screens.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan_thibanglaixe.screens.login.LoginActivity;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.api.ApiTKService;
import com.example.doan_thibanglaixe.data.api.ApiUserService;
import com.example.doan_thibanglaixe.data.model.Taikhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAccount extends Fragment {
    EditText edtMkcu, edtMkmoi,edtMkmoi2;
    Button btnDoimk;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //    return inflater.inflate(R.layout.fragment_phep_tinh, container, false);
        view = inflater.inflate(R.layout.fragment_account, container, false);
        setControl();
        setEvent();
       // LoadThongtincn();
        return view;
    }

    private void setEvent() {
        btnDoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mkCu = edtMkcu.getText().toString();
                String mkMoi = edtMkmoi.getText().toString();
                String mkMoi2 = edtMkmoi2.getText().toString();
                if(mkCu.isEmpty() || mkMoi.isEmpty() || mkMoi2.isEmpty()){
                    Toast.makeText(getContext(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(mkMoi.equals(mkMoi2))){
                    Toast.makeText(getContext(),"Xác nhận mật khẩu mới không đúng",Toast.LENGTH_SHORT).show();
                    return;
                }
                Taikhoan taiKhoan = new Taikhoan();
                ApiTKService.apiTKService.getTKByID(LoginActivity.user.getEmail().toString()).enqueue(new Callback<Taikhoan>() {
                    @Override
                    public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                        if(mkCu.equals(response.body().getMatkhau().toString())){
                            taiKhoan.setTendangnhap(LoginActivity.user.getEmail().toString());
                            taiKhoan.setMatkhau(mkMoi);
                            taiKhoan.setTrangthai("1");
                            doiMK(taiKhoan);
                        }
                        else{
                            Toast.makeText(getContext(),"Mật khẩu cũ không đúng",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<Taikhoan> call, Throwable t) {
                        Toast.makeText(getContext(),"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void doiMK(Taikhoan taikhoan){
        ApiUserService.apiUserService.updateTK(taikhoan).enqueue(new Callback<Taikhoan>() {
            @Override
            public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                Toast.makeText(getContext(),"Đổi mk thành công!",Toast.LENGTH_SHORT).show();
                edtMkcu.setText("");
                edtMkmoi.setText("");
                edtMkmoi2.setText("");
            }

            @Override
            public void onFailure(Call<Taikhoan> call, Throwable t) {
                Toast.makeText(getContext(),"Lỗi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        edtMkcu = view.findViewById(R.id.fg_mkcu);
        edtMkmoi = view.findViewById(R.id.fg_mkmoi);
        edtMkmoi2 = view.findViewById(R.id.fg_mkmoi2);
        btnDoimk = view.findViewById(R.id.btndoimk);
    }
}
