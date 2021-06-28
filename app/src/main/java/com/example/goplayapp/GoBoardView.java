package com.example.goplayapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GoBoardView extends View  {

    private Paint paint;

    public GoBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();
        paint.setColor(Color.LTGRAY);

        canvas.drawRect(100f,200f,100 + 70, 200+70, paint);
        super.onDraw(canvas);
    }
}
