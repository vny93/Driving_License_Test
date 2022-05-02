package com.example.doan_thibanglaixe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.model.Exam;

import java.util.ArrayList;
import java.util.List;

public class Exam_Adapter extends ArrayAdapter<Exam> {
    Context context;
    int resource;
    ArrayList<Exam> data;
    TextView tvName;
    ImageView imvExam;


    public Exam_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Exam> data) {
        super(context, resource, data);
        this.context = context;
        this.resource=resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        tvName = convertView.findViewById(R.id.tvExam);
        imvExam = convertView.findViewById(R.id.imvExam);
        Exam p = getItem(position);
        if(p!=null){
            tvName.setText(""+p.getName());
        }
        return convertView;
    }
}
