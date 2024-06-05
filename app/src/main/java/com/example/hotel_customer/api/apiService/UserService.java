package com.example.hotel_customer.api.apiService;

import com.example.hotel_customer.data.Account;
import com.example.hotel_customer.data.User;
import com.example.hotel_customer.data.requestData.AccountLogin;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.requestData.ActiveAccountData;
import com.example.hotel_customer.data.requestData.CreateAccountData;
import com.example.hotel_customer.data.requestData.SendCodeData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    /*
    * AUTHENTICATION
    * */
    @POST("/user/auth/gen-account-token")
    public Call<ResponseData> genAccountToken(@Body AccountLogin account);

    @POST("/user/auth/gen-user-token")
    public Call<ResponseData> genUserToken(@Body AccountLogin account);

    @POST("/user/auth/decode-token")
    public Call<ResponseData> decodeToken();

    /*
    * ACCOUNT
    * */
    @GET("/user/accounts/{id}")
    public Call<ResponseData> getAccountInfo(@Path("id") int id);
    @GET("/user/accounts/email/{email}")
    public Call<ResponseData> getAccountByEmail(@Path("email") String email);
    @POST("/user/accounts/send-code")
    public Call<ResponseData> sendAccountCode(@Body SendCodeData sendCodeData);

    @PUT("/user/accounts/active")
    public Call<ResponseData> activeAccount(@Body ActiveAccountData activeAccountData);

    @PUT("/user/accounts")
    public Call<ResponseData> updateAccount(@Body Account account);

    @POST("/user/accounts")
    public Call<ResponseData> createAccount(@Body Account account);

    @GET("/user/accounts/{id}/users")
    public Call<ResponseData> getUsersByIdAccount(@Path("id") int id);

    /*
    * USER
    * */
    @GET("/user/users/{id}")
    public Call<ResponseData> getUser(@Path("id") int id);

    @GET("/user/users/check-exists/{username}")
    public Call<ResponseData> getUserByUsername(@Path("username") String username);

    @POST("/user/users")
    public Call<ResponseData> createUser(@Body User user);
}
