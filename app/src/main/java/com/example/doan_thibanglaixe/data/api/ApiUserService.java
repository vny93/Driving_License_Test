package com.example.doan_thibanglaixe.data.api;

import com.example.doan_thibanglaixe.data.model.PathApi;
import com.example.doan_thibanglaixe.data.model.Taikhoan;
import com.example.doan_thibanglaixe.data.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiUserService {

//    Domand domand = new Domand();
//    String url = domand.getDomand();
    String url = new PathApi().getPathApi();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiUserService apiUserService = new Retrofit.Builder()
            .baseUrl(url) //truyền domand
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiUserService.class);

    @GET("user/get")
    Call<List<User>> getListUser();

    @GET("user/getById")
    Call<User> getUserByID(@Query("email") String email);

    @GET("user/getuserbytk")
    Call<User> getUserByTk(@Query("email") String email,
                           @Query("pass")String pass);

    @POST("user/insert")
    Call<User> insertTK(@Query("email") String email,
                        @Query("hoten")String hoten,
                        @Query("sdt")String sdt,
                        @Query("matkhau")String mk);

    @PUT("user/update")
    Call<User> update(@Body User user);

    @PUT("user/updateTK")
    Call<Taikhoan> updateTK(@Body Taikhoan taikhoan);
}
