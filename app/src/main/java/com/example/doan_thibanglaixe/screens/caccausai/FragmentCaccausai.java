package com.example.doan_thibanglaixe.screens.caccausai;

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

public class FragmentCaccausai extends Fragment {
    TextView tvSTTcau, tvCauhoi;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView imvHinhanh;
    View view = null;
    private CacCauSaiActivity mCacCauSai;
    private Integer position = 0;
    public static Integer size;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_caccausai, container, false);
        mCacCauSai = (CacCauSaiActivity) getActivity();
        position = mCacCauSai.getPosition();
        setControl();
        getCauSai(position);
        return view;
    }
    private void getCauSai(Integer vitri) {
        Integer stt = vitri + 1;
        Cauhoi cauhoi = mCacCauSai.ListCausai.get(vitri);
        size = mCacCauSai.ListCausai.size();
        String dapAn = cauhoi.getDapan().trim().toString();
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
        timDapAnDung(dapAn); // nếu đã có chọn rồi đáp án rồi thì gán lại vào radio tương ứng

    }

    private void timDapAnDung(String dapAn) {
        if (dapAn.equals("A")) {
            radA.setBackgroundColor(Color.RED);
        }
        if (dapAn.equals("B")) {
            radB.setBackgroundColor(Color.RED);
        }
        if (dapAn.equals("C")) {
            radC.setBackgroundColor(Color.RED);
        }
        if (dapAn.equals("D")) {
            radD.setBackgroundColor(Color.RED);
        }
    }

    private void setControl() {
        tvSTTcau = view.findViewById(R.id.tvSTTcausai);
        tvCauhoi = view.findViewById(R.id.tvCauhoi_cs);
        radioGroup = view.findViewById(R.id.radG_cs);
        radA = view.findViewById(R.id.rad1_cs);
        radB = view.findViewById(R.id.rad2_cs);
        radC = view.findViewById(R.id.rad3_cs);
        radD = view.findViewById(R.id.rad4_cs);
        imvHinhanh = view.findViewById(R.id.imvHinhanh_cs);
    }
}