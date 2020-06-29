package com.example.jsonretrofitvolley.Interface;

import com.example.jsonretrofitvolley.ModeloDatos.ListaBancos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface KushkiPagos {

    @GET("transfer-subscriptions/v1/bankList")
    Call<List<ListaBancos>> getPosts(@Header("Public-Merchant-Id")String MerchantId);

}
