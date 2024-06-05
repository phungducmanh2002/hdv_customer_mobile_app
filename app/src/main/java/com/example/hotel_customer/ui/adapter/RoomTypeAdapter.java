package com.example.hotel_customer.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.data.RoomType;
import com.example.hotel_customer.databinding.AdapterRoomTypeItemBinding;
import com.example.hotel_customer.databinding.ImageSliderItemPhotoBinding;

import java.util.List;

public class RoomTypeAdapter extends BaseAdapter {
    AdapterRoomTypeItemBinding binding;
    List<RoomType> roomTypes;
    Context context;
    public RoomTypeAdapter(List<RoomType> roomTypes){
        this.roomTypes = roomTypes;
    }
    @Override
    public int getCount() {
        if(roomTypes == null)
        return 0;
        return roomTypes.size();
    }

    @Override
    public Object getItem(int position) {
        if(roomTypes == null)
        return null;
        return roomTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.adapter_room_type_item, null);
        }
       TextView tv =  convertView.findViewById(R.id.roomType);
        tv.setText(roomTypes.get(position).getRoomTypeName());
        return convertView;
    }
}
