package com.example.doan_thibanglaixe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan_thibanglaixe.screens.luatgt.ChiTietLuatActivity;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.model.Loailuat;

import java.util.List;

public class LoailuatAdapter extends ArrayAdapter<Loailuat> {
    Context context;
    int resource;
    List<Loailuat> data;
    TextView tvloailuat;

    public LoailuatAdapter(@NonNull Context context, int resource, @NonNull List<Loailuat> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public void setFilterList(List<Loailuat> filter) {
        this.data = filter;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        tvloailuat = convertView.findViewById(R.id.tvLoailuat);
        Loailuat loailuat = data.get(position);
        tvloailuat.setText(loailuat.getTenLoaiLuatGt());
        tvloailuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChiTietLuatActivity.class);
                intent.putExtra("maloailuat",loailuat.getMaLoaiLuatGt());
                context.startActivity(intent);
            }
        });

        return convertView;
    }


}
