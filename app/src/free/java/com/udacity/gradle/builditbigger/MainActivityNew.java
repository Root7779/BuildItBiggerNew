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
    private static final String TAG = "MainActivityNewFree";
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new ProgressBar(this);
        showAds();
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

    public void showAds(){
        AdView mAdView = findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        Toast.makeText(this, "Showing Ads Now", Toast.LENGTH_SHORT).show();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }
}
