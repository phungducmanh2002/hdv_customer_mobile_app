package com.example.hotel_customer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.hotel_customer.databinding.DialogWaitBinding;

public class DialogWaiting extends Dialog {
    private static DialogWaiting instance;
    public static DialogWaiting GI(Context context){
        if(instance == null || context != null){
            instance = new DialogWaiting(context);
        }
        return  instance;
    }
    DialogWaitBinding binding;
    public DialogWaiting(@NonNull Context context) {
        super(context);
        init();
    }
    public void  init(){
        binding = DialogWaitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        if(window == null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttrs = window.getAttributes();
        windowAttrs.gravity = Gravity.CENTER;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCancelable(false);
    }

}
