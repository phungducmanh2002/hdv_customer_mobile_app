package com.example.hotel_customer.ui.core.myui.components.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.ui.core.myui.components.I_Component;

public abstract class BaseLinearItem extends LinearLayout implements I_Component {
    public BaseLinearItem(Context context) {
        super(context);
        init(context, null);
    }

    public BaseLinearItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseLinearItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    public BaseLinearItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }
}
