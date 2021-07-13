package com.example.goplayapp.local;


import android.os.Bundle;
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

    }
}

