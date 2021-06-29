package com.example.goplayapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

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
    public int i = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"click " + i);
                i += 1;
        }
        return true;
    }
}
