package com.metacrazie.jokedisplaylib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_joke);
        Intent mIntent=getIntent();
        String joke=mIntent.getStringExtra("JOKE");
        TextView textView=(TextView) findViewById(R.id.jokeText);
        textView.setText(joke);
    }
}
