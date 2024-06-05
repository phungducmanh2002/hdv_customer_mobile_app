package com.example.hotel_customer;

import android.app.Application;
import android.content.Context;
import android.text.Html;

import com.example.hotel_customer.data.Account;
import com.example.hotel_customer.data.User;
import com.example.hotel_customer.ui.adapter.data.Photo;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MyApplication extends Application {
    @Data
    public static class AppSession{
        private static AppSession instance;
        public static AppSession gI(){
            if(instance == null){
                instance = new AppSession();
            }
            return  instance;
        }

        Account account;
        User customer;
        private AppSession(){

        }
        public void reset(){
            account = null;
            customer = null;
        }
    }
    public static class AppCache{
        private static AppCache instance;
        public static AppCache gI(){
            if(instance == null){
                instance = new AppCache();
            }
            return instance;
        }
        List<Photo> photoCache;
        int photoCacheMaxSize = 10;
        private AppCache(){
            photoCache = new LinkedList<>();
        }
        public void setPhotoCacheMaxSize(int size){
            this.photoCacheMaxSize = size;
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
    private static Context applicationContext;
    public static String env = "dev";
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this.getApplicationContext();
    }
    public static Context getContext(){
        return applicationContext;
    }
}
