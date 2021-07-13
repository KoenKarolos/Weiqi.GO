package com.example.goplayapp.online;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goplayapp.GoBoardView;
import com.example.goplayapp.R;


public class play_online extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        GoBoardView boardView = new GoBoardView(getBaseContext(),null);
        boardView.init(19);
    }
}