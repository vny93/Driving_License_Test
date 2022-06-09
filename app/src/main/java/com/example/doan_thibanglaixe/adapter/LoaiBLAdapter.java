package com.example.doan_thibanglaixe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.model.Loaibang;

import java.util.List;

public class LoaiBLAdapter extends ArrayAdapter<Loaibang> {
    public static Integer maLoaiBL=0;
    public LoaiBLAdapter(@NonNull Context context, int resource, @NonNull List<Loaibang> object) {
        super(context, resource, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_spinnerloaibl, parent, false);
        TextView tvSelected = convertView.findViewById(R.id.tvSelected_maloaibl);
        Loaibang loailb = (Loaibang) this.getItem(position);
        if(loailb != null){
            tvSelected.setText(loailb.getTenloaibang());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaibl, parent, false);
        TextView tvThietBi = convertView.findViewById(R.id.tvLoaibl);
        Loaibang loailb = (Loaibang) this.getItem(position);
        if(loailb != null){
            tvThietBi.setText(loailb.getTenloaibang());
        }
        return convertView;
    }
}
