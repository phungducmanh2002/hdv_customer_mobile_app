package com.example.hotel_customer.ui.core.myui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface I_Component {
    public void init(Context context, AttributeSet attributeSet);
    public void setEvent();
    public String getText();
    public void onClick(View.OnClickListener listener);
}
