package com.example.hotel_customer.ui.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.command.UserCommand;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.User;
import com.example.hotel_customer.databinding.ActivityCreateCustomerBinding;
import com.example.hotel_customer.helper.MapperHelper;
import com.example.hotel_customer.ui.dialog.DialogNotify;
import com.example.hotel_customer.ui.dialog.DialogWaiting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCustomerActivity extends AppCompatActivity {
    ActivityCreateCustomerBinding binding;
    UserCommand userCommand;
    DialogWaiting dialogWaiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCreateCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userCommand = new UserCommand();
        setEvent();
    }
    private void setEvent() {
        binding.btnNext.setOnClickListener(v -> {
            step1CheckUsername();
        });
    }
    private void step1CheckUsername() {
        String username = binding.ipUsername.getText();
        if(username.equals("")){
            binding.ipUsername.setFocused();
            binding.ipUsername.setWarning("username không được để trống");
            return;
        }

        showDialogWaiting();

        userCommand.getUserByUsername(username, new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode() == 200){
                        if(response.body().getData() == null){
                            step2CreateCustomer(username);
                        }
                        else{
                            binding.ipUsername.setWarning("username đã tồn tại");
                            closeDialogWaiting();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });


    }
    private void step2CreateCustomer(String username) {
        userCommand.createCustomer(username, new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){
                    if(response.body().getCode() == 200){
                        User customer = MapperHelper.ConvertFromObject(response.body().getData(), User.class);
                        step3OnCraeteUserSuccessfully(customer);
                    }
                    else{
                        DialogNotify dialogNotify = new DialogNotify(CreateCustomerActivity.this);
                        dialogNotify.setTitle(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
    private void step3OnCraeteUserSuccessfully(User customer) {
        MyApplication.AppSession.gI().setCustomer(customer);
        finish();
    }
    private void showDialogWaiting(){
        if(dialogWaiting == null){
            dialogWaiting = new DialogWaiting(CreateCustomerActivity.this);
        }
        dialogWaiting.show();
    }
    private void closeDialogWaiting(){
        if(dialogWaiting != null){
            dialogWaiting.cancel();
        }
    }
}