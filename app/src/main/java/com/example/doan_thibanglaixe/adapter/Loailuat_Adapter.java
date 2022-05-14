package com.example.doan_thibanglaixe.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan_thibanglaixe.ChiTietKetQua;
import com.example.doan_thibanglaixe.ChiTietLuat;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.TraCuuLuat;
import com.example.doan_thibanglaixe.api.ApiMeothiService;
import com.example.doan_thibanglaixe.model.Loailuat;
import com.example.doan_thibanglaixe.model.Loaimeo;
import com.example.doan_thibanglaixe.model.Meo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loailuat_Adapter extends ArrayAdapter<Loailuat> {
    Context context;
    int resource;
    List<Loailuat> data;
    TextView tvloailuat;

    public Loailuat_Adapter(@NonNull Context context, int resource, @NonNull List<Loailuat> data) {
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
                Intent intent = new Intent(getContext(), ChiTietLuat.class);
                intent.putExtra("maloailuat",loailuat.getMaLoaiLuatGt());
                context.startActivity(intent);
            }
        });

        return convertView;
    }


}
