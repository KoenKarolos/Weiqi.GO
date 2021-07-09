package com.example.goplayapp;

public interface GameLogic {
    Character[][] initBoard(int size);
    void printBoard(Character[][] boardState);
    Character[][] updateBoard(Character[][] boardState, int row, int col, char player);
}
