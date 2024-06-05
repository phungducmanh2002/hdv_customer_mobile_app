package com.example.hotel_customer.ui.activity.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.command.UserCommand;
import com.example.hotel_customer.data.Account;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.User;
import com.example.hotel_customer.databinding.ActivitySignupBinding;
import com.example.hotel_customer.helper.MapperHelper;
import com.example.hotel_customer.hook.retrofit.CustomeCallback;
import com.example.hotel_customer.ui.core.events.I_OnEvent;
import com.example.hotel_customer.ui.dialog.DialogNotify;
import com.example.hotel_customer.ui.dialog.DialogWaiting;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    Account updateAccount;
    int idUser = 0;
    I_OnEvent onNext;
    DialogWaiting dialogWaiting;
    UserCommand userCommand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            updateAccount = getIntent().getSerializableExtra("account", Account.class);
        }
        userCommand = new UserCommand();
        init();
        setEvent();
    }
    private void init() {
        if(updateAccount == null){
            initSingup();
        }
        else{
            initUpdateAccountInfo();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    private void setEvent() {
        binding.back.setOnClickListener(v -> {
            finish();
        });
        binding.btnNext.setOnClickListener(v -> {
            if(onNext != null){
                onNext.action();
            }
        });
    }
    private void initUpdateAccountInfo() {
        initUi();
        /**/
        onNext = new I_OnEvent() {
            @Override
            public void action(Object... object) {
//                updateAccountInfo(new Callback<ResponseData>() {
//                    @Override
//                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseData> call, Throwable t) {
//
//                    }
//                });
                Intent profileIntent = new Intent(SignupActivity.this, ProfileActivity.class);
                profileIntent.putExtra("reload", true);
                startActivity(profileIntent);
                finish();
            }
        };
    }
    private void initSingup() {
        initUi();
        onNext = new I_OnEvent() {
            @Override
            public void action(Object... object) {
                signupStep1CheckData();
            }
        };
    }
    private void signupStep1CheckData() {
        String email = binding.ipEmail.getText();
        String password = binding.ipPassowrd.getText();
        String password2 = binding.ipPassowrd2.getText();
        String firstName = binding.ipFirstName.getText();
        String lastName = binding.ipLastName.getText();
        int gender = binding.genderMale.isChecked() ? 0 : binding.genderFemale.isChecked() ? 1 : 2;
        Date birthDay = binding.pdBirthday.getPickDateDate();

        resetInputWarning();

        if(email.equals("")){
            binding.ipEmail.setFocused();
            binding.ipEmail.setWarning("vui lòng nhập email");
            return;
        }

        if(password.equals("")){
            binding.ipPassowrd.setFocused();
            binding.ipPassowrd.setWarning("vui lòng nhập password");
            return;
        }

        if(password2.equals("")){
            binding.ipPassowrd2.setFocused();
            binding.ipPassowrd2.setWarning("vui lòng nhập password");
            return;
        }

        if(!password.equals(password2)){
            binding.ipPassowrd2.setWarning("mật khẩu không khớp");
            return;
        }

        if(firstName.equals("")){
            binding.ipFirstName.setFocused();
            binding.ipFirstName.setWarning("vui lòng nhập họ");
            return;
        }

        if(lastName.equals("")){
            binding.ipLastName.setFocused();
            binding.ipLastName.setWarning("vui lòng nhập tên");
            return;
        }

        if(birthDay == null){
            binding.pdBirthday.showPickDate();
            return;
        }

        userCommand.getAccountByEmail(
                email,
                new CustomeCallback(
                        this,
                        200,
                        (call, response) -> {
                            // email da ton tai
                            binding.ipEmail.setFocused();
                            binding.ipEmail.setWarning("email đã được sử dụng");
                            closeDialogWaiting();
                        },
                        (call, response) -> {
                            if(response.body().getCode() == 404){
                                // email chua ton tai -> tao tai khoan
                                Account account1 = new Account();
                                account1.setEmail(email);
                                account1.setPassword(password);
                                account1.setFirstName(firstName);
                                account1.setLastName(lastName);
                                account1.setGender(gender);
                                account1.setBirthDay(birthDay);

                                signupStep2CallApiSignup(account1);
                            }
                        },
                        (call, t) -> {
                            closeDialogWaiting();
                        }));
    }
    private void signupStep2CallApiSignup(Account account) {
        showDialogWaiting();

        userCommand.signup(
                account,
                new CustomeCallback(
                        this,
                        201,
                        (call, response) -> {
                            signupStep3Successfully(account);
                        },
                        (call, response) -> {
                            closeDialogWaiting();
                        },
                        (call, t) -> {
                            closeDialogWaiting();
                        }));
    }
    private void signupStep3Successfully(Account accountCreated) {
        String email = accountCreated.getEmail();
        String password = accountCreated.getPassword();

        Intent signinIntent = new Intent(this, SigninActivity.class);
        signinIntent.putExtra("email", email);
        signinIntent.putExtra("password", password);
        startActivity(signinIntent);
        finish();
        closeDialogWaiting();
    }
    private void resetInputWarning() {
        binding.ipEmail.setShowWarning(false);
        binding.ipPassowrd.setShowWarning(false);
        binding.ipPassowrd2.setShowWarning(false);
        binding.ipFirstName.setShowWarning(false);
        binding.ipLastName.setShowWarning(false);
    }
    private void initUi() {
        if(updateAccount != null){
            binding.ipPassowrd.setVisibility(View.GONE);
            binding.ipPassowrd2.setVisibility(View.GONE);
            binding.ipEmail.setText(updateAccount.getEmail());
            if(updateAccount.getGender() == 0){
                binding.genderMale.setChecked(true);
            }
            else if(updateAccount.getGender() == 1){
                binding.genderFemale.setChecked(true);
            }
            else{
                binding.genderOrther.setChecked(true);
            }
            binding.pdBirthday.setPickDateDate(updateAccount.getBirthDay());
        }
        else{
            binding.ipPassowrd.setVisibility(View.VISIBLE);
            binding.ipPassowrd2.setVisibility(View.VISIBLE);
        }
    }
    private void updateAccountInfo(Callback<ResponseData> callback){}
    private void showDialogWaiting(){
        if(dialogWaiting == null){
            dialogWaiting = new DialogWaiting(SignupActivity.this);
        }
        dialogWaiting.show();
    }
    private void closeDialogWaiting(){
        if(dialogWaiting != null){
            dialogWaiting.cancel();
        }
    }
}