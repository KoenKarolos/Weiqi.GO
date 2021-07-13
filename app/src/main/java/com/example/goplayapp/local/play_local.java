package com.example.goplayapp.local;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goplayapp.BoardStateClass;
import com.example.goplayapp.GoBoardView;
import com.example.goplayapp.R;

public class play_local extends AppCompatActivity {
    private static final String TAG = "Weiqi:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoBoardView boardView = new GoBoardView(getBaseContext(),null);
        BoardStateClass.getInstance().setSize(9);
        setContentView(R.layout.activity_local);
        boardView.init();

        /***
        Button size9_btn = (Button) findViewById(R.id.size9);
        Button size13_btn = (Button) findViewById(R.id.size13);
        Button size19_btn = (Button) findViewById(R.id.size19);

        size9_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardStateClass.getInstance().setSize(9);
                BoardStateClass.getInstance().initBoard();
            }
        });

        size13_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardStateClass.getInstance().setSize(13);
                BoardStateClass.getInstance().initBoard();
            }
        });

        size19_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardStateClass.getInstance().setSize(19);
                BoardStateClass.getInstance().initBoard();
            }
        });
         ***/
    }
}

