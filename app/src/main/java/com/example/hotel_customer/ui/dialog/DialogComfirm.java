package com.example.hotel_customer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.hotel_customer.databinding.DialogComfirmBinding;
import com.example.hotel_customer.databinding.DialogNotifyBinding;
import com.example.hotel_customer.ui.core.events.I_OnEvent;

public class DialogComfirm extends Dialog {
    private static DialogComfirm instance;
    public static DialogComfirm GI(Context context){
        if(instance == null || context != null){
            instance = new DialogComfirm(context);
        }
        return  instance;
    }
    DialogComfirmBinding binding;
    I_OnEvent onConfirm;
    public DialogComfirm(@NonNull Context context) {
        super(context);
        init();
    }
    public void  init(){
        binding = DialogComfirmBinding.inflate(getLayoutInflater());
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
        binding.confirm.setOnClickListener(v -> {
            if(onConfirm != null){
                onConfirm.action();
            }
        });

        binding.cancle.setOnClickListener(v -> {cancel();});

    }

    public DialogComfirm setTitle(String title){
        binding.title.setText(title);
        return this;
    }
    public DialogComfirm setContent(String content){
        binding.content.setText(content);
        return this;
    }
    public void setOnConfirm(I_OnEvent onConfirm){
        this.onConfirm =   onConfirm;
    }
}
