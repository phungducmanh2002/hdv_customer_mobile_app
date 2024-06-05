package com.example.hotel_customer.ui.activity.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.R;
import com.example.hotel_customer.command.ResCommand;
import com.example.hotel_customer.command.UserCommand;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.responseData.UserAvatar;
import com.example.hotel_customer.databinding.ActivityUpdateAvatarBinding;
import com.example.hotel_customer.helper.MapperHelper;
import com.example.hotel_customer.helper.StreamHelper;
import com.example.hotel_customer.hook.retrofit.CustomeCallback;
import com.example.hotel_customer.ui.dialog.DialogWaiting;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAvatarActivity extends AppCompatActivity {
    ActivityUpdateAvatarBinding binding;
    int idUser;
    ResCommand resCommand;
    DialogWaiting dialogWaiting;
    byte[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUpdateAvatarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idUser = getIntent().getIntExtra("idUser", -1);
        resCommand = new ResCommand();
        dialogWaiting = new DialogWaiting(this);
        setEvent();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        Uri uri = data.getData();
        if(uri == null){
            return;
        }
        try {
            InputStream iStream =   getContentResolver().openInputStream(uri);
            byte[] avatarData = StreamHelper.GetBytes(iStream);
            onSelectAvatarResponse(avatarData);
        } catch (IOException e) {
//            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("SELECT_AVATAR_RESULT", e.getMessage());
        }
    }
    private void onSelectAvatarResponse(byte[] avatarData) {
        byte[] avatarDataClone = avatarData.clone();
        image = avatarData.clone();
        binding.avatar.setImageBitmap(StreamHelper.Bytes2Bitmap(avatarDataClone));
    }
    private void step1UpdateUserAvatar(){
        byte[] avatarData = image.clone();
        showDialogWaiting();
        int idUser = MyApplication.AppSession.gI().getCustomer().getId();

        resCommand.updateUserAvatar(
                idUser,
                avatarData,
                new CustomeCallback(
                        this,
                        200,
                        (call, response) -> {
                            onUpdateUserAvatarSuccessfully();
                        },
                        (call, response) -> {
                            closeDialogWaiting();
                        },
                        (call, t) -> {
                            closeDialogWaiting();
                        }));
    }
    private void onUpdateUserAvatarSuccessfully() {
        reloadUserAvatar();
    }
    private void reloadUserAvatar() {
        int idUser = MyApplication.AppSession.gI().getCustomer().getId();

        resCommand.getUserAvatar(
                idUser,
                new CustomeCallback(
                        this,
                        200,
                        (call, response) -> {
                            UserAvatar userAvatar  = MapperHelper.ConvertFromObject(response.body().getData(), UserAvatar.class);
                            MyApplication.AppSession.gI().getCustomer().setUserAvatar(userAvatar);
                            onReloadUserAvatarSuccessfully();
                        },
                        (call, response) -> {
                            closeDialogWaiting();
                        },
                        (call, t) -> {
                            closeDialogWaiting();
                        }));
    }
    private void onReloadUserAvatarSuccessfully() {
        finish();
    }
    private void setEvent() {
        binding.back.setOnClickListener(v -> {
            finish();
        });

        binding.btnNext.setOnClickListener(v -> {
            if(image != null)
            startUpdateUserAvatar();
            else{
                binding.select.callOnClick();
            }
        });

        binding.select.setOnClickListener(v -> {
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080,1080).start();
        });
    }
    private void startUpdateUserAvatar() {
        /**/
        step1UpdateUserAvatar();
    }
    public void gotoProfile(){
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("reload", true);
        startActivity(profileIntent);
        finish();
    }
    private void showDialogWaiting(){
        if(dialogWaiting == null){
            dialogWaiting = new DialogWaiting(this);
        }
        dialogWaiting.show();
    }
    private void closeDialogWaiting(){
        if(dialogWaiting != null){
            dialogWaiting.cancel();
        }
    }
}