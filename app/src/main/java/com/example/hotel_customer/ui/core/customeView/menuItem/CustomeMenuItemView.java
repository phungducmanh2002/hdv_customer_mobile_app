package com.example.hotel_customer.ui.core.customeView.menuItem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.ui.core.customeView.I_CustomeView;

public class CustomeMenuItemView extends View implements I_CustomeView {

    private int notificationCount = 0;
    private Paint paint;
    private Rect rect;

    public CustomeMenuItemView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomeMenuItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomeMenuItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CustomeMenuItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }
    public void init(Context context, AttributeSet attributeSet){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(40);
        rect = new Rect();

        setNotificationCount(1);
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        Drawable icon = getResources().getDrawable(R.drawable.ic_bell, null);
        icon.setBounds(0,0, getWidth(), getHeight());
        icon.draw(canvas);

        if(notificationCount > 0){
            paint.setColor(Color.RED);
            int radius = 30;
            int cx = getWidth() - radius;
            int cy = radius;
            canvas.drawCircle(cx, cy,radius,paint);

            paint.setColor(Color.WHITE);
            String countText = String.valueOf(notificationCount);
            float textWidth = paint.measureText(countText);
            float textHeight = rect.height();
            canvas.drawText(countText, cx - textWidth / 2, cy + textHeight / 2 + 10, paint);
        }
    }
    public void setNotificationCount(int count) {
        this.notificationCount = count;
        invalidate(); // Gọi để vẽ lại view
    }
}
