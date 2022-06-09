package com.example.doan_thibanglaixe.screens.ketqua;

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
import com.example.doan_thibanglaixe.data.model.Ketqua;
import com.squareup.picasso.Picasso;


public class FragmentChitietkq extends Fragment {
    TextView tvSTTcau, tvCauhoi;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView imvHinhanh;
    View view = null;
    private ChiTietKqActivity mChiTietKetQua;
    private Integer position = 0;
    public static Integer size;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chitietkq, container, false);
        mChiTietKetQua = (ChiTietKqActivity) getActivity();
        position = mChiTietKetQua.getPosition();
        setControl();
        getKQ(position);
        return view;
    }

    private void getKQ(Integer vitri) {
        Integer stt = vitri + 1;
        Cauhoi cauhoi = mChiTietKetQua.ListCauhoi_ctkq.get(vitri);
        Ketqua ketqua = mChiTietKetQua.ListKetQuaDachon.get(vitri);
        size = mChiTietKetQua.ListCauhoi_ctkq.size();
        String dapAn = cauhoi.getDapan().trim().toString();
        String cautl = ketqua.getPhuongan().trim().toString();
        tvSTTcau.setText("Câu " + stt);
        tvCauhoi.setText(cauhoi.getNoidung().toString());
        radA.setText(cauhoi.getA().toString());
        radB.setText(cauhoi.getB().toString());
        //thêm cái view vào layout để chứa hình ảnh
        //nếu get hinh anh == null thì ẩn view chứa hình ảnh đi và ngược lại
        if(cauhoi.getHinhanh() == null) imvHinhanh.setVisibility(View.INVISIBLE);
        else Picasso.get().load(cauhoi.getHinhanh()).into(imvHinhanh);

        if (cauhoi.getC() == null) radC.setVisibility(View.INVISIBLE);
        else radC.setText(cauhoi.getC().toString());
        if (cauhoi.getD() == null) radD.setVisibility(View.INVISIBLE);
        else radD.setText(cauhoi.getD().toString());
        checkRadio(vitri, cautl, dapAn); // nếu đã có chọn rồi đáp án rồi thì gán lại vào radio tương ứng

    }

    private void checkRadio(Integer vitri, String cautl, String dapAn) {
        //nếu mình chọn A mà đáp án là A thì tô xanh, ngược lại tô đỏ
        if (cautl.equals("A")) {
            radA.setChecked(true);
            if (dapAn.equals("A")) {
                radA.setBackgroundColor(Color.GREEN);
            } else{
                radA.setBackgroundColor(Color.RED);
                timDapAnDung(dapAn);
            }

        } else if (cautl.equals("B")) {
            radB.setChecked(true);
            if (dapAn.equals("B")) {
                radB.setBackgroundColor(Color.GREEN);
            } else{
                radB.setBackgroundColor(Color.RED);
                timDapAnDung(dapAn);
            }

        } else if (cautl.equals("C")) {
            radC.setChecked(true);
            if (dapAn.equals("C")) {
                radC.setBackgroundColor(Color.GREEN);
            } else {
                radC.setBackgroundColor(Color.RED);
                timDapAnDung(dapAn);
            }

        } else if (cautl.equals("D")) {
            radD.setChecked(true);
            if (dapAn.equals("D")) {
                radD.setBackgroundColor(Color.GREEN);
            } else{
                radD.setBackgroundColor(Color.RED);
                timDapAnDung(dapAn);
            }
        }
    }

    private void timDapAnDung(String dapAn) {
        if (dapAn.equals("A")) {
            radA.setBackgroundColor(Color.GREEN);
        }
        if (dapAn.equals("B")) {
            radB.setBackgroundColor(Color.GREEN);
        }
        if (dapAn.equals("C")) {
            radC.setBackgroundColor(Color.GREEN);
        }
        if (dapAn.equals("D")) {
            radD.setBackgroundColor(Color.GREEN);
        }
    }

    private void setControl() {
        tvSTTcau = view.findViewById(R.id.tvSTTcau_tckq);
        tvCauhoi = view.findViewById(R.id.tvCauhoi_ctkq);
        radioGroup = view.findViewById(R.id.radG_ctkq);
        radA = view.findViewById(R.id.rad1_ctkq);
        radB = view.findViewById(R.id.rad2_ctkq);
        radC = view.findViewById(R.id.rad3_ctkq);
        radD = view.findViewById(R.id.rad4_ctkq);
        imvHinhanh = view.findViewById(R.id.imvHinhanh_kq);
    }
}