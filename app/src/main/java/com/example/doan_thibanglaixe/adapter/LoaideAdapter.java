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
import com.example.doan_thibanglaixe.data.model.Loaide;

import java.util.List;

public class LoaideAdapter extends ArrayAdapter<Loaide> {
    public static Integer maLoaide=0;
    public LoaideAdapter(@NonNull Context context, int resource, @NonNull List<Loaide> object) {
        super(context, resource, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_spinnerloaide, parent, false);
        TextView tvSelected = convertView.findViewById(R.id.tvSelected_maloaide);
        Loaide loaide = (Loaide) this.getItem(position);
        if(loaide != null){
            tvSelected.setText(loaide.getTenloaide());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaide, parent, false);
        TextView tvThietBi = convertView.findViewById(R.id.tvLoaide);
        Loaide loaide = (Loaide) this.getItem(position);
        if(loaide != null){
            tvThietBi.setText(loaide.getTenloaide());
        }
        return convertView;
    }
}
