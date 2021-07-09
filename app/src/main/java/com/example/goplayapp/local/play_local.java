package com.example.goplayapp.local;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goplayapp.GoBoardView;
import com.example.goplayapp.R;
import com.example.goplayapp.GameLogic;

public class play_local extends AppCompatActivity implements GameLogic {
    private static final String TAG = "Weiqi:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoBoardView boardView = new GoBoardView(getBaseContext(),null);
        boardView.setOnGameLogic(this);

        setContentView(R.layout.activity_local);
        boardView.init();
    }

    @Override
    public Character[][] initBoard(int size){
        Character[][] boardState = new Character[size][size];
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++) {
                boardState[row][col] = '*';
            }
        }
        return boardState;
    }

    @Override
    public void printBoard(Character[][] boardState){
        StringBuilder row = new StringBuilder();
        String board = "Boardstate \n";
        for (Character[] rows : boardState) {
            for (Character cell : rows) {
                row.append(cell).append("  ");
            }
            board += row + "\n";
            row = new StringBuilder();
        }
        Log.i(TAG, board);
    }

    @Override
    public Character[][] updateBoard(Character[][] boardState, int row, int col, char player){
            boardState[row][col] = player;
        return boardState;
    }

}

