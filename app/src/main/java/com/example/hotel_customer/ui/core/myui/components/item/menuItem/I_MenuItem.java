package com.example.hotel_customer.ui.core.myui.components.item.menuItem;

import android.graphics.drawable.Drawable;

public interface I_MenuItem {
    public void setText(String text);
    public void setIcon(Drawable icon);
    public void setShowIcon(boolean isShow);
    public void setShowText(boolean isShow);
    public void setIconColor(int color);
    public void setTextColor(int color);
}
