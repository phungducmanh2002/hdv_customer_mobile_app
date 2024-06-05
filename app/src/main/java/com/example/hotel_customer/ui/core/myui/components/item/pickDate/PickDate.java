package com.example.hotel_customer.ui.core.myui.components.item.pickDate;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.ui.core.myui.components.item.BaseLinearItem;
import com.example.hotel_customer.databinding.ItemDatePickerBinding;
import com.example.hotel_customer.helper.DateHelper;

import java.util.Calendar;
import java.util.Date;

public class PickDate  extends BaseLinearItem implements I_PickDate{

    ItemDatePickerBinding binding;
    Date selectDate;
    I_OnPickDate onBeforePickDate, onAfterPickDate;
    I_OnPickDate2 onPickDate2;

    public PickDate(Context context) {
        super(context);
    }

    public PickDate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PickDate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attributeSet) {
        binding = ItemDatePickerBinding.inflate(LayoutInflater.from(context), this, true);

        if(attributeSet != null){
            TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.PickDate);

            String label = a.getString(R.styleable.PickDate_pickDateLabel);
            String text = a.getString(R.styleable.PickDate_pickDateText);
            String warn = a.getString(R.styleable.PickDate_pickDateWarn);

            int icon = a.getResourceId(R.styleable.PickDate_pickDateIcon, -1);

            setPickDateLabel(label);
            setPickDateText(text);
            setPickDateWarn(warn);

            if(icon != -1){
                setPickDateIcon(getDrawableBitmap(getContext().getDrawable(icon)));
            }
        }

        setEvent();

    }

    @Override
    public void setEvent() {
        binding.mainArea.setOnClickListener(v -> {
            showPickDate();
        });
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void onClick(OnClickListener listener) {

    }

    @Override
    public void setPickDateLabel(String label) {
        if(label == null){
            binding.label.setVisibility(GONE);
            return;
        }

        binding.label.setText(label);
        binding.label.setVisibility(VISIBLE);
    }

    @Override
    public void setPickDateText(String text) {
        if(text == null){
            binding.text.setVisibility(GONE);
            return;
        }

        binding.text.setText(text);
        binding.text.setVisibility(VISIBLE);
    }

    @Override
    public void setPickDateWarn(String warning) {
        if(warning == null){
            binding.warn.setVisibility(GONE);
            return;
        }

        binding.warn.setText(warning);
        binding.warn.setVisibility(VISIBLE);
    }

    @Override
    public void setPickDateIcon(Bitmap bitmap) {
        binding.icon.setImageBitmap(bitmap);
    }

    @Override
    public void setPickDateDate(Date date) {
        this.selectDate = date;
        binding.text.setVisibility(VISIBLE);
       this.binding.text.setText(DateHelper.Date2String(date));
    }

    @Override
    public void setPickDateShowLabel(boolean isShow) {
        binding.label.setVisibility(isShow ? VISIBLE : GONE);
    }

    @Override
    public void setPickDateShowWarn(boolean isShow) {
        binding.warn.setVisibility(isShow ? VISIBLE : GONE);
    }

    @Override
    public Date getPickDateDate() {
        return this.selectDate;
    }

    @Override
    public void setPickDateBeforePick(I_OnPickDate onPickDate) {
        this.onBeforePickDate = onPickDate;
    }

    @Override
    public void setPickDateAfterPick(I_OnPickDate onPickDate) {
        this.onAfterPickDate = onPickDate;
    }

    @Override
    public void setPickDateOnPick(I_OnPickDate2 onPickDate2) {
        this.onPickDate2 = onPickDate2;
    }

    @Override
    public void showPickDate() {
        final Calendar calender = Calendar.getInstance();
        if(this.selectDate != null){
            calender.setTime(this.selectDate);
        }
        int mYear = calender.get(Calendar.YEAR);
        int mMonth = calender.get(Calendar.MONTH);
        int mDay = calender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    Date oldDate = selectDate;
                    Date newDate = DateHelper.CreateDate(year, month, dayOfMonth);

                    if(onBeforePickDate != null){
                        onBeforePickDate.execute(selectDate);
                    }
                    setPickDateDate(newDate);

                    if(onAfterPickDate != null){
                        onAfterPickDate.execute(newDate);
                    }

                    if(onPickDate2 != null){
                        onPickDate2.execute(oldDate, newDate);
                    }

                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public Bitmap getDrawableBitmap(Drawable drawable){
        return ((BitmapDrawable)drawable).getBitmap();
    }
}
