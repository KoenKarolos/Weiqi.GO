package com.example.goplayapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoBoardView extends View {

    Map<Integer, List<Integer>> moves = new HashMap<>(); // Dictionary {turn : [posX,posY]}

    float posX,posY;
    private int turn = 1;

    private Paint paint;
    int brown = ContextCompat.getColor(getContext(), R.color.brown);
    int gray = ContextCompat.getColor(getContext(), R.color.darkgray);
    private Bitmap whiteStoneBitmap = BitmapFactory.decodeResource(getContext().getResources() ,R.drawable.white_stone);
    private Bitmap blackStoneBitmap = BitmapFactory.decodeResource(getContext().getResources() ,R.drawable.black_stone);

    public GoBoardView(Context context, @Nullable AttributeSet attrs) { super(context,attrs);}

    public void init(){ //get's called onCreate and initiates a BoardInstance
        BoardStateClass.getInstance().initBoard();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int size = BoardStateClass.getInstance().getSize();
        paint=new Paint();
        drawBoardstate(canvas, size);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int size = BoardStateClass.getInstance().getSize();
        posX=event.getX();
        posY=event.getY();

        float cell_size=(getWidth()/size);
        int move_col = (int) Math.floor(posX/ cell_size);
        int move_row = (int) Math.floor(posY/cell_size);

        List<Integer> coordinates = new ArrayList<>();
        Character[][] boardState = BoardStateClass.getInstance().getBoard();

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if (boardState[move_row][move_col] == '*' ){
                    coordinates.add(move_col);
                    coordinates.add(move_row);
                    moves.put(turn,coordinates);
                    turn += 1;
                    invalidate(); //redraws canvas
                }
                break;

            case MotionEvent.ACTION_DOWN:
                //to do ghost move
                break;
        }
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

    private void drawGrid(int size,float cell, float offset,Canvas canvas){ //Drawing grid
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
        Character[][] boardState = BoardStateClass.getInstance().getBoard();
        for(Integer key : moves.keySet()) {
            try {
                int move_col = moves.get(key).get(0);
                int move_row = moves.get(key).get(1);

                RectF BoardPosition = new RectF(
                        (cell*move_col),
                        (cell*move_row),
                        (cell*(move_col+1)),
                        (cell*(move_row+1)));

                if(key%2 == 0){
                    canvas.drawBitmap(whiteStoneBitmap, null, BoardPosition, paint);
                    boardState[move_row][move_col] = 'w';
                }else{
                    canvas.drawBitmap(blackStoneBitmap, null, BoardPosition, paint);
                    boardState[move_row][move_col] = 'b';
                }
            }catch (Exception e) {
                System.out.println("Cursor out of bounds!");
            }
        }
        BoardStateClass.getInstance().setBoard(boardState);
        BoardStateClass.getInstance().printValue();
    }
}
