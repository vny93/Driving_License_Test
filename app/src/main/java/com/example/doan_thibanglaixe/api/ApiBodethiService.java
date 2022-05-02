package com.example.doan_thibanglaixe.api;

import com.example.doan_thibanglaixe.model.Bodethi;
import com.example.doan_thibanglaixe.model.Domand;
import com.example.doan_thibanglaixe.model.Loaibang;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiBodethiService {

    String domand = new Domand().getDomand();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiBodethiService apiBodethiService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiBodethiService.class);

    @GET("bodethi/getBodethi")
    Call<Bodethi> getBode(@Query("maloaibang") Integer maloaibang,
                                @Query("maloaide") Integer maloaide);


}
