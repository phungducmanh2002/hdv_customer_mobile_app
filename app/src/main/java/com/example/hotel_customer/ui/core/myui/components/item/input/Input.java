package com.example.hotel_customer.ui.core.myui.components.item.input;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.ui.core.myui.components.item.BaseLinearItem;
import com.example.hotel_customer.databinding.ItemInputBinding;

public class Input extends BaseLinearItem implements I_Input {
    ItemInputBinding binding;

    public Input(Context context) {
        super(context);
    }
    public Input(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Input(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public Input(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        binding = ItemInputBinding.inflate(LayoutInflater.from(context), this, true);

        if(attributeSet != null){
            TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.Input);
            String text = a.getString(R.styleable.Input_inputText);
            String hint = a.getString(R.styleable.Input_inputHint);
            String warning = a.getString(R.styleable.Input_inputWarning);
            String label = a.getString(R.styleable.Input_inputLabel);
            String line = a.getString(R.styleable.Input_inputLine);

            String length = a.getString(R.styleable.Input_inputLength);
            int textAlign = a.getInt(R.styleable.Input_inputTextAlign, -1);
            int inputType = a.getInt(R.styleable.Input_inputType, -1);

            int background = a.getResourceId(R.styleable.Input_inputBackground, -1);
            int textColor = a.getResourceId(R.styleable.Input_inputTextColor, -1);

            if(line != null){
                int inputLine = Integer.parseInt(line);
                setLine(inputLine);
            }
            if(text != null){
                setText(text);
            }
            if(hint != null){
                setHint(hint);
            }
            if(warning != null){
                setWarning(warning);
            }else{
                setShowWarning(false);
            }
            if(length != null){
                Integer textLength = Integer.parseInt(length);
                setTextLength(textLength);
            }
            if(textAlign != -1){
                setTextAlign(textAlign);
            }
            if(inputType != -1){
                setInputType(inputType);
            }
            if(label != null){
                setLabel(label);
            }else{
                setShowLabel(false);
            }
            if(background != -1){
                setBackgroundDrawable(getContext().getDrawable(background), null);
            }
            if(textColor != -1){
                setTextColor(getContext().getColor(textColor));
            }
            a.recycle();
        }

        setEvent();
    }
    @Override
    public void setEvent() {
    }
    @Override
    public String getText() {
        return binding.input.getText().toString();
    }
    @Override
    public void onClick(OnClickListener listener) {

    }

    @Override
    public void setLine(int line) {
        binding.input.setMaxLines(line);
        if(line == 1)
        binding.input.setSingleLine();
    }

    @Override
    public void setHint(String hint) {
        binding.input.setHint(hint);
    }

    @Override
    public void setText(String text) {
        binding.input.setText(text);
    }

    @Override
    public void setLabel(String label) {
        binding.label.setText(label);
    }

    @Override
    public void setShowLabel(boolean isShow) {
        binding.label.setVisibility(isShow ? VISIBLE : GONE);
    }

    @Override
    public boolean isShowLabel() {
        return binding.label.getVisibility() == VISIBLE;
    }

    @Override
    public void setWarning(String warning) {
        binding.warn.setText(warning);
        setShowWarning(true);
    }

    @Override
    public boolean isShowWarning() {
        return binding.warn.getVisibility() == VISIBLE;
    }

    @Override
    public void setShowWarning(boolean isShow) {
        binding.warn.setVisibility(isShow ? VISIBLE : GONE);
    }

    @Override
    public void setTextLength(int maxLength) {
        InputFilter[] inputFilter = new InputFilter[1];
        inputFilter[0] = new InputFilter.LengthFilter(maxLength);
        binding.input.setFilters(inputFilter);
    }

    @Override
    public void setTextAlign(int textAlignment) {
        binding.input.setTextAlignment(textAlignment);
    }

    @Override
    public void setInputType(int inputType) {
        binding.input.setInputType(inputType);
    }

    @Override
    public void setTextColor(int color) {
        binding.input.setTextColor(color);
    }

    @Override
    public void setBackgroundDrawable(Drawable drawable, Object object) {
        binding.input.setBackgroundDrawable(drawable);
    }

    @Override
    public void setFocused() {
        binding.input.requestFocus();
    }
}
