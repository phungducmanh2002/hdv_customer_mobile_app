package com.example.hotel_customer.ui.activity.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.R;
import com.example.hotel_customer.command.ResCommand;
import com.example.hotel_customer.data.Account;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.User;
import com.example.hotel_customer.data.responseData.UserAvatar;
import com.example.hotel_customer.databinding.ActivityProfileBinding;
import com.example.hotel_customer.helper.MapperHelper;
import com.example.hotel_customer.imageService.ImageServiceProxy;
import com.example.hotel_customer.ui.activity.booking.BookingHistoryActivity;
import com.example.hotel_customer.ui.dialog.DialogComfirm;
import com.example.hotel_customer.ui.dialog.DialogEditProfile1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    DialogEditProfile1 dialogEditProfile1;
    DialogComfirm dialogComfirmLogout;
    boolean reload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        reload = getIntent().getBooleanExtra("reload", false);
        if(reload){
            reLoadProfile();
        }

        setEvent();
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadProfile();
    }
    private void setEvent() {
        binding.btnBookingHistroy.setOnClickListener(v -> {
            Intent bookingHistoryIntent = new Intent(this, BookingHistoryActivity.class);
            startActivity(bookingHistoryIntent);
        });

        binding.btnUpdate.setOnClickListener(v -> {
            if(dialogEditProfile1 == null){
                dialogEditProfile1 = new DialogEditProfile1(this);
                dialogEditProfile1.setOnUpdateAvatar(object -> {
                    startUpdateAvatar();
                });
                dialogEditProfile1.setOnUpdateUsername(object -> {
                    startUpdateUsername();
                });
                dialogEditProfile1.setOnUpdateAccount(object -> {
                    startUpdateAccount();
                });
            }
            dialogEditProfile1.show();
        });

        binding.btnLogout.setOnClickListener(v -> {
            if(dialogComfirmLogout == null){
                dialogComfirmLogout = new DialogComfirm(this);
                dialogComfirmLogout.setContent("Bạn có muốn đăng xuất?");
                dialogComfirmLogout.setOnConfirm(object -> {
                    logout();
                });
            }
            dialogComfirmLogout.show();
        });

        binding.back.setOnClickListener(v -> {
            finish();
        });
    }
    private void startUpdateAccount() {
        Intent updateAccountInfoIntent = new Intent(this, SignupActivity.class);
        updateAccountInfoIntent.putExtra("account", new Account());
        startActivity(updateAccountInfoIntent);
        finish();
    }
    private void startUpdateUsername() {
        
    }
    private void startUpdateAvatar() {
        Intent updateAvatarIntent = new Intent(this, UpdateAvatarActivity.class);
        updateAvatarIntent.putExtra("idUser", 1);
        startActivity(updateAvatarIntent);
        finish();
    }
    private void logout() {
        MyApplication.AppSession.gI().reset();
        finish();
    }
    private void loadProfile() {
        Account account = MyApplication.AppSession.gI().getAccount();
        User customer = MyApplication.AppSession.gI().getCustomer();

        binding.username.setText(customer.getUsername());

        binding.firstName.setText(account.getFirstName());
        binding.lastName.setText(account.getLastName());
        binding.gender.setText(account.getGender() == 0 ? "Nam" : account.getGender() == 1 ? "Nữ" : "Khác");
        binding.email.setText(account.getEmail());
        
        loadUserAvatar();
    }
    private void loadUserAvatar() {
        if(MyApplication.AppSession.gI().getCustomer().getUserAvatar() == null){
            ResCommand resCommand = new ResCommand();
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
            binding.avatar.setImageBitmap(bitmap);
        });
    }
    private void reLoadProfile() {
    }
}