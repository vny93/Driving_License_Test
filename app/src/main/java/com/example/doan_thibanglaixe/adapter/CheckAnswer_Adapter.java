package com.example.doan_thibanglaixe.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_thibanglaixe.LamBaiThi;
import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.fragment.Fragment_Lambaithi;
import com.example.doan_thibanglaixe.model.Cauhoi;

import java.util.ArrayList;
import java.util.List;

public class CheckAnswer_Adapter extends BaseAdapter {
    ArrayList data;
    LayoutInflater inflater;

    public CheckAnswer_Adapter(ArrayList data, Context context){
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_gvdscauhoi,null);
            viewHolder.tvNumAns = view.findViewById(R.id.tvNumAns);
            viewHolder.tvAns = view.findViewById(R.id.tvAns);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        int position = i + 1;
        viewHolder.tvNumAns.setText("Câu "+position+": ");
        String cault = LamBaiThi.traLoi.get(i).getTraloi().toString();
        viewHolder.tvAns.setText(cault);
        return view;
    }

    private static class ViewHolder{
        TextView tvNumAns, tvAns;
    }
}
