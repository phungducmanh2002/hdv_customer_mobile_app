package com.example.hotel_customer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.R;
import com.example.hotel_customer.data.Account;
import com.example.hotel_customer.data.RoomType;
import com.example.hotel_customer.databinding.DialogChooseBookingInfoBinding;
import com.example.hotel_customer.ui.adapter.RoomTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class DialogChooseBookingInfo extends Dialog {
    private static DialogChooseBookingInfo instance;
    public static DialogChooseBookingInfo GI(Context context){
        if(instance == null || context != null){
            instance = new DialogChooseBookingInfo(context);
        }
        return  instance;
    }
    DialogChooseBookingInfoBinding binding;
    public DialogChooseBookingInfo(@NonNull Context context) {
        super(context);
        init();
    }
    public void  init(){
        binding = DialogChooseBookingInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        if(window == null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttrs = window.getAttributes();
        windowAttrs.gravity = Gravity.CENTER;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCancelable(false);
        initTemplateData();
        initUi();
        setEvent();
    }
    private void initTemplateData(){
        List<RoomType> roomTypes = new ArrayList<>();
        roomTypes.add(RoomType.builder().roomTypeName("VIP").build());
        roomTypes.add(RoomType.builder().roomTypeName("NORMAL").build());

        RoomTypeAdapter adapter = new RoomTypeAdapter(roomTypes);
        binding.spRoomType.setAdapter(adapter);
    }
    private void initUi() {
        Account account = MyApplication.AppSession.gI().getAccount();
        if(account != null){
            binding.ipPersonName.setText(String.format("%s %s", account.getFirstName(), account.getLastName()));
            binding.ipEmail.setText(account.getEmail());
        }
    }
    private void setEvent() {
        binding.cancle.setOnClickListener(v -> {cancel();});

        binding.peopleNumMinus.setOnClickListener(v -> {
            plusPeople(-1);
        });

        binding.peopleNumPlus.setOnClickListener(v -> {
            plusPeople(1);
        });
    }
    private void plusPeople(int i) {
        Integer people = Integer.parseInt(binding.tvPeopleNum.getText().toString());
        if(people != null){
            Integer newPeopleNum = people += i;
            if(newPeopleNum > 0){
                binding.tvPeopleNum.setText(newPeopleNum.toString());
            }
        }
    }
}
