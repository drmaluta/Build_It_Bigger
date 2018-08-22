package com.maluta.jokeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_KEY_EXTRA = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        setJokeTV();
    }

    private void setJokeTV() {
        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_KEY_EXTRA);
        if (joke != null) {
            TextView textView = findViewById(R.id.tv_joke);
            textView.setText(joke);
        }
    }
}
