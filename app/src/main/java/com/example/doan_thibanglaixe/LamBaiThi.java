package com.example.doan_thibanglaixe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_thibanglaixe.adapter.CheckAnswer_Adapter;
import com.example.doan_thibanglaixe.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.api.ApiKetquaService;
import com.example.doan_thibanglaixe.fragment.Fragment1;
import com.example.doan_thibanglaixe.fragment.Fragment_Hoclithuyet;
import com.example.doan_thibanglaixe.fragment.Fragment_Lambaithi;
import com.example.doan_thibanglaixe.model.Cauhoi;
import com.example.doan_thibanglaixe.model.Cautraloi;
import com.example.doan_thibanglaixe.model.Ketqua;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LamBaiThi extends AppCompatActivity {

    public static List<Cauhoi> ListCauhoirand;
    public static List<Cautraloi> traLoi;
    private TextView tvLui, tvTien,tvKiemtra,tvKetthuc,tvTimer,tvcaudung;
    private Integer position = 0;
    Button btnDong,btnLamlai,btnThoat;
    CounterClass timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lam_bai_thi);
        timer = new CounterClass(2*60*1000,1000);
        getListDeThi();
//        replaceFragment(new Fragment_Lambaithi());
        setControl();
        setEvent();
    }

    private void getListDeThi() {
        ApiCauhoiiService.apiCauhoiService.ranđDeThi(Menu.bodethi.getMabodethi()).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                ListCauhoirand = response.body();
                khoiTao(ListCauhoirand);
                replaceFragment(new Fragment_Lambaithi());
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {

            }
        });
    }
    private void khoiTao(List<Cauhoi> list){
        traLoi = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            Cautraloi cautl = new Cautraloi(i,"");
            traLoi.add(cautl);
        }
        System.out.println(traLoi.size());
    }

    private void setEvent() {
        tvLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position > 0){
                    position = position -1;
                    System.out.println("Lùi: "+position);
                    replaceFragment(new Fragment_Lambaithi());
                }
                else return;
            }
        });
        tvTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position < Fragment_Lambaithi.size_lbt-1){
                    position = position + 1;
                    System.out.println("Tiến: "+position);
                    replaceFragment(new Fragment_Lambaithi());
                }else return;
            }
        });

        tvKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
        tvKetthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                Xemdiem();
            }
        });

        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        timer.start();
    }

    private void Xemdiem() {
        Integer dem = demCaudung();
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_xemdiem);
        tvcaudung = dialog.findViewById(R.id.tvcaudung);
        tvcaudung.setText(dem+"/"+ListCauhoirand.size());
        btnLamlai = dialog.findViewById(R.id.btnLamlai);
        btnThoat = dialog.findViewById(R.id.btnThoat);
        btnLamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                khoiTao(ListCauhoirand);
                position = 0; //gán lại vị trí câu đầu tiên
                replaceFragment(new Fragment_Lambaithi());
                timer.start();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lưu kết quả và thoát ra màn hình Fragment1
                dialog.dismiss();
                insertKetQua();
                finish();
            }
        });
        dialog.show();

    }
    private void insertKetQua() {
        String email = MainActivity.user.getEmail().toString();
        Integer mabode = Menu.bodethi.getMabodethi();
        ApiKetquaService.apiKetquaService.getMaxluotthi(email, mabode).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer max = response.body();
                System.out.println("MAX: "+max);
                insert(traLoi,max);
                Toast.makeText( LamBaiThi.this,"Kết quả đã được lưu lại",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(LamBaiThi.this, Menu.class);
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void insert(List<Cautraloi> cautraloiList, Integer max) {
        List<Cauhoi> cauhoi = ListCauhoirand;
        for(int i =0;i<cauhoi.size();i++) {
            Ketqua kq = new Ketqua();
            Integer luotthi = max+1;
            kq.setPhuongan(cautraloiList.get(i).getTraloi());
            kq.setLuotthi(luotthi);
            kq.setMauser(MainActivity.user.getEmail().toString());
            kq.setMacauhoi(cauhoi.get(i).getMacauhoi());
            ApiKetquaService.apiKetquaService.insertKetqua(kq).enqueue(new Callback<Ketqua>() {
                @Override
                public void onResponse(Call<Ketqua> call, Response<Ketqua> response) {

                }

                @Override
                public void onFailure(Call<Ketqua> call, Throwable t) {

                }
            });
        }
    }

    private Integer demCaudung(){
        Integer dem = 0;
        for(int i =0;i <ListCauhoirand.size();i++){
            String cautl = traLoi.get(i).getTraloi().trim().toString();
            String dapAn = ListCauhoirand.get(i).getDapan().trim().toString();
            if(cautl.equals(dapAn)){
                dem = dem + 1;
            }
        }
        return dem;
    }

    public Integer getPosition_lbt(){
        return position;
    }

    public void checkAnswer(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_checkanswer);
        btnDong = dialog.findViewById(R.id.btnDong);
        dialog.setTitle("Danh sách câu trả lởi");
        CheckAnswer_Adapter answer_adapter = new CheckAnswer_Adapter((ArrayList) LamBaiThi.ListCauhoirand,this);
        GridView gvQuestion = (GridView) dialog.findViewById(R.id.gvQuestion);
        gvQuestion.setAdapter(answer_adapter);
        gvQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                replaceFragment(new Fragment_Lambaithi());
                dialog.dismiss();//tắt dialog đi
            }
        });
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public class  CounterClass extends CountDownTimer{
    //millisInFuture: muốn hiển thị 1p: 60 giây *1000
    //countDownInterval: đếm bước nhảy 1 giây là :1000
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            String coutTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(l),
                    TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
            tvTimer.setText(coutTime);
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");
            Xemdiem();
        }
    }

    private void setControl() {
        tvTien = findViewById(R.id.tvTien_lbt);
        tvLui = findViewById(R.id.tvLui_lbt);
        tvKiemtra = findViewById(R.id.tvKiemTra);
        tvKetthuc = findViewById(R.id.tvKetthuc);
        tvTimer = findViewById(R.id.tvTimer);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame4, fragment);
        transaction.commit();
    }
}