package com.vyas.pranav.androidjokelib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeTellingActivityLib extends AppCompatActivity {
    public static final String JOKE_KEY = "Joke";
    TextView tvJoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_telling_lib);

        tvJoke = findViewById(R.id.lib_text_joke_main);

        String joke = getIntent().getStringExtra(JOKE_KEY);
        tvJoke.setText(joke);
    }
}
