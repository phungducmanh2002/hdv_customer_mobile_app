package com.example.hotel_customer.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.hotel_customer.databinding.ImageSliderItemPhotoBinding;
import com.example.hotel_customer.ui.adapter.data.Photo;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    ImageSliderItemPhotoBinding binding;
    private Context context;
    private List<Photo> photos;

    public PhotoAdapter(Context context, List<Photo> photos){
        this.context = context;
        this.photos = photos;
    }
    @Override
    public int getCount() {
        if(photos != null){
            return photos.size();
        }
        return 0;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        binding = ImageSliderItemPhotoBinding.inflate(LayoutInflater.from(context), container, false);

        Photo photo = photos.get(position);
        if(photo != null){
            binding.imgPhoto.setImageBitmap(photo.getBitmap());
        }

        container.addView(binding.getRoot());
        return binding.getRoot();
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
