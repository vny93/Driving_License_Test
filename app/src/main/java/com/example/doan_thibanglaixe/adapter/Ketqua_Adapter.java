//package com.example.doan_thibanglaixe.adapter;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//
//import com.example.doan_thibanglaixe.ChiTietKetQua;
//import com.example.doan_thibanglaixe.MainActivity;
//import com.example.doan_thibanglaixe.api.ApiKetquaService;
//import com.example.doan_thibanglaixe.fragment.Fragment_Ketqua;
//import com.example.doan_thibanglaixe.model.Exam;
//import com.example.doan_thibanglaixe.model.Ketqua;
//
//import java.util.ArrayList;
//
//import com.example.doan_thibanglaixe.R;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class Ketqua_Adapter extends ArrayAdapter<Ketqua> {
//    Context context;
//    int resource;
//    ArrayList<Ketqua> data;
//    TextView tvKq;
//    ImageView imvDelete;
//    Button btnHuy, btnXoa;
//
//
//    public Ketqua_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Ketqua> data) {
//        super(context, resource, data);
//        this.context = context;
//        this.resource = resource;
//        this.data = data;
//    }
//    public void setFilterList(ArrayList<Ketqua> filter){
//        this.data = filter;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        System.out.println("cayyyyyy");
//        convertView = LayoutInflater.from(context).inflate(resource, null);
//        tvKq = convertView.findViewById(R.id.tvExam);
//        imvDelete = convertView.findViewById(R.id.imvDelete);
//        int soDe = position+1;
//        tvKq.setText("Đề "+soDe);
//        tvKq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("huhuhuhu");
//                Intent intent = new Intent(getContext(), ChiTietKetQua.class);
//                intent.putExtra("luotthi",soDe);
//                context.startActivity(intent);
//            }
//        });
//        String email = MainActivity.user.getEmail().toString();
//        imvDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                xoaKQ(Gravity.CENTER, soDe,email);
//            }
//        });
//        return convertView;
//    }
//
//    private void xoaKQ(int gravity, Integer soDe, String email){
//
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_xoa_ket_qua);
//
//        Window window = dialog.getWindow();
//        if (window == null)
//            return;
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//
//        //click ra bên ngoài để tắt dialog
//        if (Gravity.CENTER == gravity) {
//            dialog.setCancelable(false);
//        } else {
//            dialog.setCancelable(true);
//        }
//        btnXoa = dialog.findViewById(R.id.btnXoaTB);
//        btnHuy = dialog.findViewById(R.id.btnHuyTB);
//        btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                xoa(soDe,email);
//                dialog.dismiss();
//
//            }
//        });
//        btnHuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//    public void xoa(Integer soDe, String email){
//        ApiKetquaService.apiKetquaService.xoaKQ(soDe,email).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                thongBaoThanhCong(Gravity.CENTER, "Xóa thành công!");
//                Fragment_Ketqua fm = new Fragment_Ketqua();
//                fm.getSode();
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    private void thongBaoThanhCong(int gravity, String text) {
//        //xử lý vị trí của dialog
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_thanh_cong);
//
//        Window window = dialog.getWindow();
//        if (window == null)
//            return;
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//
//        //click ra bên ngoài để tắt dialog
//        if (Gravity.CENTER == gravity) {
//            dialog.setCancelable(true);
//        } else {
//            dialog.setCancelable(true);
//        }
//        TextView tvThongBao = dialog.findViewById(R.id.tvThongbao);
//        tvThongBao.setText(text);
//        dialog.show();
//    }
//}
