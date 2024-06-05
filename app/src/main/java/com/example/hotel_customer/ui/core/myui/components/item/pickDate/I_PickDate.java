package com.example.hotel_customer.ui.core.myui.components.item.pickDate;

import android.graphics.Bitmap;

import java.util.Date;

public interface I_PickDate {
    public void setPickDateLabel(String label);
    public void setPickDateText(String text);
    public void setPickDateWarn(String warning);
    public void setPickDateIcon(Bitmap bitmap);
    public void setPickDateDate(Date date);
    public void setPickDateShowLabel(boolean isShow);
    public void setPickDateShowWarn(boolean isShow);
    public Date getPickDateDate();
    public void setPickDateBeforePick(I_OnPickDate onPickDate);
    public void setPickDateAfterPick(I_OnPickDate onPickDate);
    public void setPickDateOnPick(I_OnPickDate2 onPickDate2);
    public void showPickDate();
}
