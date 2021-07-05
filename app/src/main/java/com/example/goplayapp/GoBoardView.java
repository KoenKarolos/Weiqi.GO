package com.example.goplayapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class GoBoardView extends View  {

    Map<Integer, List<Integer>> moves = new HashMap<>();

    float x,y;
    boolean touched = false;
    private int turn = 1;
    int teal = ContextCompat.getColor(getContext(), R.color.teal_700);
    private Paint paint;
    private Bitmap whiteStoneBitmap = BitmapFactory.decodeResource(getContext().getResources() ,R.drawable.white_stone);


    public GoBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint=new Paint();
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

        for(Integer key : moves.keySet()) {
            x = moves.get(key).get(0);
            y = moves.get(key).get(1);
            Rect BoardPosition = new Rect(
                    (int) (x - cell_9 / 2),
                    (int) (y - cell_9 / 2),
                    (int) (x + cell_9 / 2),
                    (int) (y + cell_9 / 2));
            canvas.drawBitmap(whiteStoneBitmap, null, BoardPosition, paint);
        }

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=event.getX();
        y=event.getY();
        List<Integer> k = new ArrayList<>();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(turn%2 == 0){
                    Log.d(TAG,"Turn Nr. " + turn + ": WHITE moved to x= " + x +" y= "+ y);
                }else{
                    Log.d(TAG,"Turn Nr. " + turn + ": BLACK moved to x= " + x +" y= "+ y);
                }
                break;
        }

        k.add((int) x);
        k.add((int) y);
        moves.put(turn,k);
        Log.d(TAG, String.valueOf(moves.keySet()));
        turn += 1;

        invalidate(); //redraws canvas

        return true;
    }

}
