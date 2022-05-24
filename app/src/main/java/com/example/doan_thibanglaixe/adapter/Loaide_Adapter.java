package com.example.doan_thibanglaixe.adapter;

import android.graphics.Color;
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

public class Loaide_Adapter extends RecyclerView.Adapter<Loaide_Adapter.LoaideHolder> {
    public static Loaide loaiBoDe;
    public static Integer maLoaide=0;
    private List<Loaide> mLoaide;

    public Loaide_Adapter(List<Loaide> mLoaide) {
        this.mLoaide = mLoaide;
    }

    @NonNull
    @Override
    public LoaideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaide_banglai,parent,false);
        return new LoaideHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaideHolder holder, int position) {
        Loaide loaide = mLoaide.get(position);
        loaiBoDe = new Loaide(mLoaide.get(0).getMaloaide(),mLoaide.get(0).getSocau(),
                mLoaide.get(0).getThoigian(),mLoaide.get(0).getTenloaide());
        System.out.println("Loại đề là: "+loaiBoDe.getMaloaide());
        if(loaide == null){
            return;
        }
        holder.tvLoaide.setText(loaide.getTenloaide());
       // loaiBoDe.setMaloaide(1);
        holder.tvLoaide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiBoDe = loaide;
                maLoaide = loaide.getMaloaide();
//                System.out.println("holder "+holder.tvLoaide.getBackground());
//                if (holder.tvLoaide.getBackground()==null || holder.tvLoaide.getBackground().equals(Color.BLUE))
//                    holder.tvLoaide.setBackgroundColor(Color.BLUE);
//                else
//                    holder.tvLoaide.setBackgroundColor(Color.WHITE);

                System.out.println("maloaide"+loaiBoDe.getMaloaide());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mLoaide != null){
            return mLoaide.size();
        }
        return 0;
    }

    public class LoaideHolder extends RecyclerView.ViewHolder {
        private TextView tvLoaide;
        public LoaideHolder(@NonNull View itemView) {
            super(itemView);
            tvLoaide = itemView.findViewById(R.id.tv_lt2);
        }
    }
}
