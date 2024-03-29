package com.example.doan_thibanglaixe.data.api;

import com.example.doan_thibanglaixe.data.model.Cauhoi;
import com.example.doan_thibanglaixe.data.model.PathApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCauhoiiService {

    String domand = new PathApi().getPathApi();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiCauhoiiService apiCauhoiService = new Retrofit.Builder()
            .baseUrl(domand) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiCauhoiiService.class);


    @GET("cauhoi/getCauhoiLT")
    Call<List<Cauhoi>> getCauhoiLT(@Query("mabode") Integer mabode,
                                   @Query("maloailt") Integer maloailt);

    @GET("cauhoi/randDeThi")
    Call<List<Cauhoi>> ranđDeThi(@Query("mabode") Integer mabode);

//    @GET("cauhoi/get-rand")
//    Call<List<Cauhoi>> ranđDeThi(@Query("mabode") Integer mabode);

    @GET("cauhoi/getkqch")
    Call<List<Cauhoi>> getCauhoi_KQ(@Query("email") String email,
                                    @Query("luotthi") Integer luotthi,
                                    @Query("mabode") Integer mabode);

    @GET("cauhoi/getcausai")
    Call<List<Cauhoi>> getCausai(@Query("email") String email,
                                    @Query("mabode") Integer mabode);
}
