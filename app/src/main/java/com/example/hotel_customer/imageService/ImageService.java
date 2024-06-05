package com.example.hotel_customer.imageService;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.api.RetrofitClient;
import com.example.hotel_customer.ui.core.events.I_OnEvent;

public class ImageService implements I_ImageService{
    @Override
    public void getImage(int idImage, I_OnEvent onEvent) {
        String url = String.format("%s/res/images/%d", RetrofitClient.gI().getBaseUrl(), idImage);

        Glide.with(MyApplication.getContext()).load(url).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                onEvent.action(bitmap);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }
}
