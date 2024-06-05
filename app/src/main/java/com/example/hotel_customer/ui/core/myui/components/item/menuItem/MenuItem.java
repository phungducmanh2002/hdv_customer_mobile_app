package com.example.hotel_customer.ui.core.myui.components.item.menuItem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.hotel_customer.ui.core.myui.components.item.BaseLinearItem;

public class MenuItem extends BaseLinearItem implements I_MenuItem {
    public MenuItem(Context context) {
        super(context);
    }
    public MenuItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MenuItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void init(Context context, AttributeSet attributeSet) {

    }
    @Override
    public void setEvent() {

    }
    @Override
    public String getText() {
        return null;
    }
    @Override
    public void onClick(OnClickListener listener) {

    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void setIcon(Drawable icon) {

    }

    @Override
    public void setShowIcon(boolean isShow) {

    }

    @Override
    public void setShowText(boolean isShow) {

    }

    @Override
    public void setIconColor(int color) {

    }

    @Override
    public void setTextColor(int color) {

    }
}
