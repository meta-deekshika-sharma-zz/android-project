package com.example.user15.intermediateapplication.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.user15.intermediateapplication.R;

public class CustomButtonView extends android.support.v7.widget.AppCompatButton {

    private int borderRadius;
    private int color;

    public CustomButtonView(Context context) {
        super(context);
        init(null);
    }

    public CustomButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attributeSet) {

        TypedArray attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomButtonView);

        try {
            borderRadius = attributes.getInt(R.styleable.CustomButtonView_border_radius, 2);
            color = attributes.getColor(R.styleable.CustomButtonView_color, Color.BLUE);
            setCustomButton(borderRadius, color);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            attributes.recycle();
        }
    }

    private void setCustomButton(int borderRadius, int color) {
        PaintDrawable paintDrawable = new PaintDrawable(color);
        paintDrawable.setCornerRadius(borderRadius);
        setBackgroundDrawable(paintDrawable);
    }
}
