package com.example.doan_thibanglaixe.adapter;

import android.app.Dialog;
import android.content.Context;
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

import com.example.doan_thibanglaixe.R;
import com.example.doan_thibanglaixe.data.api.ApiMeothiService;
import com.example.doan_thibanglaixe.data.model.Loaimeo;
import com.example.doan_thibanglaixe.data.model.Meo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeothiAdapter extends ArrayAdapter<Loaimeo> {
    Context context;
    int resource;
    List<Loaimeo> data;
    TextView tv_meothi, tvloaimeo;

    public MeothiAdapter(@NonNull Context context, int resource, @NonNull List<Loaimeo> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public void setFilterList(List<Loaimeo> filter) {
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
        tvloaimeo = convertView.findViewById(R.id.tv_meothi);
        Loaimeo loaimeo = data.get(position);
        tvloaimeo.setText(loaimeo.getTenloaimeo());
        tvloaimeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiMeothiService.apiMeothiService.getMeo(loaimeo.getMaloaimeo()).enqueue(new Callback<Meo>() {
                    @Override
                    public void onResponse(Call<Meo> call, Response<Meo> response) {
                        Chitietmeo(Gravity.CENTER,response.body().getNoidung());
                    }

                    @Override
                    public void onFailure(Call<Meo> call, Throwable t) {

                    }
                });
            }
        });

        return convertView;
    }

    private void Chitietmeo(int gravity, String noidung) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_meothi);

        Window window = dialog.getWindow();
        if (window == null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        //click ra bên ngoài để tắt dialog
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(true);
        }

        tv_meothi = dialog.findViewById(R.id.tvMeo);
        tv_meothi.setText(noidung);
        dialog.show();


    }

}
