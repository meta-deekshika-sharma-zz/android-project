package com.example.user15.intermediateapplication.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.user15.intermediateapplication.R;

public class CustomEditTextView extends android.support.v7.widget.AppCompatEditText {

    private String editText;
    private Drawable clearTextIcon;

    public CustomEditTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.CustomEditTextView, 0, 0);

        try {
            editText = attributes.getString(R.styleable.CustomEditTextView_edit_text);
            clearTextIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
            setCustomEditText(editText, clearTextIcon);

            clearText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearText() {
        setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (getCompoundDrawablesRelative()[2] != null) {
                float clearButtonStart;
                float clearButtonEnd;
                boolean isClearButtonClicked = false;

                if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                    clearButtonEnd = clearTextIcon
                            .getIntrinsicWidth() + getPaddingStart();

                    if (event.getX() < clearButtonEnd)
                        isClearButtonClicked = true;
                } else {
                    clearButtonStart = (getWidth() - getPaddingEnd()
                            - clearTextIcon.getIntrinsicWidth());
                    if (event.getX() > clearButtonStart)
                        isClearButtonClicked = true;
                }

                if (isClearButtonClicked) {
                    getText().clear();
                }
            }
            return false;
        }
    });
    }

    private void setCustomEditText(String editText, final Drawable clearTextIcon) {

        setText(editText);
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, clearTextIcon, null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
