package com.example.doan_thibanglaixe.data.api;

import com.example.doan_thibanglaixe.data.model.PathApi;
import com.example.doan_thibanglaixe.data.model.Loailuat;
import com.example.doan_thibanglaixe.data.model.Luat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiLuatGTService {
    String domand = new PathApi().getPathApi();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiLuatGTService apiluatGTService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLuatGTService.class);

    @GET("luat/getLoailuat")
    Call<List<Loailuat>> getListLoaiLuat();

    @GET("luat/getLuat")
    Call<List<Luat>> getLuat(@Query("maloailuat") Integer maloailuat);
}
