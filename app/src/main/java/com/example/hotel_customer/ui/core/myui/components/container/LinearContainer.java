package com.example.hotel_customer.ui.core.myui.components.container;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.ui.core.myui.components.I_Component;

import java.util.ArrayList;
import java.util.List;

public class LinearContainer extends LinearLayout implements I_Container{
    List<I_Component> childs;

    public LinearContainer(Context context) {
        super(context);
        init(context, null);
    }

    public LinearContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LinearContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    public LinearContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet){
        childs = new ArrayList<>();
    }
    @Override
    public void addChild(I_Component component){
        this.childs.add(component);
    }
}
