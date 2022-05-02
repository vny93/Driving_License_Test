package com.example.doan_thibanglaixe.api;

import com.example.doan_thibanglaixe.model.Domand;
import com.example.doan_thibanglaixe.model.Loaibang;
import com.example.doan_thibanglaixe.model.Loailithuyet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiLoaiBanglaiService {

    String domand = new Domand().getDomand();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiLoaiBanglaiService apiLoaibanglaiService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLoaiBanglaiService.class);

    @GET("loaibang/get")
    Call<List<Loaibang>> getListLoaibang();


}
