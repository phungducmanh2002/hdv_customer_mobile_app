package com.example.hotel_customer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.hotel_customer.data.RoomType;
import com.example.hotel_customer.databinding.DialogNotifyBinding;
import com.example.hotel_customer.ui.adapter.RoomTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class DialogNotify extends Dialog {
    private static DialogNotify instance;
    public static DialogNotify GI(Context context){
        if(instance == null || context != null){
            instance = new DialogNotify(context);
        }
        return  instance;
    }
    DialogNotifyBinding binding;
    public DialogNotify(@NonNull Context context) {
        super(context);
        init();
    }
    public void  init(){
        binding = DialogNotifyBinding.inflate(getLayoutInflater());
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
    }

    public DialogNotify setTitle(String title){
        binding.title.setText(title);
        return this;
    }
    public DialogNotify setContent(String content){
        binding.content.setText(content);
        return this;
    }
}
