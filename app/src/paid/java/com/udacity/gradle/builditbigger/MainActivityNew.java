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

import com.vyas.pranav.androidjokelib.JokeTellingActivityLib;
import com.vyas.pranav.javajokelibrary.Joker;

import static com.vyas.pranav.androidjokelib.JokeTellingActivityLib.JOKE_KEY;

public class MainActivityNew extends AppCompatActivity implements EndpointsAsyncTask.AsyncCallback,EndpointsAsyncTask.AsyncCallbackBegin{
    private static final String TAG = "MainActivityNewPaid";
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        progress = findViewById(R.id.progressBarMain);
        android.widget.Toast.makeText(this, "Not Showing Ads", Toast.LENGTH_SHORT).show();
    }

    public void tellJoke(View view) {
        Joker jokerJavaLib = new Joker();
        String joke = jokerJavaLib.getJoke();
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(this);
        asyncTask.execute(joke);
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

    @Override
    public void startedProgress(boolean isStarted) {
        Log.d(TAG, "tellJoke: Starting ProgressBar");
        progress.setVisibility(View.VISIBLE);
    }
}
