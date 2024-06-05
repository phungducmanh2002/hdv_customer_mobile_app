package com.example.hotel_customer.ui.activity.user;

import android.content.Intent;
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
import com.example.hotel_customer.data.AccessToken;
import com.example.hotel_customer.data.Account;
import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.data.Role;
import com.example.hotel_customer.data.User;
import com.example.hotel_customer.databinding.ActivitySigninBinding;
import com.example.hotel_customer.helper.MapperHelper;
import com.example.hotel_customer.hook.retrofit.CustomeCallback;
import com.example.hotel_customer.ui.activity.hotel.HomeActivity;
import com.example.hotel_customer.ui.dialog.DialogNotify;
import com.example.hotel_customer.ui.dialog.DialogWaiting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    String email, password;
    UserCommand userCommand;
    String accountToken;
    Account account;
    DialogWaiting dialogWaiting;
    DialogNotify dialogNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        userCommand = new UserCommand();

        initUi();
        setEvent();
    }

    private void initUi() {
        if (email != null && password != null) {
            binding.ipEmail.setText(email);
            binding.ipPassowrd.setText(password);
        }
    }

    private void setEvent() {
        binding.home.setOnClickListener(v -> {
            finish();
        });

        binding.btnSignin.setOnClickListener(v -> {
//            MyApplication.AppSession.gI().setCustomer(new User());
//            finish();
            step1CheckInput();
        });
    }

    private void step1CheckInput() {
        String email = binding.ipEmail.getText();
        String password = binding.ipPassowrd.getText();

        if (email.equals("")) {
            binding.ipEmail.setFocused();
            binding.ipEmail.setWarning("email không được để trống");
            return;
        } else {
            binding.ipEmail.setShowWarning(false);
        }

        if (password.equals("")) {
            binding.ipPassowrd.setFocused();
            binding.ipPassowrd.setWarning("password không được để trống");
            return;
        } else {
            binding.ipPassowrd.setShowWarning(false);
        }

        step2GenAccountToken(email, password);
    }

    private void step2GenAccountToken(String email, String password) {
        showDialogWaiting();
        userCommand.genAccountToken(email, password, new CustomeCallback(this, 200, (call, response) -> {
            AccessToken accessToken = MapperHelper.ConvertFromObject(response.body().getData(), AccessToken.class);
            SigninActivity.this.accountToken = accessToken.getAccessToken();
            step3GetAccountInfo(email);
        }, (call, response) -> {
            closeDialogWaiting();
        }, (call, t) -> {
            closeDialogWaiting();
        }));
    }

    private void step3GetAccountInfo(String email) {

        userCommand.getAccountByEmail(email, new CustomeCallback(this, 200, (call, response) -> {
            Account account = MapperHelper.ConvertFromObject(response.body().getData(), Account.class);
            SigninActivity.this.account = account;
            step4GetAccountUsers(account);
        }, (call, response) -> {
            closeDialogWaiting();
        }, (call, t) -> {
            closeDialogWaiting();
        }));
    }

    private void step4GetAccountUsers(Account account) {
        userCommand.getAccountUsers(
                account.getId(),
                new CustomeCallback(
                        this,
                        200,
                        (call, response) -> {
                            User[] users = MapperHelper.ConvertFromObject2(response.body().getData(), User[].class);
                            step5SigninFinish(users);
                        },
                        (call, response) -> {
                            closeDialogWaiting();
                        },
                        (call, t) -> {
                            closeDialogWaiting();
                        }));
    }

    private void step5SigninFinish(User[] users) {

        MyApplication.AppSession.gI().setAccount(this.account);

        for (User user : users) {
            Log.d("USER_IN_LOGIN", user.toString());
            if (user.getIdRole() == Role.CUSTOMER) {
                MyApplication.AppSession.gI().setCustomer(user);
                gotoHome();
                return;
            }
        }

        gotoCreateCustomer();

    }

    private void gotoCreateCustomer() {
        showDialogWaiting();
        Intent welcomIntent = new Intent(this, WellComeActivity.class);
        startActivity(welcomIntent);
        finish();
    }

    private void gotoHome() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }

    private void showDialogWaiting() {
        if (dialogWaiting == null) {
            dialogWaiting = new DialogWaiting(SigninActivity.this);
        }
        dialogWaiting.show();
    }

    private void closeDialogWaiting() {
        if (dialogWaiting != null) {
            dialogWaiting.cancel();
        }
    }

    private void showDialogNotify(String content) {
        if (dialogWaiting != null) {
            closeDialogWaiting();
        }
        if (dialogNotify == null) {
            dialogNotify = new DialogNotify(SigninActivity.this);
        }
        dialogNotify.setContent(content);
        dialogNotify.show();
    }
}