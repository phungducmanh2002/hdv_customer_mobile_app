package com.example.hotel_customer.ui.core.myui.components.container.customeView;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.ui.activity.hotel.HotelDetailActivity;
import com.example.hotel_customer.ui.core.myui.components.container.LinearContainer;
import com.example.hotel_customer.databinding.ContainerHotelPanelBinding;
import com.example.hotel_customer.helper.NumberHelper;

public class HotelPanel extends LinearContainer implements I_HotelPanel {

    ContainerHotelPanelBinding binding;
    Integer idHotel = 1;
    public HotelPanel(Context context) {
        super(context);
    }
    public HotelPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public HotelPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init(Context context, AttributeSet attributeSet){
        binding = ContainerHotelPanelBinding.inflate(LayoutInflater.from(context), this, true);

        if(attributeSet != null){
            TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.HotelPanel);
            String hotelName = a.getString(R.styleable.HotelPanel_hotelPanelHotelName);
            String hotelDescription = a.getString(R.styleable.HotelPanel_hotelPanelHotelDescription);

            int hotelImage = a.getResourceId(R.styleable.HotelPanel_hotelPanelImage, -1);

            float hotelStart = a.getFloat(R.styleable.HotelPanel_hotelPanelHotelStart, -1);
            float payForNight = a.getFloat(R.styleable.HotelPanel_hotelPanelHotelPayForNight, -1);

            if(hotelName != null){
                setHotelName(hotelName);
            }
            if(hotelDescription != null){
                setHotelDescription(hotelDescription);
            }
            if(hotelImage != -1){
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), hotelImage);
                setHotelImage(bitmap);
            }
            if(hotelStart != -1){
                setHotelStart(hotelStart);
            }
            if(payForNight != -1){
                setHotelPayForNight(payForNight);
            }

            a.recycle();
        }

        setEvent();
    }
    private void setEvent() {
        binding.getRoot().setOnClickListener(v -> {
            if(idHotel != null){
                Intent hotelDetailIntent = new Intent(getContext(), HotelDetailActivity.class);
                hotelDetailIntent.putExtra("idHotel",idHotel);
                getContext().startActivity(hotelDetailIntent);
            }
        });
    }
    @Override
    public void setHotelImage(Bitmap bitmap) {
        binding.image.setImageBitmap(bitmap);
    }
    @Override
    public void setHotelName(String name) {
        binding.name.setText(name);
    }
    @Override
    public void setHotelDescription(String description) {
        binding.description.setText(description);
    }
    @Override
    public void setHotelStart(float start) {
        binding.start.setText(Float.toString(start));
    }
    @Override
    public void setHotelPayForNight(float money) {
        binding.payForNight.setText(NumberHelper.GetMoney(money));
    }
    @Override
    public void setIdHotel(int id) {
        this.idHotel = id;
    }

    @Override
    public Integer getIdHotel() {
        return this.idHotel;
    }
}
