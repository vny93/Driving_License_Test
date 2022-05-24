package com.example.doan_thibanglaixe.api;

import com.example.doan_thibanglaixe.model.Cauhoi;
import com.example.doan_thibanglaixe.model.Domand;
import com.example.doan_thibanglaixe.model.Ketqua;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiKetquaService {

    String domand = new Domand().getDomand();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiKetquaService apiKetquaService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiKetquaService.class);

    @GET("ketqua/max")
    Call<Integer> getMaxluotthi(@Query("id") String email,
                                   @Query("mabode") Integer maloailt);


    @POST("ketqua/insertKetqua")
    Call<Ketqua> insertKetqua(@Body Ketqua ketqua);

    @GET("ketqua/getLuotThi") //get lượt thi để lấy ra kết quả đã chọn
    Call<List<Ketqua>> getLuotthi(@Query("email") String email,
                            @Query("luotthi") Integer luotthi,
                            @Query("mabode") Integer mabode);

    @GET("ketqua/deleteKQ")
    Call<Void> xoaKQ(@Query("luotthi") Integer luotthi,
                     @Query("email") String email);

    @GET("ketqua/getSTT")
    Call<List<Integer>> getSTT(@Query("email") String email,
                               @Query("mabode") Integer mabode);
}
