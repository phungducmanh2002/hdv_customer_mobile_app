package com.example.hotel_customer.ui.activity.hotel;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.MyApplication;
import com.example.hotel_customer.R;
import com.example.hotel_customer.databinding.ActivityHotelDetailBinding;
import com.example.hotel_customer.ui.adapter.PhotoAdapter;
import com.example.hotel_customer.ui.adapter.data.Photo;
import com.example.hotel_customer.ui.dialog.DialogChooseBookingInfo;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailActivity extends AppCompatActivity {
    ActivityHotelDetailBinding binding;
    DialogChooseBookingInfo chooseBookingInfoDialog;
    PhotoAdapter photoAdapter;
    int idHotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHotelDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idHotel = getIntent().getIntExtra("idHotel", -1);
        
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHotelInfo();
    }

    private void setEvent() {
        binding.btnBook.setOnClickListener(v -> {
            if(chooseBookingInfoDialog == null){
                chooseBookingInfoDialog = new DialogChooseBookingInfo(this);
            }
            chooseBookingInfoDialog.show();
        });
    }
    private void loadHotelInfo() {
//        if(idHotel == -1){
//            return;
//        }
        if(MyApplication.env == "dev"){
            photoAdapter = new PhotoAdapter(this, getListPhoto());

            binding.imageSliderViewPager.setAdapter(photoAdapter);
            binding.imageSliderCircleIndicator.setViewPager(binding.imageSliderViewPager);
            photoAdapter.registerDataSetObserver(binding.imageSliderCircleIndicator.getDataSetObserver());
        }
    }

    private List<Photo> getListPhoto() {
        List<Photo> photos = new ArrayList<>();

        Bitmap hotel1Bm = ((BitmapDrawable)getDrawable(R.drawable.img_hotel)).getBitmap();
        Bitmap hotel2Bm = ((BitmapDrawable)getDrawable(R.drawable.img_hotel_ba)).getBitmap();
        Bitmap hotel3Bm = ((BitmapDrawable)getDrawable(R.drawable.img_hotel_ba)).getBitmap();

        photos.add(new Photo(1, hotel1Bm));
        photos.add(new Photo(2,hotel2Bm));
        photos.add(new Photo(3,hotel3Bm));

        return photos;
    }
}