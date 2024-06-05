package com.example.hotel_customer.ui.adapter.customeViewAdapter;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.hotel_customer.data.Booking;

public class BookingItem extends com.example.hotel_customer.ui.core.myui.components.container.customeView.BookingItem {
    public BookingItem(Context context) {
        super(context);
    }
    public BookingItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public BookingItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setBookingData(Booking booking){
        setUI(booking);
        setEvent(booking);
    }
    private void setEvent(Booking booking){
        binding.btnCancle.setOnClickListener(v -> {
            if(Booking.canCancle(booking)){
                cancleBooking(booking);
            }
        });
        
        binding.btnView.setOnClickListener(v -> {
            gotoBookingDetail(booking);
        });
    }
    private void setUI(Booking booking) {
        this.setBookingID(booking.getId());
        this.setCheckinDate(booking.getCheckinDate());
        this.setCheckoutDate(booking.getCheckoutDate());
        this.setCheckoutDate(booking.getCheckoutDate());
        this.setStatus(booking.getBookingStatus());
        this.setTotalMoney(booking.getPaymentAmount());
    }
    private void gotoBookingDetail(Booking booking) {
    }
    private void cancleBooking(Booking booking) {
    }
}
