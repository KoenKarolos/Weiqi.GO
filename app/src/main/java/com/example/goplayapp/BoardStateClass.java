package com.example.goplayapp;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class BoardStateClass {

    private static BoardStateClass instance;

    public static BoardStateClass getInstance() {
        if (instance == null)
            instance = new BoardStateClass();
        return instance;
    }

    private BoardStateClass() {

    }

    private Character[][] boardState;
    private Integer size = 19;

    public void setBoard(Character[][] value) {
        this.boardState = value;
    }
    public Character[][] getBoard() { return boardState; }

    public void setSize(Integer size){this.size = size;}
    public Integer getSize(){ return size;}

    public void initBoard(){ //creates 2d matrix and set's boardstate value
        Character[][] boardState = new Character[size][size];
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++) {
                boardState[row][col] = '*';
            }
        }
        BoardStateClass.getInstance().setBoard(boardState);
    }

    public void printValue(){
        Character[][] boardState = BoardStateClass.getInstance().getBoard();
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


}