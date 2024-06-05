package com.example.hotel_customer.command;

import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.api.RetrofitClient;
import com.example.hotel_customer.api.apiService.ResService;
import com.example.hotel_customer.api.apiService.UserService;
import com.example.hotel_customer.data.Account;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.Role;
import com.example.hotel_customer.data.User;
import com.example.hotel_customer.data.requestData.AccountLogin;
import com.example.hotel_customer.data.requestData.CreateAccountData;

import retrofit2.Callback;

public class UserCommand implements I_Command{
    UserService userService;
    ResService resService;
    public UserCommand(){
        userService  = RetrofitClient.gI().getRetrofit().create(UserService.class);
        resService  = RetrofitClient.gI().getRetrofit().create(ResService.class);
    }
    public void signup(Account account, Callback<ResponseData> callback){
        userService.createAccount(account).enqueue(callback);
    }
    public void getAccountByEmail(String email, Callback<ResponseData> callback){
        userService.getAccountByEmail(email).enqueue(callback);
    }
    public void genAccountToken(String email, String password, Callback<ResponseData> callback){
        AccountLogin accountLogin = new AccountLogin();
        accountLogin.setEmail(email);
        accountLogin.setPassword(password);
        userService.genAccountToken(accountLogin).enqueue(callback);
    }
    public void getAccountUsers(int idAccount, Callback<ResponseData> callback){
        userService.getUsersByIdAccount(idAccount).enqueue(callback);
    }
    public void getUserByUsername(String username, Callback<ResponseData> callback){
        userService.getUserByUsername(username).enqueue(callback);
    }
    private void createUser(String username, int idRole, Callback<ResponseData> callback){
        int idAccount = MyApplication.AppSession.gI().getAccount().getId();

        User user = new User();
        user.setUsername(username);
        user.setIdRole(idRole);
        user.setIdAccount(idAccount);

        userService.createUser(user).enqueue(callback);
    }
    public void createCustomer(String username, Callback<ResponseData> callback){
        createUser(username, Role.CUSTOMER, callback);
    }
    @Override
    public void exec() {

    }
}
