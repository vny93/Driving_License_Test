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
import com.example.doan_thibanglaixe.data.model.Luat;

import java.util.List;

public class LuatAdapter extends ArrayAdapter<Luat> {
    Context context;
    int resource;
    List<Luat> data;
    TextView tvluat,tvMucphat;

    public LuatAdapter(@NonNull Context context, int resource, @NonNull List<Luat> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public void setFilterList(List<Luat> filter) {
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
        tvluat = convertView.findViewById(R.id.tv_ndluat);
        tvMucphat = convertView.findViewById(R.id.tv_mpluat);
        Luat luat = data.get(position);
        tvluat.setText(luat.getNoidung());
        tvMucphat.setText(luat.getMucphat());
        return convertView;
    }


}
