package com.example.hotel_customer.ui.core.myui.components.container.customeView;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.data.Booking;
import com.example.hotel_customer.databinding.ContainerBookingItemBinding;
import com.example.hotel_customer.helper.DateHelper;
import com.example.hotel_customer.helper.NumberHelper;
import com.example.hotel_customer.ui.activity.hotel.HotelDetailActivity;
import com.example.hotel_customer.ui.core.myui.components.container.LinearContainer;

import java.util.Date;

public class BookingItem extends LinearContainer implements I_BookingItem {

    protected ContainerBookingItemBinding binding;
    Integer idHotel;

    public BookingItem(Context context) {
        super(context);
        init(context, null);
    }
    public BookingItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    public BookingItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    @Override
    public void init(Context context, AttributeSet attributeSet) {
        binding = ContainerBookingItemBinding.inflate(LayoutInflater.from(context), this, true);

        if(attributeSet != null){
            TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.BookingItem);
            float totalMoney = a.getFloat(R.styleable.BookingItem_BookingItemTotalMoney, -1);
            int status = a.getInt(R.styleable.BookingItem_BookingItemTotalMoney, -1);
            int id = a.getInt(R.styleable.BookingItem_BookingItemID, -1);

            String checkinDate = a.getString(R.styleable.BookingItem_BookingItemCheckinDate);
            String checkoutDate = a.getString(R.styleable.BookingItem_BookingItemCheckoutDate);


            if(id != -1){
                setBookingID(id);
            }
            setTotalMoney(totalMoney);
            setStatus(status);
            setCheckinDate(new Date());
            setCheckoutDate(new Date());
            a.recycle();
        }

        setEvent();
    }

    protected void setEvent() {
        binding.getRoot().setOnClickListener(v -> {
            if(idHotel != null){
                Intent hotelDetailIntent = new Intent(getContext(), HotelDetailActivity.class);
                hotelDetailIntent.putExtra("idHotel",idHotel);
                getContext().startActivity(hotelDetailIntent);
            }
        });
    }
    @Override
    public void setBookingID(int id) {
        binding.tvIdBooking.setText(Integer.toString(id));
    }
    @Override
    public void setCheckinDate(Date date) {
        binding.tvCheckin.setText(DateHelper.Date2String(date));
    }
    @Override
    public void setCheckoutDate(Date date) {
        binding.tvCheckout.setText(DateHelper.Date2String(date));
    }
    @Override
    public void setStatus(int status) {
        binding.tvStatus.setText(Integer.toString(status));
    }
    @Override
    public void setTotalMoney(float money) {
        binding.tvTotalMoney.setText(NumberHelper.GetMoney(money));
    }
}
