package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.vyas.pranav.androidjokelib.JokeTellingActivityLib;
import com.vyas.pranav.javajokelibrary.Joker;

import static com.vyas.pranav.androidjokelib.JokeTellingActivityLib.JOKE_KEY;

public class MainActivityNew extends AppCompatActivity implements EndpointsAsyncTask.AsyncCallback{
    private static final String TAG = "MainActivityNewPaid";
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        progress = new ProgressBar(this);
        android.widget.Toast.makeText(this, "Not Showing Ads", Toast.LENGTH_SHORT).show();
    }

    public void tellJoke(View view) {
        Joker jokerJavaLib = new Joker();
        String joke = jokerJavaLib.getJoke();
        Log.d(TAG, "tellJoke: Starting ProgressBar");
        progress.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, joke));
    }


    @Override
    public void getString(String jokeString) {
        Toast.makeText(this, jokeString, Toast.LENGTH_LONG).show();
        progress.setVisibility(View.GONE);
        Log.d(TAG, "getString: Stopping ProgressBar");
        Intent intent = new Intent(this, JokeTellingActivityLib.class);
        intent.putExtra(JOKE_KEY,jokeString);
        startActivity(intent);
    }
}
