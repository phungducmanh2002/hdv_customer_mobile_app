package com.example.hotel_customer.command;

import android.util.Log;

import com.example.hotel_customer.api.RetrofitClient;
import com.example.hotel_customer.api.apiService.ResService;
import com.example.hotel_customer.data.ResponseData;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

public class ResCommand implements I_Command{
    ResService resService ;
    public ResCommand(){
        resService  = RetrofitClient.gI().getRetrofit().create(ResService.class);
    }
    public void getUserAvatar(int idUser, Callback<ResponseData> callback){
         resService.getUserAvatar(idUser).enqueue(callback);
    }
    public void updateUserAvatar(int idUser, byte[] image, Callback<ResponseData> callback){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "newavatar.image", requestBody);
        try {
            resService.createUserAvatar(idUser, body).enqueue(callback);
        }
        catch (Exception ex){
            Log.d("CREATE_USER_AVATAR", ex.getMessage());
        }
    }
    @Override
    public void exec() {

    }
}
