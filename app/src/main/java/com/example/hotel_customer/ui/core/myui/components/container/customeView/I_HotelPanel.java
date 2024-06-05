package com.example.hotel_customer.ui.core.myui.components.container.customeView;

import android.graphics.Bitmap;

public interface I_HotelPanel {
    public void setHotelImage(Bitmap bitmap);
    public void setHotelName(String name);
    public void setHotelDescription(String description);
    public void setHotelStart(float start);
    public void setHotelPayForNight(float money);
    public void setIdHotel(int id);
    public Integer getIdHotel();
}
