package com.example.doan_thibanglaixe.api;

import com.example.doan_thibanglaixe.model.Domand;
import com.example.doan_thibanglaixe.model.Loaimeo;
import com.example.doan_thibanglaixe.model.Meo;
import com.example.doan_thibanglaixe.model.Taikhoan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMeothiService {
    String domand = new Domand().getDomand();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiMeothiService apiMeothiService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiMeothiService.class);

    @GET("meo/getLoaiMeo")
    Call<List<Loaimeo>> getListLoaiMeo();

    @GET("meo/getMeo")
    Call<Meo> getMeo(@Query("maloaimeo") Integer maloaimeo);
}
