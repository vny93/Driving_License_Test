package com.example.doan_thibanglaixe.screens.lithuyet;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.model.Cauhoi;
import com.squareup.picasso.Picasso;


public class FragmentHoclt extends Fragment {

    TextView tvSTTcau, tvCauhoilt;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView imvHinhanh;
    View view = null;
    private CauhoiltActivity mCauhoilithuyet;
    private Integer position = 0;
    public static Integer size;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hoclithuyet, container, false);
        mCauhoilithuyet = (CauhoiltActivity) getActivity();
        position = mCauhoilithuyet.getPosition();
        setControl();
        getListCauhoi(position);
        return view;
    }

    private void setControl() {
        tvSTTcau = view.findViewById(R.id.tvSTTcau);
        tvCauhoilt = view.findViewById(R.id.tvCauhoilt);
        radioGroup = view.findViewById(R.id.radG);
        radA = view.findViewById(R.id.rad1);
        radB = view.findViewById(R.id.rad2);
        radC = view.findViewById(R.id.rad3);
        radD = view.findViewById(R.id.rad4);
        imvHinhanh = view.findViewById(R.id.imvHinhanh);
    }

    private void getListCauhoi(Integer vitri) {
        Integer stt = vitri + 1;
        Cauhoi cauhoi = mCauhoilithuyet.ListCauhoi.get(vitri);
        size = mCauhoilithuyet.ListCauhoi.size();
        String dapAn = cauhoi.getDapan().trim().toString();
        tvSTTcau.setText("Câu " + stt);
        tvCauhoilt.setText(cauhoi.getNoidung().toString());
        radA.setText(cauhoi.getA().toString());
        radB.setText(cauhoi.getB().toString());
        //thêm cái view vào layout để chứa hình ảnh
        //nếu get hinh anh == null thì ẩn view chứa hình ảnh đi và ngược lại

//        if(cauhoi.getHinhanh() == null) imvHinhanh.setVisibility(View.INVISIBLE);
//        else Picasso.get().load(cauhoi.getHinhanh()).into(imvHinhanh);

        if(cauhoi.getHinhanh() == null) imvHinhanh.setVisibility(View.GONE);
        else Picasso.get().load(cauhoi.getHinhanh()).into(imvHinhanh);

        if (cauhoi.getC() == null) radC.setVisibility(View.INVISIBLE);
        else radC.setText(cauhoi.getC().toString());
        if (cauhoi.getD() == null) radD.setVisibility(View.INVISIBLE);
        else radD.setText(cauhoi.getD().toString());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                clickRadio(i, dapAn);
            }
        });

    }

    private void clickRadio(int id, String dapAn) {
        System.out.println("1");
        if (id == R.id.rad1) { //so sanh cái id radio thay đổi với từng id radio
            if (dapAn.equals("A")) {
                radA.setBackgroundColor(Color.GREEN);
            } else radA.setBackgroundColor(Color.RED);
            radB.setBackgroundColor(Color.WHITE);
            radC.setBackgroundColor(Color.WHITE);
            radD.setBackgroundColor(Color.WHITE);

        } else if (id == R.id.rad2) {
            if (dapAn.equals("B")) {
                radB.setBackgroundColor(Color.GREEN);
            } else radB.setBackgroundColor(Color.RED);
            radA.setBackgroundColor(Color.WHITE);
            radC.setBackgroundColor(Color.WHITE);
            radD.setBackgroundColor(Color.WHITE);
        } else if (id == R.id.rad3) {
            if (dapAn.equals("C")) {
                radC.setBackgroundColor(Color.GREEN);
            } else radC.setBackgroundColor(Color.RED);
            radA.setBackgroundColor(Color.WHITE);
            radB.setBackgroundColor(Color.WHITE);
            radD.setBackgroundColor(Color.WHITE);
        } else if (id == R.id.rad4) {
            if (dapAn.equals("D")) {
                radD.setBackgroundColor(Color.GREEN);
            } else radD.setBackgroundColor(Color.RED);
            radA.setBackgroundColor(Color.WHITE);
            radB.setBackgroundColor(Color.WHITE);
            radC.setBackgroundColor(Color.WHITE);
        }
    }

}