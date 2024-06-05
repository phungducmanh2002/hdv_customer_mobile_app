package com.example.hotel_customer.hook.retrofit;

import retrofit2.Call;

public interface OnFailure {
    public void action(Call call, Throwable t);
}
