package com.example.doan_thibanglaixe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan_thibanglaixe.CacCauSai;
import com.example.doan_thibanglaixe.KetQuaActivity;
import com.example.doan_thibanglaixe.LamBaiThi;
import com.example.doan_thibanglaixe.LiThuyet;
import com.example.doan_thibanglaixe.MeoActivity;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.TraCuuLuat;
import com.example.doan_thibanglaixe.model.Meo;

public class Fragment1 extends Fragment {
    ImageView imvHoclt,imvLambaithi,imvKetqua,imvMeo,imvCausai,imvLuat;
    View view = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_1,container,false);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        imvHoclt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LiThuyet.class);
                startActivity(intent);
            }
        });
        imvLambaithi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LamBaiThi.class);
                startActivity(intent);
            }
        });
        imvKetqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), KetQuaActivity.class);
                startActivity(intent);
            }
        });
        imvMeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MeoActivity.class);
                startActivity(intent);
            }
        });
        imvCausai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CacCauSai.class);
                startActivity(intent);
            }
        });
        imvLuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TraCuuLuat.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        imvHoclt = view.findViewById(R.id.imvHoclithuyet);
        imvLambaithi = view.findViewById(R.id.imvLambaithi);
        imvKetqua = view.findViewById(R.id.imvKetqua);
        imvMeo = view.findViewById(R.id.imvMeo);
        imvCausai = view.findViewById(R.id.imvCausai);
        imvLuat = view.findViewById(R.id.imvluat);
    }
}
