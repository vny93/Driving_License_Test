package com.example.doan_thibanglaixe.api;

import com.example.doan_thibanglaixe.model.Domand;
import com.example.doan_thibanglaixe.model.Taikhoan;
import com.example.doan_thibanglaixe.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiTKService {

    String domand = new Domand().getDomand();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiTKService apiTKService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiTKService.class);

    @GET("tk/get")
    Call<List<Taikhoan>> getListTK();

    @GET("tk/getById")
    Call<Taikhoan> getTKByID(@Query("tendangnhap") String tendn);

    @GET("tk/dangNhap")
    Call<Boolean> checkDN(@Query("tendangnhap") String tendn, @Query("matkhau") String mk);

}
