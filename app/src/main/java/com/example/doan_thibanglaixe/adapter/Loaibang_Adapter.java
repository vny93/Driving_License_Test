package com.example.doan_thibanglaixe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.model.Loaibang;
import com.example.doan_thibanglaixe.model.Loaide;
import com.example.doan_thibanglaixe.model.Loailithuyet;

import java.util.List;

public class Loaibang_Adapter extends RecyclerView.Adapter<Loaibang_Adapter.LoaibangHolder> {
    public static Loaibang loaibanglai;
    public Loaibang_Adapter(List<Loaibang> mLoaibang) {
        this.mLoaibang = mLoaibang;
    }

    private List<Loaibang> mLoaibang;
    @NonNull
    @Override
    public LoaibangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaide_banglai,parent,false);
        return new LoaibangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaibangHolder holder, int position) {
        Loaibang loaibang = mLoaibang.get(position);
        loaibanglai = new Loaibang(mLoaibang.get(0).getMaloaibang(),mLoaibang.get(0).getTenloaibang());
        System.out.println("Loại bằng lái là: "+loaibanglai.getMaloaibang());
        if(loaibang == null){
            return;
        }
        holder.tvLoaibang.setText(loaibang.getTenloaibang());
        holder.tvLoaibang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaibanglai = loaibang;
                System.out.println("maloaibang"+loaibanglai.getMaloaibang());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mLoaibang != null){
            return mLoaibang.size();
        }
        return 0;
    }

    public class LoaibangHolder extends RecyclerView.ViewHolder {
        private TextView tvLoaibang;
        public LoaibangHolder(@NonNull View itemView) {
            super(itemView);
            tvLoaibang = itemView.findViewById(R.id.tv_lt2);
        }
    }
}
