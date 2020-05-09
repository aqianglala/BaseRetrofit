package com.example.baseretrofit2.net;

import com.example.baseretrofit2.bean.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("banner/json")
    Call<Result<List<Banner>>> getBanner();
}
