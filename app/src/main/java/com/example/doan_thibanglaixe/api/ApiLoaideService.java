package com.example.doan_thibanglaixe.api;

import com.example.doan_thibanglaixe.model.Domand;
import com.example.doan_thibanglaixe.model.Loaide;
import com.example.doan_thibanglaixe.model.Loailithuyet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiLoaideService {

    String domand = new Domand().getDomand();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiLoaideService apiLoaideService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLoaideService.class);

    @GET("loaide/get")
    Call<List<Loaide>> getListLoaide();

}
