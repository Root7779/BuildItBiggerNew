package com.vyas.pranav.androidjokelib;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
    private BottomNavigationView bottomNavigation;
    private CoordinatorLayout containerBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean dark = getIntent().getBooleanExtra("KEY_DARK_ENABLED",false);
        if(dark){
            setTheme(R.style.Theme_AppCompat);
            //Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
        }else{
            setTheme(R.style.Theme_AppCompat_Light);
            //Toast.makeText(this, "Dark Mode Disabled", Toast.LENGTH_SHORT).show();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_telling_lib);

        tvJoke = findViewById(R.id.lib_text_joke_main);
        tvTitle = findViewById(R.id.text_main_title);
        imagError = findViewById(R.id.image_error);
        fab = findViewById(R.id.fab_btn);
        bottomNavigation = findViewById(R.id.bottom_joke_library);
        containerBottomNavigation = findViewById(R.id.coordinate_bottom);

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

        bottomNavigation.setSelectedItemId(R.id.menu_like);
        bottomNavigation.setItemIconTintList(null);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.menu_like) {//Toast.makeText(JokeTellingActivityLib.this, "Clicked 0", Toast.LENGTH_SHORT).show();
                    Toast.makeText(JokeTellingActivityLib.this, "Cool! Glad you liked It", Toast.LENGTH_LONG).show();
                    finish();

                } else if (i == R.id.menu_dislike) {//Toast.makeText(JokeTellingActivityLib.this, "Clicked 1", Toast.LENGTH_SHORT).show();
                    Toast.makeText(JokeTellingActivityLib.this, "OH! I don't care !! LOL!\n(pun Intended)", Toast.LENGTH_LONG).show();
                    finish();

                }
                return false;
            }
        });
    }

    /**
     * Check if Error Has Occurred and It Occurred Hides Button and Text
     */
    private void checkError(){
        containerBottomNavigation.setVisibility(View.INVISIBLE);
        tvTitle.setText("Oops ! Some Error Has Occured :");
        imagError.setVisibility(View.VISIBLE);
    }

    private void sendJoke(String joke){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.send_joke_message)+"\n"+joke);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"Choose from which app to share"));
    }

}
