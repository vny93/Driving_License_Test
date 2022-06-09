package com.example.doan_thibanglaixe.data.api;

import com.example.doan_thibanglaixe.data.model.Bodethi;
import com.example.doan_thibanglaixe.data.model.PathApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiBodethiService {

    String domand = new PathApi().getPathApi();
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
