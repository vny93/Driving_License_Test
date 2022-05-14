package com.example.doan_thibanglaixe.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.doan_thibanglaixe.ChiTietKetQua;
import com.example.doan_thibanglaixe.MainActivity;
import com.example.doan_thibanglaixe.Menu;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.adapter.Exam_Adapter;
import com.example.doan_thibanglaixe.api.ApiCauhoiiService;
import com.example.doan_thibanglaixe.api.ApiKetquaService;
import com.example.doan_thibanglaixe.model.Cauhoi;
import com.example.doan_thibanglaixe.model.Exam;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Ketqua extends Fragment {
    View view = null;
   // public static List<Cauhoi> ListCauhoikq;
    public static List<Integer> soDe;
  //  public static List<Ketqua> ListKq;
    Exam_Adapter exam_adapter;
    GridView gvkq;
    ArrayList<Exam> exams;
    public Fragment_Ketqua() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ketqua, container, false);
        getSode();
        return view;
    }
    private void khoiTao(){
        exams = new ArrayList<>();
    }

    private void getDe(){
        exam_adapter = new Exam_Adapter(getActivity(),R.layout.item_ketqua,exams);
        gvkq = (GridView) getActivity().findViewById(R.id.gvKetqua);
        gvkq.setAdapter(exam_adapter);
        gvkq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChiTietKetQua.class);
                intent.putExtra("luotthi",i);
                startActivity(intent);
            }
        });
    }
    private void getSode() {
        String email = MainActivity.user.getEmail().toString();
        Integer mabode = Menu.bodethi.getMabodethi();
        ApiKetquaService.apiKetquaService.getMaxluotthi(email, mabode).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer max = response.body();
                khoiTao();
                for(int i =0;i<max;i++){
                    Integer de = i+1;
                    exams.add(new Exam("Đề số: "+de));
                }
                getDe();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

}