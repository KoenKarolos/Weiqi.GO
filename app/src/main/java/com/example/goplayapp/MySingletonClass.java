package com.example.goplayapp;

public class MySingletonClass {

    private static MySingletonClass instance;

    public static MySingletonClass getInstance() {
        if (instance == null)
            instance = new MySingletonClass();
        return instance;
    }

    private MySingletonClass() {

    }

    private Character[][] boardState;

    public Character[][] getValue() {

        return boardState;
    }

    public void setValue(Character[][] value) {
        this.boardState = value;
    }
}