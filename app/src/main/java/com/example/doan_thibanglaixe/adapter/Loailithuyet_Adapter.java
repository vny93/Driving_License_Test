package com.example.doan_thibanglaixe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_thibanglaixe.Cauhoilithuyet;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.model.Loailithuyet;

import java.util.List;

public class Loailithuyet_Adapter extends RecyclerView.Adapter<Loailithuyet_Adapter.LoailithuyetHolder> {

    private List<Loailithuyet>mLoailithuyet;
    private IClickTVListener mIClickTVListener;
    public static Loailithuyet loailt;

    public interface IClickTVListener{
        void onClickTv();
    }
    public Loailithuyet_Adapter(List<Loailithuyet> mLoailithuyet, IClickTVListener listener) {
        this.mLoailithuyet = mLoailithuyet;
        this.mIClickTVListener = listener;
    }

    @NonNull
    @Override
    public LoailithuyetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lithuyet,parent,false);
        return new LoailithuyetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoailithuyetHolder holder, int position) {
        Loailithuyet loailithuyet = mLoailithuyet.get(position);
        if(loailithuyet == null){
            return;
        }
        holder.tvLoailt.setText(loailithuyet.getTenloai());
        holder.tvLoailt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loailt = loailithuyet;
                mIClickTVListener.onClickTv();
                System.out.println("maloailithuyet"+loailithuyet.getMaLoaiLiThuyet());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mLoailithuyet != null){
            return mLoailithuyet.size();
        }
        return 0;
    }

    public class LoailithuyetHolder extends RecyclerView.ViewHolder{
        private TextView tvLoailt;
        public LoailithuyetHolder(@NonNull View itemView) {
            super(itemView);
            tvLoailt = itemView.findViewById(R.id.tv_lt);
        }
    }
}
