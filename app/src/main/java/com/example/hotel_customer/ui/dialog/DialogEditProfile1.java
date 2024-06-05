package com.example.hotel_customer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.hotel_customer.databinding.DialogListOptionEditProfileBinding;
import com.example.hotel_customer.databinding.DialogWaitBinding;
import com.example.hotel_customer.ui.core.events.I_OnEvent;

import lombok.Setter;

public class DialogEditProfile1 extends Dialog {
    private static DialogEditProfile1 instance;
    public static DialogEditProfile1 GI(Context context){
        if(instance == null || context != null){
            instance = new DialogEditProfile1(context);
        }
        return  instance;
    }
    DialogListOptionEditProfileBinding binding;
    @Setter
    I_OnEvent onUpdateAvatar, onUpdateUsername, onUpdateAccount;
    public DialogEditProfile1(@NonNull Context context) {
        super(context);
        init();
    }
    public void  init(){
        binding = DialogListOptionEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        if(window == null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttrs = window.getAttributes();
        windowAttrs.gravity = Gravity.CENTER;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCancelable(false);

        setEvent();
    }
    private void setEvent() {
        binding.cancle.setOnClickListener(v -> {cancel();});

        binding.updateAvatar.setOnClickListener(v -> {
            if(onUpdateAvatar != null){
                onUpdateAvatar.action();
            }
        });

        binding.updateUsername.setOnClickListener(v -> {
            if(onUpdateUsername != null){
                onUpdateUsername.action();
            }
        });

        binding.updateAccount.setOnClickListener(v -> {
            if(onUpdateAccount != null){
                onUpdateAccount.action();
            }
        });
    }
}
