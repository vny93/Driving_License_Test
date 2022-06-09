package com.example.doan_thibanglaixe.screens.lambaithi;

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

public class FragmentLambaithi extends Fragment {

    TextView tvSTTcau, tvCauhoilt;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView imvHinhanh;
    View view = null;
    private LamBaiThiActivity mLamBaiThi;
    private Integer position = 0;
    public static Integer size_lbt;

    public FragmentLambaithi() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lambaithi, container, false);
        mLamBaiThi = (LamBaiThiActivity) getActivity();
        position = mLamBaiThi.getPosition_lbt();
        setControl();
        getDeThi2(position);

        return view;
    }


    private void getDeThi2(Integer vitri) {
        System.out.println("vitri"+vitri);
        Integer stt = vitri + 1;
        Cauhoi cauhoi = mLamBaiThi.ListCauhoirand.get(vitri);
        size_lbt = mLamBaiThi.ListCauhoirand.size();
        String dapAn = cauhoi.getDapan().trim().toString();
        tvSTTcau.setText("Câu " + stt);
        tvCauhoilt.setText(cauhoi.getNoidung().toString());
        radA.setText(cauhoi.getA().toString());
        radB.setText(cauhoi.getB().toString());
        //thêm cái view vào layout để chứa hình ảnh
        //nếu get hinh anh == null thì ẩn view chứa hình ảnh đi và ngược lại
        if(cauhoi.getHinhanh() == null) imvHinhanh.setVisibility(View.GONE);
        else Picasso.get().load(cauhoi.getHinhanh()).into(imvHinhanh);

        if (cauhoi.getC() == null) radC.setVisibility(View.INVISIBLE);
        else radC.setText(cauhoi.getC().toString());
        if (cauhoi.getD() == null) radD.setVisibility(View.INVISIBLE);
        else radD.setText(cauhoi.getD().toString());
        System.out.println("size_lbt:"+size_lbt);
        checkRadio(vitri,mLamBaiThi.traLoi.get(vitri).getTraloi()); // nếu đã có chọn rồi đáp án rồi thì gán lại vào radio tương ứng
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                System.out.println("chage:"+i);
                System.out.println("rad:"+R.id.rad1_lbt);
                mLamBaiThi.traLoi.get(vitri).setTraloi(clickRadio(i));
                System.out.println("traloi"+mLamBaiThi.traLoi.get(vitri).getTraloi());
            }
        });
    }

    private void checkRadio(Integer vitri,String cautl) {
        if(cautl.equals("A")){
            radA.setChecked(true);
        }else if(cautl.equals("B")){
            radB.setChecked(true);
        }else if(cautl.equals("C")){
            radC.setChecked(true);
        }else if(cautl.equals("D")){
            radD.setChecked(true);
        }
    }

    private String clickRadio(int id) {
        System.out.println("1");
        if (id == R.id.rad1_lbt) { //so sanh cái id radio thay đổi với từng id radio
            System.out.println("2");
            return "A";

        } else if (id == R.id.rad2_lbt) {
            return "B";
        } else if (id == R.id.rad3_lbt) {
            return "C";
        } else if (id == R.id.rad4_lbt) {
            return "D";
        }
        return "";
    }

    private void setControl() {
        tvSTTcau = view.findViewById(R.id.tvSTTcau_lbt);
        tvCauhoilt = view.findViewById(R.id.tvCauhoilt_lbt);
        radioGroup = view.findViewById(R.id.radG_lbt);
        radA = view.findViewById(R.id.rad1_lbt);
        radB = view.findViewById(R.id.rad2_lbt);
        radC = view.findViewById(R.id.rad3_lbt);
        radD = view.findViewById(R.id.rad4_lbt);
        imvHinhanh = view.findViewById(R.id.imvHinhanh_lbt);
    }
}