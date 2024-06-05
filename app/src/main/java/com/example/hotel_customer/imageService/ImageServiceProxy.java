package com.example.hotel_customer.imageService;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.hotel_customer.ui.adapter.data.Photo;
import com.example.hotel_customer.ui.core.events.I_OnEvent;

import java.util.LinkedList;
import java.util.List;

public class ImageServiceProxy implements I_ImageService {
    private static ImageServiceProxy instance;
    public static ImageServiceProxy gI(){
        if(instance == null){
            instance = new ImageServiceProxy();
        }
        return instance;
    }
    ImageService imageService;
    List<Photo> photoCache;
    int photoCacheMaxSize = 10;
    private ImageServiceProxy(){
        imageService = new ImageService();
        photoCache = new LinkedList<>();
    }
    @Override
    public void getImage(int id, I_OnEvent onEvent) {
        Photo photo = getPhotoCache(id);
        // chưa có trong cache
        if(photo == null){
            Log.d("CACHE_IMAGE_LOG", String.format("Image id: %d chưa có trong cache", id));
            imageService.getImage(id, new I_OnEvent() {
                @Override
                public void action(Object... object) {
                    Bitmap bitmap = (Bitmap) object[0];
                    Photo photo1 = new Photo();
                    photo1.setId(id);
                    photo1.setBitmap(bitmap);
                    cachePhoto(photo1);
                    onEvent.action(bitmap);
                }
            });
            return;
        }
        // đã có trong cache
        Log.d("CACHE_IMAGE_LOG", String.format("Image id: %d đã có trong cache", id));
        onEvent.action(photo.getBitmap());
    }
    public void cachePhoto(Photo photo){
        if(photoCache.size() > photoCacheMaxSize){
            for (int i = 0; i < photoCache.size() - 1; i++) {
                photoCache.set(i, photoCache.get(i+1));
            }
            photoCache.remove(photoCache.size()-1);
        }
        photoCache.add(photo);
    }
    public Photo getPhotoCache(int id){
        for (int i = 0; i < photoCache.size(); i++) {
            if(photoCache.get(i).getId() == id){
                Photo tmp = photoCache.get(i);
                // dịch trái từ vị trí i + 1
                for (int j = i; j < photoCache.size() - 1; j++) {
                    photoCache.set(j, photoCache.get(j+1));
                }
                // đưa photo vừa được lấy ra cuối
                photoCache.set(photoCache.size() - 1, tmp);
                return tmp;
            }
        }
        return null;
    }
}
