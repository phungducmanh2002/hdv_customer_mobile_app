package com.example.hotel_customer.hook.retrofit;

import com.example.hotel_customer.data.ResponseData;

import retrofit2.Call;
import retrofit2.Response;

public interface OnResponse {
    public void action(Call<ResponseData> call, Response<ResponseData> response);
}
