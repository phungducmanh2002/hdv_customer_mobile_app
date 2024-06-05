package com.example.hotel_customer.ui.core.myui.components.container.customeView;

import android.graphics.Bitmap;

import java.util.Date;

public interface I_BookingItem {
    public void setBookingID(int id);
    public void setCheckinDate(Date date);
    public void setCheckoutDate(Date date);
    public void setStatus(int status);
    public void setTotalMoney(float money);
}
