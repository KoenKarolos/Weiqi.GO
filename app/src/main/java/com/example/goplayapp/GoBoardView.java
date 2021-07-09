package com.example.goplayapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.goplayapp.ui.GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class GoBoardView extends View {

    Map<Integer, List<Integer>> moves = new HashMap<>(); // Dictionary {turn : [posX,posY]}

    float posX,posY;
    private int turn = 1;
    int size = 9;

    private Paint paint;
    int brown = ContextCompat.getColor(getContext(), R.color.brown);
    int gray = ContextCompat.getColor(getContext(), R.color.darkgray);
    private Bitmap whiteStoneBitmap = BitmapFactory.decodeResource(getContext().getResources() ,R.drawable.white_stone);
    private Bitmap blackStoneBitmap = BitmapFactory.decodeResource(getContext().getResources() ,R.drawable.black_stone);

    public GoBoardView(Context context, @Nullable AttributeSet attrs) { super(context,attrs);}

    private GameLogic gameLogic; //setter of interface GameLogic
    public void setOnGameLogic(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    public void init(){
        String[][] boardState = gameLogic.initBoard(size);
        gameLogic.printBoard(boardState);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint=new Paint();
        drawBoardstate(canvas, size);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        posX=event.getX();
        posY=event.getY();
        List<Integer> k = new ArrayList<>();

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
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

    private void drawBoardstate(Canvas canvas, int size){
        float width = getWidth();
        float cell = width/size;
        float offset = cell/2;

        drawBoard(size,cell,canvas);
        drawGrid(size,cell,offset, canvas);
        drawMoves(cell, canvas);
    }

    private void drawBoard(int size,float cell, Canvas canvas){ //Draws Board
        for(int column=0;column<size;column++){ // draws size*size board (standard sizes are 9x9, 13x13, 19x19)
            for(int row=0;row<size;row++){
                paint.setColor(brown);
                canvas.drawRect(
                        cell*column,
                        cell*row,
                        cell*(column+1),
                        cell*(row+1),
                        paint);
            }
        }
    }

    private void drawGrid(int size,float cell, float offset,Canvas canvas){ //Drawing actual grid
        paint.setColor(gray);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        for(int column=0;column<size-1;column++){ // draws size-1*size-1 grid (standard sizes are 9x9, 13x13, 19x19)
            for(int row=0;row<size-1;row++){
                canvas.drawRect(
                        cell*column+offset,
                        cell*row+offset,
                        cell*(column+1)+offset,
                        cell*(row+1)+offset,
                        paint);
            }
        }

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        switch(size){
            default:
                break;
            case 19:
                for(int i=1; i<6;i=i+2){
                    canvas.drawCircle(3*i*cell+offset,3*cell+offset,10,paint);
                    canvas.drawCircle(3*i*cell+offset,9*cell+offset,10,paint);
                    canvas.drawCircle(3*i*cell+offset,15*cell+offset,10,paint);
                }
                break;
            case 13:
                for(int i=1; i<4;i++){
                    if(i==2){
                        canvas.drawCircle(3*i*cell+offset,6*cell+offset,10,paint);
                    }else{
                        canvas.drawCircle(3*i*cell+offset,3*cell+offset,10,paint);
                        canvas.drawCircle(3*i*cell+offset,9*cell+offset,10,paint);
                    }
                }
                break;
            case 9:
                for(int i=1; i<4;i++){
                    if(i==2){
                        canvas.drawCircle(2*i*cell+offset,4*cell+offset,10,paint);
                    }else{
                        canvas.drawCircle(2*i*cell+offset,2*cell+offset,10,paint);
                        canvas.drawCircle(2*i*cell+offset,6*cell+offset,10,paint);
                    }
                }
                break;
        }
    }

    private void drawMoves(float cell, Canvas canvas){ //draws stones/moves
        //stones snap onto nearest grid intersection
        for(Integer key : moves.keySet()) {
            posX = moves.get(key).get(0);
            posY = moves.get(key).get(1);
            float move_col = (float)Math.floor(posX/cell);
            float move_row = (float)Math.floor(posY/cell);

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
