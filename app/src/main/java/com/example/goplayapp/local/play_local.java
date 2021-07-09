package com.example.goplayapp.local;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goplayapp.GoBoardView;
import com.example.goplayapp.R;
import com.example.goplayapp.ui.GameLogic;

public class play_local extends AppCompatActivity implements GameLogic {
    private static final String TAG = "Weiqi:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoBoardView boardView = new GoBoardView(getBaseContext(),null);
        boardView.setOnGameLogic(this);
        boardView.init();
        setContentView(R.layout.activity_local);
    }

    @Override
    public String[][] initBoard(int size){
        String[][] boardState = new String[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++) {
                boardState[i][j] = "*";
            }
        }
        return boardState;
    }

    @Override
    public void printBoard(String[][] boardState){
        StringBuilder row = new StringBuilder();
        String board = "Boardstate \n";
        for (String[] rows : boardState) {
            for (String column : rows) {
                row.append("*").append(" ");
            }
            board += row + "\n";
            row = new StringBuilder();
        }
        Log.i(TAG, board);
    }

}

