package com.example.hotel_customer.ui.activity.hotel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.command.ResCommand;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.responseData.UserAvatar;
import com.example.hotel_customer.databinding.ActivityHomeBinding;
import com.example.hotel_customer.helper.MapperHelper;
import com.example.hotel_customer.imageService.ImageServiceProxy;
import com.example.hotel_customer.ui.activity.user.ProfileActivity;
import com.example.hotel_customer.ui.activity.user.SigninActivity;
import com.example.hotel_customer.ui.activity.user.SignupActivity;
import com.example.hotel_customer.ui.dialog.DialogNotify;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    ResCommand resCommand;
    DialogNotify dialogNotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resCommand = new ResCommand();
        dialogNotify = new DialogNotify(this    );
        setEvent();
    }
    @Override
    protected void onResume() {
        super.onResume();

        if(MyApplication.AppSession.gI().getCustomer() != null){
            initUiLogin();
            loadUserInfo();
        }
        else{
            initUiNotLogin();
        }
    }
    private void loadUserInfo() {
        binding.username.setText(MyApplication.AppSession.gI().getCustomer().getUsername());
        if(MyApplication.AppSession.gI().getCustomer().getUserAvatar() == null){
            resCommand.getUserAvatar(MyApplication.AppSession.gI().getCustomer().getId(), new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    if(response.isSuccessful()){
                        if(response.body().getCode() == 200){
                            if(response.body().getData() == null){

                            }
                            UserAvatar userAvatar = MapperHelper.ConvertFromObject(response.body().getData(), UserAvatar.class);
                            onGetCustomerAvatarSuccessfully(userAvatar);

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {

                }
            });
        }
        else{
            onGetCustomerAvatarSuccessfully(MyApplication.AppSession.gI().getCustomer().getUserAvatar());
        }
    }
    private void onGetCustomerAvatarSuccessfully(UserAvatar userAvatar) {

        MyApplication.AppSession.gI().getCustomer().setUserAvatar(userAvatar);

        ImageServiceProxy.gI().getImage(userAvatar.getIdImage(), objs -> {
            Bitmap bitmap = (Bitmap) objs[0];
            binding.userAvatar.setImageBitmap(bitmap);
        });
    }
    private void initUiNotLogin(){
        binding.header1.setVisibility(View.VISIBLE);
        binding.header2.setVisibility(View.GONE);
    }
    private void initUiLogin(){
        binding.header1.setVisibility(View.GONE);
        binding.header2.setVisibility(View.VISIBLE);

    }
    private void setEvent() {
        binding.btnSignin.setOnClickListener(v -> {
            Intent signinIntent = new Intent(this, SigninActivity.class);
            startActivity(signinIntent);
        });

        binding.btnSignup.setOnClickListener(v -> {
            Intent signupIntent = new Intent(this, SignupActivity.class);
            startActivity(signupIntent);
        });

        binding.userAvatar.setOnClickListener(v -> {
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);
        });
    }
}