package com.example.hotel_customer.api.apiService;

import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.requestData.AccountLogin;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ResService {
    /*
    * IMAGE
    * */
    @POST("/res/images")
    @Multipart
    public Call<ResponseData> createImage(@Part MultipartBody.Part file);

    @GET("/res/images/{id}")
    public  Call<ResponseData> getImageById(@Path("id") int id);
    /*
     * AVATAR
     * */

    @GET("/res/avatars/{id}/images")
    public  Call<ResponseData> getUserAvatarImage(@Path("id") int id);

    @GET("/res/avatars/{id}")
    public  Call<ResponseData> getUserAvatar(@Path("id") int id);

    @POST("/res/avatars/{idUser}")
    @Multipart
    public  Call<ResponseData> createUserAvatar(@Path("idUser") int idUser, @Part MultipartBody.Part image);

    /*
     * HOTEL_IMAGE
     * */

    /*
     * ROOM_IMAGE
     * */

}
