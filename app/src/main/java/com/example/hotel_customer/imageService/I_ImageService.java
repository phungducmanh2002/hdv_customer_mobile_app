package com.example.hotel_customer.imageService;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.hotel_customer.ui.core.events.I_OnEvent;

public interface I_ImageService {
    public void getImage(int id, I_OnEvent onEvent);
}
