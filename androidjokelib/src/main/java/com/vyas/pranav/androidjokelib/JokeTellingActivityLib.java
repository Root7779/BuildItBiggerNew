package com.vyas.pranav.androidjokelib;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class JokeTellingActivityLib extends AppCompatActivity {
    public static final String JOKE_KEY = "Joke";
    public static final String ErrorDefaultString = "Error Occured\nHere are Details:\n";

    private TextView tvJoke;
    private TextView tvTitle;
    private Button btnLike;
    private Button btnDislike;
    private ImageView imagError;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_telling_lib);

        tvJoke = findViewById(R.id.lib_text_joke_main);
        tvTitle = findViewById(R.id.text_main_title);
        btnLike = findViewById(R.id.btnlike);
        btnDislike = findViewById(R.id.btnDislike);
        imagError = findViewById(R.id.image_error);
        fab = findViewById(R.id.fab_btn);

        final String joke = getIntent().getStringExtra(JOKE_KEY);
        tvJoke.setText(joke.replace(ErrorDefaultString,""));
        imagError.setVisibility(View.GONE);
        if(joke.contains(ErrorDefaultString)){
            checkError();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendJoke(joke);
            }
        });
    }
    //Mehod to show message and dissmiss activity
    public void like(View view){
        Toast.makeText(this, "Cool! Glad you liked It", Toast.LENGTH_LONG).show();
        finish();
    }
    //Method to dissmiss Activity and show sarcastic message
    public void dislike(View view){
        Toast.makeText(this, "OH! I don't care !! LOL!\n(pun Intended)", Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * Check if Error Has Occurred and It Occurred Hides Button and Text
     */
    private void checkError(){
        btnDislike.setVisibility(View.INVISIBLE);
        btnLike.setVisibility(View.INVISIBLE);
        tvTitle.setText("Oops ! Some Error Has Occured :");
        imagError.setVisibility(View.VISIBLE);
    }

    private void sendJoke(String joke){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.send_joke_message)+"\n"+joke);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"Choose from which to share"));
    }

}
