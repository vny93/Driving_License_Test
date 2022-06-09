package com.example.doan_thibanglaixe.data.api;

import com.example.doan_thibanglaixe.data.model.PathApi;
import com.example.doan_thibanglaixe.data.model.Loailithuyet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiLoailithuyetService {

    String domand = new PathApi().getPathApi();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiLoailithuyetService apiLoailtService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLoailithuyetService.class);

    @GET("loailt/get")
    Call<List<Loailithuyet>> getListLoailt();

}
