package com.example.goplayapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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

    Map<Integer, List<Integer>> moves = new HashMap<>(); // Dictionary {turn : [posX,posY]}

    float posX,posY;
    private int turn = 1;
    int teal = ContextCompat.getColor(getContext(), R.color.teal_700);
    private Paint paint;
    private Bitmap whiteStoneBitmap = BitmapFactory.decodeResource(getContext().getResources() ,R.drawable.white_stone);
    private Bitmap blackStoneBitmap = BitmapFactory.decodeResource(getContext().getResources() ,R.drawable.black_stone);

    public GoBoardView(Context context, @Nullable AttributeSet attrs) { super(context,attrs);}

    @Override
    protected void onDraw(Canvas canvas) {
        paint=new Paint();
        drawBoard(canvas, 9);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        posX=event.getX();
        posY=event.getY();
        List<Integer> k = new ArrayList<>();

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(turn%2 == 0){
                    Log.d(TAG,"Turn Nr. " + turn + ": WHITE moved to x= " + posX +" y= "+ posY);
                }else{
                    Log.d(TAG,"Turn Nr. " + turn + ": BLACK moved to x= " + posX +" y= "+ posY);
                }
                k.add((int) posX);
                k.add((int) posY);
                moves.put(turn,k);
                Log.d(TAG, String.valueOf(moves.keySet()));
                turn += 1;
                break;

            case MotionEvent.ACTION_DOWN:
                //to do ghost move
                break;
        }
        invalidate(); //redraws canvas

        return true;
    }

    private void drawBoard(Canvas canvas, int size){
        float width = getWidth();
        float height = getHeight();
        float init_height = 0;
        float cell = width/size;

        //Drawing Grid
        for(int column=0;column<size;column++){ // draws 9x9 board
            for(int row=0;row<size;row++){
                if (column%2 == 1 && row%2 == 0 || column%2 == 0 && row%2 ==1){
                    paint.setColor(teal);
                }else{
                    paint.setColor(Color.LTGRAY);
                }
                canvas.drawRect(
                        cell*column,
                        init_height+cell*row,
                        cell*(column+1),
                        init_height+cell*(row+1),paint);
            }
        }

        //Drawing actual Board
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        for(int column=0;column<size-1;column++){ // draws 9x9 board
            for(int row=0;row<size-1;row++){
                canvas.drawRect(
                        cell*column+cell/2,
                        init_height+cell*row+cell/2,
                        cell*(column+1)+cell/2,
                        init_height+cell*(row+1)+cell/2,paint);
            }
        }

        //drawing stones/moves
        //stones are slightly offset on the y axis, not perfectly in the middle of grid cell
        for(Integer key : moves.keySet()) {
            posX = moves.get(key).get(0);
            posY = moves.get(key).get(1);
            float move_col = (float)Math.floor(posX/cell); //posX/cell = column
            float move_row = (float)Math.floor(posY/cell); //posY/cell = row

            RectF BoardPosition = new RectF(
                    (cell*move_col),
                    (cell*move_row),
                    (cell*(move_col+1)),
                    (cell*(move_row+1)));
            if(key%2 == 0){
                canvas.drawBitmap(whiteStoneBitmap, null, BoardPosition, paint);
            }else{
                canvas.drawBitmap(blackStoneBitmap, null, BoardPosition, paint);
            }
        }
    }
}
