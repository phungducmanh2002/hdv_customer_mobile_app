package com.example.hotel_customer.helper;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MapperHelper {
    public static <T> T ConvertFromLinkedTreeMap(LinkedTreeMap<?, ?> linkedTreeMap, Class<T> cls){
        Gson gson = new Gson();
        String json = gson.toJson(linkedTreeMap);
        Type type = TypeToken.getParameterized(cls, cls).getType();
        return gson.fromJson(json,type);
    }
    public static <T> T ConvertFromObject(Object object, Class<T> cls){
        LinkedTreeMap<?, ?> linkedTreeMap = (LinkedTreeMap<?, ?>)object;
        Gson gson = new Gson();
        String json = gson.toJson(linkedTreeMap);
        Type type = TypeToken.getParameterized(cls, cls).getType();
        return gson.fromJson(json,type);
    }

    public static <T> T ConvertFromObject2(Object object, Class<T> cls){
        Gson gson = new Gson();
        String json = gson.toJson(object); // Convert object to JSON string
        return gson.fromJson(json, cls); // Convert JSON string to desired class
    }

}
