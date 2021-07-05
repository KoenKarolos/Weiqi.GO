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
import androidx.core.content.ContextCompat;

import static android.content.ContentValues.TAG;

public class GoBoardView extends View  {

    private int counter = 0;
    private Paint paint;
    int teal = ContextCompat.getColor(getContext(), R.color.teal_700);

    public GoBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();
        paint.setColor(Color.LTGRAY);

        float width = getWidth();
        float height = getHeight();
        float init_height = height/5;
        float cell_9 = width/9;

        for(int column=0;column<9;column++){
            for(int row=0;row<9;row++){
                if (column%2 == 1 && row%2 == 0 || column%2 == 0 && row%2 ==1){
                    paint.setColor(teal);
                }else{
                    paint.setColor(Color.LTGRAY);
                }
                canvas.drawRect(
                        cell_9*column,
                        init_height+cell_9*row,
                        cell_9*(column+1),
                        init_height+cell_9*(row+1),paint);

            }
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                paint.setColor(Color.BLACK);
                Log.d(TAG,"click " + counter);
                counter += 1;
        }
        return true;
    }
}
