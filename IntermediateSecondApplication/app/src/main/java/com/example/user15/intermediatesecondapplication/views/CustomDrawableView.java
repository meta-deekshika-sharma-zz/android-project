package com.example.user15.intermediatesecondapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

public class CustomDrawableView extends View {

    private ShapeDrawable shapeDrawable;

    public CustomDrawableView(Context context) {
        super(context);

        int x = 10, y = 10, width = 200, height = 100;
        shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(Color.BLUE);
        shapeDrawable.setBounds(x, y, x + width, y + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        shapeDrawable.draw(canvas);
    }
}
