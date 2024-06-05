package com.example.hotel_customer.ui.core.myui.components.item.input;

import android.graphics.drawable.Drawable;

public interface I_Input {
    public void setLine(int line);
    public void setHint(String hint);
    public void setText(String text);
    public void setLabel(String label);
    public void setShowLabel(boolean isShow);
    public boolean isShowLabel();
    public void setWarning(String warning);
    public boolean isShowWarning();
    public void setShowWarning(boolean isShow);
    // text options
    public void setTextLength(int maxLength);
    public void setTextAlign(int textAlignment);
    public void setInputType(int inputType);
    // colors
    public void setTextColor(int color);
    public void setBackgroundDrawable(Drawable drawable, Object object);
    public void setBackgroundColor(int color);
    public void setFocused();
}
